package ru.ampi.notificationservice.service.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.common.common_utils.event.NotificationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.ampi.notificationservice.service.mailsender.NotificationSenderService;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationEventKafkaConsumer {

    public final NotificationSenderService notificationSenderService;

    @KafkaListener(topics = "notification-events", groupId = "notification-events-group")
    public void consume(NotificationEvent event) {
        log.info("Получено событие: {}", event);

        try {
            notificationSenderService.sendEmail(event);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
