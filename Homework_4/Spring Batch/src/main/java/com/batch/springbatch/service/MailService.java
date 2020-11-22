package com.batch.springbatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    @Qualifier("gmail")
    private JavaMailSender mailSender;

    public void sendMail(String from, String subject, String toAddresses, String body) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(toAddresses.split("[,;]"));
            message.setFrom(from, "<From Name>");
            message.setSubject(subject);
            message.setText(body, false);
        };
        mailSender.send(preparator);
    }
}