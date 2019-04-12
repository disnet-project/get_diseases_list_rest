package edu.upm.midas.scheduling;

import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.constants.Constants;
import edu.upm.midas.email.model.EmailStatus;
import edu.upm.midas.email.service.EmailService;
import edu.upm.midas.model.jpa.Album;
import edu.upm.midas.service.Populate;
import edu.upm.midas.service.jpa.AlbumService;
import edu.upm.midas.service.jpa.helper.DiseaseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.sql.Date;

/**
 * Created by gerardo on 03/11/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className ExtractionScheduling
 * @see
 */
@Service
public class ExtractionScheduling {

    private static final Logger logger = LoggerFactory.getLogger(ExtractionScheduling.class);

    @Autowired
    private Populate populateService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private TimeProvider timeProvider;

    /**
     * Método para extraer una nueva lista de enfermedades desde DBPedia y almacenar en la
     * base de datos "addb".
     *
     * Se ejecutará cada día primero de cada mes a la 12:00 horas = @Scheduled(cron = "0 0 12 1 * ? ").
     *
     * Explicación de las expresiones Cron:
     *
     * Dada la siguiente expresión: @Scheduled(cron = "0 9 23 ? * 5 ")
     * La tarea anterior se ejecutará a las 23 horas con 9 minutos y 0 segundos, todos los meses, los días 5 (viernes).

        Las expresiones cron tienen 6 valores obligatorios.

            Segundos. En nuestro ejemplo tiene el valor 0. Acepta valores del 0-59 y caracteres especiales como , - * /
            Minutos. En nuestro ejemplo tiene el valor 9. Acepta valores del 0-59 y caracteres especiales como , - * /
            Hora. En nuestro ejemplo tiene el valor 23. Acepta valores del 0-23 y caracteres especiales como , - * /
            Día del mes. En nuestro ejemplo tiene el caracter especial “?” el cual significa no definido
             ya que no deseamos que se ejecute un determinado día del mes,
             en su lugar deseamos que se ejecute un determinado día de la semana.
             Acepta valores del 1-31 y caracteres especiales como , - * ? /
            Mes. En nuestro ejemplo tiene el caracter especial “*” el cuál significa todos , es decir, deseamos se ejecute todos los meses. Acepta valores del 1-12 o abreviaturas JAN-DEC y caracteres especiales como , - * /
            Día de la semana. En nuestro ejemplo tiene el valor 5, es decir, deseamos se ejecute el quinto día (Viernes). Acepta valores del 1-7 o abreviaturas SUN-SAT y caracteres especiales como , - * ? /
            El día del mes y el día de la semana son excluyentes, es decir que podemos definir solo uno de los dos, no ámbos. En nuestro ejemplo queremos que se ejecute siempre un día de la semana por lo tanto en la posición de día del mes asignaremos un “?” para indicar que no está definido.

            El caracter especial “/” se usa para especificar incrementos. Por ejemplo en el campo de minutos, un valor como 0/1 indica que la tarea se ejecutará cada minuto, en el campo de segundos un valor como 0/15 indica una ejecución cada 15 segundos.
                Se ejecuta cada minuto de todos los dias sábados a media noche.
                @Scheduled(cron = "0 0/1 0 ? * 6 ")

            El caracter especial “,” se usa para especificar un conjunto de valores. Por ejemplo en el campo de día de la semana, un valor como “6,7” indica que la tarea se ejecutará todos los sábados y domingos.
                Se ejecuta cada 15 segundos los días sábados y domingos a media noche.
                @Scheduled(cron = "0/15 * 0 ? * 6,7 ")
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void extractionEveryFirstDayOfTheMonth() throws Exception {
        try {
            Album album = null;
            logger.info("Scheduled task for the first of each month at midnight starts!" + timeProvider.getNowFormatyyyyMMdd());
            album = populateService.populate();
            if (album!=null)
                updateAlbumDiseaseListProcedure(album, Constants.WIKIPEDIA_SOURCE);

            logger.info("Scheduled task for the first of each month at midnight end!" + timeProvider.getNowFormatyyyyMMdd());
        }catch (Exception e){
            logger.error("Error to populate tha DB with the scheduled task for the first of each month at midnight", e);
        }
    }


    /**
     * Método que actualiza la lista de enfermedades del album recien creado
     *
     * Integra enfermedades provenientes de la Safe List de enfermedades y con las enfermedades del
     * album enterior
     *
     * @param album
     * @param sourceName
     */
    public void updateAlbumDiseaseListProcedure(Album album, String sourceName){
        try {
            logger.info("Update the currently disease album with the disease Safe List and the last disease album");
            populateService.populateAlbumWithDiseaseSafeListAndLastDiseaseAlbum(sourceName, album);
            logger.info("Update the currently disease album with the disease Safe List and the last disease album... READY!");
            logger.info("Update disease Safe List");
            populateService.updateDiseaseSafeList(sourceName, album);
            logger.info("Update disease Safe List... READY!");
            createAndSendInformationEmail(album, sourceName);
        }catch (Exception e){
            logger.error("Error to update current album {}", album.toString(), e);
        }
    }


