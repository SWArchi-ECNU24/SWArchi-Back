package sw.archi.auth.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class FastJsonDefaultInitConfig {

    @PostConstruct
    public void postConstruct() {
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
    }
}
