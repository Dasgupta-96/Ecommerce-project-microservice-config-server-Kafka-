package seat_inventory_service.messages;

import com.common_util.event.SeatReserveEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.common_util.config.KafkaConfigProperties.SEAT_RESERVE_TOPIC;

@Component
public class SeatReserveProducer {
  private static final Logger logger = LoggerFactory.getLogger(SeatReserveProducer.class);
  @Autowired
  private KafkaTemplate<String, SeatReserveEvent> kafkaTemplate;

  public void publishSeatReserveEvent(SeatReserveEvent seatReserveEvent) {

    try {
      logger.info("successfully send the seat reserve info to down stream services {}", seatReserveEvent.getShowName());
      //TODO: process for payment to Payment microservices
      kafkaTemplate.send(SEAT_RESERVE_TOPIC, seatReserveEvent.getShowName(), seatReserveEvent);

    } catch (Exception e) {
      logger.error("failed to send the seat reserve info to down stream services {}", seatReserveEvent.getShowName());
     throw new RuntimeException("failed to send the seat reserve info to down stream services", e);
    }
  }
}
