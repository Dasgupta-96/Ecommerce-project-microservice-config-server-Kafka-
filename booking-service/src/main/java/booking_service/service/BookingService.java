package booking_service.service;

import booking_service.entity.BookingEntity;
import booking_service.entity.BookingStatus;
import booking_service.messages.BookingEventProducer;
import booking_service.repository.BookingRepository;
import com.common_util.event.BookingEvent;
import com.common_util.event.SeatReserveEvent;
import com.common_util.payload.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private BookingEventProducer bookingEventProducer;

  public BookingEntity createTicketBooking(BookingRequest bookingRequest) {

    BookingEntity bookingEntity = mapBookingRequestToEntity(bookingRequest);

    BookingEntity savedEntity = bookingRepository.save(bookingEntity);

    BookingEvent bookingEvent = convertEntityToBookingEvent(savedEntity);

    bookingEventProducer.produceBookingEvent(bookingEvent);

    return bookingEntity;

  }

  private BookingEvent convertEntityToBookingEvent(BookingEntity savedEntity) {
    return new BookingEvent(savedEntity.getUserId(), savedEntity.getShowName(), savedEntity.getSeats());
  }

  private BookingEntity mapBookingRequestToEntity(BookingRequest bookingRequest) {

    BookingEntity bookingEntity = new BookingEntity();
    bookingEntity.setBookingId(UUID.randomUUID().toString());
    bookingEntity.setUserId(bookingRequest.getUserId());
    bookingEntity.setBookingStatus(BookingStatus.CONFIRMED);
    bookingEntity.setCreatedAt(LocalDateTime.now());
    bookingEntity.setSeats(bookingRequest.getSeats());
    bookingEntity.setShowName(bookingRequest.getShowName());

    return bookingEntity;
  }

  public void handelReservationFailure(SeatReserveEvent seatReserveEvent) {
    BookingEntity bookingEntity = bookingRepository.findByUserId(seatReserveEvent.getUserId());

    bookingEntity.setBookingStatus(BookingStatus.CANCELLED);
    bookingRepository.save(bookingEntity);

  }
}
