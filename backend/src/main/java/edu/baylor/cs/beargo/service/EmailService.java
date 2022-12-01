package edu.baylor.cs.beargo.service;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class EmailService {
    @Value("${beargo.admin.email}")
    private String adminEmail;

    @Value("${sendgrid.api.key}")
    private String sendgridApiKey;

    Map<String, Integer> confirmationCodes = new HashMap<>();

    public void sendVerificationEmail(String email) throws IOException {
        Integer code = new Random().nextInt(9000) + 1000;
        confirmationCodes.put(email, code);

        Email from = new Email(adminEmail);
        Email to = new Email(email);

        String subject = "BearGo: Confirmation Code";
        Content content = new Content("text/plain", "Your confirmation code is " + code);

        log.info("Sending verification email to " + email);
        log.info("TODO REMOVE THIS CODE: " + code);
        Mail mail = new Mail(from, subject, to, content);
        // sendEmail(mail);
    }

    public boolean verifyCode(String email, int code) {
        if (confirmationCodes.containsKey(email)) {
            return confirmationCodes.get(email) == code;
        } else {
            return false;
        }
    }

    public void sendEmail(Mail mail) throws IOException {
        SendGrid sg = new SendGrid(sendgridApiKey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        sg.api(request);
    }
}
