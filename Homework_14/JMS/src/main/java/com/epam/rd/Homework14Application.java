package com.epam.rd;

import com.epam.rd.message.Message;
import com.epam.rd.sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class Homework14Application implements ApplicationRunner {

	public static final int MESSAGE_COUNT = 10;

	@Autowired
	private Sender sender;

	public static void main(String[] args) {
		SpringApplication.run(Homework14Application.class, args);
	}

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            int firstNumber = new Random().nextInt(50);
            int secondNumber = new Random().nextInt(50);
            sender.send(new Message(firstNumber, secondNumber));
        }
    }
}