    public Album getPenultimateAlbumInfo(Album album, String sourceName){
        Date penultimateAlbumDate = albumService.getPenultimateDiseaseAlbumDateBySourceNative(false, album.getDate(), sourceName);
        edu.upm.midas.model.response.Album penultimateAlbum = albumService.findByVersionAndSourceNative(penultimateAlbumDate, sourceName);
        Album albumReturned = null;
        if (penultimateAlbum!=null) {
            albumReturned = new Album(penultimateAlbum.getAlbumId(), timeProvider.convertSQLDateToUtilDate(penultimateAlbum.getDate()), penultimateAlbum.getNumberDiseases());
        }
        return albumReturned;
    }


    public void createAndSendInformationEmail(Album currentAlbum, String sourceName){
        Album penultimateAlbum = getPenultimateAlbumInfo(currentAlbum, sourceName);
        if (penultimateAlbum!=null) {
            Context context = new Context();
            context.setVariable("currentAlbum", currentAlbum);
            context.setVariable("penultimateAlbum", penultimateAlbum);
            context.setVariable("version", currentAlbum.getDate().toString());
            context.setVariable("user", "Gerardo Lagunes");
            String messageType = "";
            String color = "";
            int diff = currentAlbum.getNumberDiseases() - penultimateAlbum.getNumberDiseases();
            if (diff>=0){
                messageType = "Info. The number of diseases has increased";
                color = "green-text";
            }else{
                messageType = "Warning. The number of diseases has decrease, please verify disease list.";
                color = "red-text";
            }
            context.setVariable("message_type", messageType);
            context.setVariable("message_color", color);
            EmailStatus confirmationEmailStatus = emailService.sendSuccessfulProcedureMessage(context);
        }
    }


    /**
     * Método para extraer una nueva lista de enfermedades desde DBPedia y almacenar en la
     * base de datos "addb".
     *
     * Se ejecutará cada día quince de cada mes a la 12:00 horas = @Scheduled(cron = "0 0 12 15 * ? ").
     */
    //@Scheduled(cron = "0 15 14 15 * ?" )
    @Scheduled(cron = "0 0 0 15 * ?")
    public void extractionEvery15thDayOfTheMonth() throws Exception {
        try {
            Album album = null;
            logger.info("Scheduled for the 15th of each month at midnight starts!" + timeProvider.getNowFormatyyyyMMdd());
            album = populateService.populate();
            if (album!=null)
                updateAlbumDiseaseListProcedure(album, Constants.WIKIPEDIA_SOURCE);

            logger.info("Scheduled for the 15th of each month at midnight ends!" + timeProvider.getNowFormatyyyyMMdd());
        }catch (Exception e){
            logger.error("Error to populate tha DB with the scheduled task for the 15th of each month at midnight", e);
        }
    }

    //TEST
    //@Scheduled(cron = "0 0 10 17 * ?")
//    @Scheduled(fixedRate = 30000)
    //@Scheduled(cron = "0 15 11 18 * ?" )
    public void test() throws Exception {
        try {
            System.out.println("Tarea programada usando expresiones Cron: 0 0 12 15 * ?" + System.currentTimeMillis() / 1000 + timeProvider.getNowFormatyyyyMMdd());
//            getDiseasesFromDBPedia.getDiseasesFromDBPedia("dbpedia");
        }catch (Exception e){
            System.out.println("getAlbumListERR (15thOfTheMonth): " + e.getMessage());
        }
    }

    // Se ejecuta cada 3 segundos
    //@Scheduled(fixedRate = 3000)
    public void tarea1() {
        System.out.println("Tarea usando fixedRate cada 3 segundos - " + System.currentTimeMillis() / 1000 + timeProvider.getNowFormatyyyyMMdd());
    }

}
