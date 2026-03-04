package com.bank.bank_app.notification.repository;

import com.bank.bank_app.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  NotificationRepository extends JpaRepository<Notification, Long> {

}
