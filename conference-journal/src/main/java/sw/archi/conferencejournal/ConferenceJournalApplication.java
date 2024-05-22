package sw.archi.conferencejournal;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(info = @Info(title = "会议期刊模块", description = "会议期刊模块 Conference-Journal"))
@EnableDiscoveryClient
@SpringBootApplication
public class ConferenceJournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceJournalApplication.class, args);
    }
}
