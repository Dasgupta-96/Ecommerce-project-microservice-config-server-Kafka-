package seat_inventory_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import seat_inventory_service.entity.SeatInventory;

import java.util.List;

public interface SeatInventoryRepository extends JpaRepository<SeatInventory, Long> {

  @Query("""
    SELECT s
    FROM SeatInventory s
    WHERE s.showName = :showName
    AND s.seatId IN :seatIds
""")
  List<SeatInventory> findSeats(
    @Param("showName") String showName,
    @Param("seatIds") List<String> seatIds
  );
}
