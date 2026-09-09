package booking_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.common_util.config.KafkaConfigProperties.MOVIE_BOOKING_TOPIC;


@Configuration
public class BookingEventTopic {

  @Bean
  public NewTopic createBookingTopic() {
    return new NewTopic(MOVIE_BOOKING_TOPIC, 3, (short) 2);
  }

}
