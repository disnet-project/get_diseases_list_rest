package edu.upm.midas.email.service;
import edu.upm.midas.email.component.EmailHtmlSender;
import edu.upm.midas.email.model.EmailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

/**
 * Created by gerardo on 22/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className EmailService
 * @see
 */
@Service
public class EmailService {

    @Autowired
    private EmailHtmlSender emailHtmlSender;

    @Value("${spring.mail.username}")
    private String administratorEmail;

    @Value("${email.template.successful.procedure}")
    private String successfulProcedureEmailTemplate;
    @Value("${email.template.error.procedure}")
    private String errorProcedureEmailTemplate;

    @Value("${email.template.successful.procedure.subject.gdl_api}")
    private String successfulEmailSubject;
    @Value("${email.template.error.procedure.subject.gdl_api}")
    private String errorEmailSubject;


    public EmailStatus sendSuccessfulProcedureMessage(Context context){
        return emailHtmlSender.send(this.administratorEmail, this.successfulEmailSubject, this.successfulProcedureEmailTemplate, context);
    }

    public EmailStatus sendErrorProcedureMessage(Context context){
        return emailHtmlSender.send(this.administratorEmail, this.errorEmailSubject, this.errorProcedureEmailTemplate, context);
    }

}
