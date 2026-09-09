package booking_service.listner;

import booking_service.service.BookingService;
import com.common_util.event.SeatReserveEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.common_util.config.KafkaConfigProperties.SEAT_RESERVE_TOPIC;

@Component
public class SeatReserveConsumer {

  @Autowired
  private BookingService bookingService;

  private static final Logger log = LoggerFactory.getLogger(SeatReserveConsumer.class);

  @KafkaListener(topics = SEAT_RESERVE_TOPIC)
  public void handelSeatReservation(SeatReserveEvent seatReserveEvent) {
    if (seatReserveEvent.isReserved()) {
      log.info("Booking process completed for movie: {}", seatReserveEvent.getShowName());
    } else {
      log.info("Seat reservation failed for movie: {}", seatReserveEvent.getShowName());
      bookingService.handelReservationFailure(seatReserveEvent);
    }
  }
}
