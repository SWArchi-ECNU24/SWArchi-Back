package sw.archi.intermediator.constants;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

public class IMConstants {

    @Value("${application.auth.url}")
    private String injectedAuthModuleBaseUrl;

    @Value("${application.confjour.url}")
    private String injectedConfjourModuleBaseUrl;

    public static String authModuleBaseUrl;

    public static String confjourModuleBaseUrl;

    public static String authModuleApiPrefix = "auth";

    public static String confjourModuleApiPrefix = "confjour";

    @PostConstruct
    public void init() {
        authModuleBaseUrl = injectedAuthModuleBaseUrl;
        confjourModuleBaseUrl = injectedConfjourModuleBaseUrl;
    }
}
