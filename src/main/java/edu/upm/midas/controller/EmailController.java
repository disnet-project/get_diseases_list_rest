package edu.upm.midas.controller;

import edu.upm.midas.constants.Constants;
import edu.upm.midas.email.model.EmailStatus;
import edu.upm.midas.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gerardo on 21/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className EmailController
 * @see
 */
@Controller
@RequestMapping(value = "${my.service.rest.request.mapping.general.url}")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private Constants constants;


    @RequestMapping(value = "/email/test", method = RequestMethod.GET)
    @ResponseBody
    String test(HttpServletRequest request) {
        try {
            System.out.println( request.getServletPath() );
            Context context = new Context();
            context.setVariable("user", "Gerardo Lagunes García");
            context.setVariable("email","grardolagar@gmail.com");
            EmailStatus confirmationEmailStatus= emailService.sendSuccessfulProcedureMessage( context );
            return "Email Sent!";
        } catch (Exception ex) {
            return "Error in sending email: " + ex;
        }
    }


}
