package booking_service.repository;

import booking_service.entity.BookingEntity;
import com.common_util.event.BookingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

  BookingEntity findByUserId(String userId);
}
