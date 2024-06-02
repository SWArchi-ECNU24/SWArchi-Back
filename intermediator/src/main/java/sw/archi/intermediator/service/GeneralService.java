package sw.archi.intermediator.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.commonutils.helper.DataHelper;
import sw.archi.commonutils.helper.HttpDataHelper;
import sw.archi.commonutils.helper.HttpHelper;
import sw.archi.commonutils.struct.EnumCode;
import sw.archi.intermediator.helper.FilterHelper;

@Service
public class GeneralService {

    @Autowired
    private RestTemplate restTemplate;

    public JSONObject getAllData(String baseUrl, String moduleName, String tableName) {
        String api = "api/" + moduleName + "/get/" + tableName;

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + api);

        return restTemplate.getForObject(uriBuilder.toUriString(), JSONObject.class);
    }

    public HttpDataHelper<JSONObject> getDataById(String baseUrl, String moduleName, String tableName, int id) {
        String api = "api/" + moduleName + "/get/" + tableName + "/" + String.valueOf(id);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + api);

        return HttpHelper.forwardHttpDataHelperDirectly(
                restTemplate.getForObject(uriBuilder.toUriString(), JSONObject.class));
    }

    public HttpDataHelper<JSONArray> getData(
            String baseUrl, String moduleName, String tableName, JSONObject queryObject) {
        return HttpDataHelper.success(DataHelper.filterJSONObjectKeywords(
                getAllData(baseUrl, moduleName, tableName).getJSONArray(SWConstants.data), queryObject));
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

    public HttpDataHelper<JSONObject> addNUpdateDataById(
            String baseUrl, String moduleName, String tableName, Integer id, JSONObject value) {
        JSONArray filterRes = DataHelper.filterJSONObjectKeys(
                value,
                getAllData(baseUrl, moduleName, tableName).getJSONArray(SWConstants.data),
                FilterHelper.generateKeyNames(moduleName, tableName));

        if (!(filterRes.isEmpty()
                || (filterRes.size() == 1
                        && !Objects.equals(id, null)
                        && Objects.equals(
                                FilterHelper.getTableId(filterRes.getJSONObject(0)),
                                FilterHelper.getTableId(value))))) {
            return HttpDataHelper.error(EnumCode.getEnumCodeType(EnumCode.PARAMETER_ERROR.getCode()));
        }

        if (Objects.equals(id, null)) {
            String api = "api/" + moduleName + "/add";

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + api);

            MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
            requestEntity.add(SWConstants.tableName, tableName);
            requestEntity.add(SWConstants.value, value.toJSONString());

            return HttpHelper.forwardHttpDataHelperDirectly(
                    restTemplate.postForObject(uriBuilder.toUriString(), requestEntity, JSONObject.class));
        } else {
            String api = "api/" + moduleName + "/update";

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + api)
                    .queryParam(SWConstants.tableName, tableName)
                    .queryParam(SWConstants.id, id)
                    .queryParam(SWConstants.value, value);

            return HttpHelper.forwardHttpDataHelperDirectly(restTemplate
                    .exchange(uriBuilder.toUriString(), HttpMethod.PUT, null, JSONObject.class)
                    .getBody());
        }
    }
}
