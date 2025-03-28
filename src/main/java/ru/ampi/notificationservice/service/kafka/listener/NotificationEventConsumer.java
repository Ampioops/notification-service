package ru.ampi.notificationservice.service.kafka.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.common.common_utils.event.BookEvent;
import org.common.common_utils.event.NotificationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.ampi.notificationservice.service.mailsender.NotificationService;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationEventConsumer {

    public final NotificationService notificationService;

    @KafkaListener(topics = "notification-events", groupId = "notification-events-group")
    public void consume(NotificationEvent event) {
        log.info("Получено событие: {}", event);

        try {
            notificationService.sendEmail(event);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
