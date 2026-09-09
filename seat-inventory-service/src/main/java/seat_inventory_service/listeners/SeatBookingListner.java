package seat_inventory_service.listeners;

import com.common_util.event.BookingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;
import seat_inventory_service.service.SeatInventoryService;

import static com.common_util.config.KafkaConfigProperties.MOVIE_BOOKING_TOPIC;

@Component
public class SeatBookingListner {

  @Autowired
  private SeatInventoryService seatInventoryService;
  private static final Logger logger = LoggerFactory.getLogger(SeatBookingListner.class);

  @KafkaListener(topics = MOVIE_BOOKING_TOPIC)
  public void ticketBookingProcess(BookingEvent bookingEvent) {
    logger.info("Process started for booking {}", bookingEvent.getShowName());
    seatInventoryService.processBooking(bookingEvent);
  }
}
