package com.bank.bank_app;

import com.bank.bank_app.auth_User.model.User;
import com.bank.bank_app.notification.dto.NotificationDto;
import com.bank.bank_app.notification.service.NotificationService;
import com.bank.bank_app.shared.enums.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@RequiredArgsConstructor
public class BankAppApplication {
   // private final NotificationService notificationService;
	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
	}

//        @Bean
//        CommandLineRunner runner(){
//        return args -> {
//            NotificationDto notificationDTO = NotificationDto.builder()
//                    .recipient("therecepientemail@gmail.com")
//                    .subject("HEllo testing email")
//                    .body("Hey, this is a test eamil 😁")
//                    .type(NotificationType.EMAIL)
//                    .build();
//
//            notificationService.sendEmail(notificationDTO, new User());
//        };
//    }
}
