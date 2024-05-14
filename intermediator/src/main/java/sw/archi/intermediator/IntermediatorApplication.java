package sw.archi.intermediator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class IntermediatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntermediatorApplication.class, args);
    }
}
