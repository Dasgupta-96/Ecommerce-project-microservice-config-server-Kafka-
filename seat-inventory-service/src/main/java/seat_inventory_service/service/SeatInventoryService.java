package seat_inventory_service.service;

import com.common_util.event.BookingEvent;
import com.common_util.event.SeatReserveEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seat_inventory_service.SeatInventoryRepository;
import seat_inventory_service.entity.SeatInventory;
import seat_inventory_service.entity.SeatStatus;
import seat_inventory_service.messages.SeatReserveProducer;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatInventoryService {
  @Autowired
  private SeatInventoryRepository seatInventoryRepository;

  @Autowired
  private SeatReserveProducer seatReserveProducer;

  public void processBooking(BookingEvent bookingEvent) {
    List<SeatInventory> seatInventories = seatInventoryRepository
      .findSeats(bookingEvent.getShowName(), bookingEvent.getSeats());

    boolean available = seatInventories.stream()
      .allMatch(seat -> seat.getSeatStatus().equals(SeatStatus.AVAILABLE));

    if (available) {
      seatInventories.stream()
        .forEach(seat -> {
          seat.setSeatStatus(SeatStatus.LOCKED);
          seat.setLastUpdatedAt(LocalDateTime.now());
        });
      seatInventoryRepository.saveAll(seatInventories);
      seatReserveProducer.publishSeatReserveEvent(new
        SeatReserveEvent(bookingEvent.getShowName(), true, bookingEvent.getUserId()));
    } else {
      seatReserveProducer.publishSeatReserveEvent(new
        SeatReserveEvent(bookingEvent.getShowName(), false, bookingEvent.getUserId()));
    }

  }
}
