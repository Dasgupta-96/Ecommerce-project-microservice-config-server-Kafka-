package booking_service.messages;

import com.common_util.event.BookingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.common_util.config.KafkaConfigProperties.MOVIE_BOOKING_TOPIC;

@Component
public class BookingEventProducer {

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;
  private static final Logger logger = LoggerFactory.getLogger(BookingEventProducer.class);

  public void produceBookingEvent(BookingEvent bookingEvent) {
    try {
      logger.info("trying to seno seat ind booking event tventory service {}", bookingEvent.getShowName());
      kafkaTemplate.send(MOVIE_BOOKING_TOPIC, bookingEvent.getShowName(), bookingEvent);
    } catch (Exception e) {
      throw new RuntimeException("unable to send booking info");
    }

  }
}
