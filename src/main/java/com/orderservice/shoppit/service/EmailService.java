package com.orderservice.shoppit.service;

import com.orderservice.shoppit.request.EmailRequest;
import com.orderservice.shoppit.response.EmailResponse;
import freemarker.core.ParseException;
import freemarker.template.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Configuration configuration;

    public EmailResponse sendEmail(EmailRequest emailRequest, Map<String,Object> model){

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = configuration.getTemplate("email_template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);

//            mimeMessageHelper.setFrom(emailRequest.getFromName());
            mimeMessageHelper.setFrom(new InternetAddress("noreply@aj.com","Aji"));
            mimeMessageHelper.setText(html,true);
            mimeMessageHelper.setTo(emailRequest.getToName());
            mimeMessageHelper.setSubject(emailRequest.getSubject());

            mailSender.send(message);

            return new EmailResponse("success","email sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (MalformedTemplateNameException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }


    }

}
