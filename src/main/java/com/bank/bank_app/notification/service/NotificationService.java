package com.bank.bank_app.notification.service;

import com.bank.bank_app.auth_User.model.User;
import com.bank.bank_app.notification.dto.NotificationDto;

public interface NotificationService {
    void sendEmail(NotificationDto notificationDTO, User user);
}