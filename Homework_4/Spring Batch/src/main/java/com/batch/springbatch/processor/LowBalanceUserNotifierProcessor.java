package com.batch.springbatch.processor;

import com.batch.springbatch.model.User;
import com.batch.springbatch.service.MailService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LowBalanceUserNotifierProcessor implements ItemProcessor<User, Optional<Void>> {

    private static final String MESSAGE = "Your balance is below $10";

    @Autowired
    private MailService mailService;

    @Override
    public Optional<Void> process(User item) throws Exception {
        String email = item.getEmail();
        mailService.sendMail("utel200519@gmail.com", "Low balance notification", email, MESSAGE);
        return Optional.empty();
    }
}
