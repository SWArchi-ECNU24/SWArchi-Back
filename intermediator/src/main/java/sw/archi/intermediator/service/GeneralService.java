package sw.archi.intermediator.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.commonutils.helper.HttpDataHelper;
import sw.archi.commonutils.helper.HttpHelper;

@Service
public class GeneralService {

    @Autowired
    private RestTemplate restTemplate;

    public HttpDataHelper<JSONObject> getDataById(String baseUrl, String moduleName, String tableName, int id) {
        String api = "api/" + moduleName + "/get/" + tableName + "/" + String.valueOf(id);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + api);

        return HttpHelper.forwardHttpDataHelperDirectly(
                restTemplate.getForObject(uriBuilder.toUriString(), JSONObject.class));
    }

    public HttpDataHelper<JSONObject> deleteDataById(String baseUrl, String moduleName, String tableName, int id) {
        String api = "api/" + moduleName + "/delete";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + api)
                .queryParam(SWConstants.tableName, tableName)
                .queryParam(SWConstants.id, id);

        return HttpHelper.forwardHttpDataHelperDirectly(restTemplate
                .exchange(uriBuilder.toUriString(), HttpMethod.DELETE, null, JSONObject.class)
                .getBody());
    }
}
