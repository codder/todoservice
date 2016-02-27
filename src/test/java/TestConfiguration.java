
import dev.codder.services.TODOStorageService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Daniil on 2/27/2016.
 */

@Configuration
public class TestConfiguration {

    @Bean
    public TODOStorageService todoService() {
        return Mockito.mock(TODOStorageService.class);
    }
}
