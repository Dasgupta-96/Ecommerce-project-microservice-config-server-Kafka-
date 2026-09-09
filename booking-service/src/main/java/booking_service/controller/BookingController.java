package booking_service.controller;

import booking_service.service.BookingService;
import com.common_util.payload.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
  @RequestMapping("/booking")
public class BookingController {

  @Autowired
  private BookingService bookingService;

    @PostMapping("/create")
  public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest) {

    return ResponseEntity.ok(bookingService.createTicketBooking(bookingRequest));

  }
}
