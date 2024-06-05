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
import sw.archi.intermediator.constants.IMConstants;
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

        if (!checkForeignKey(moduleName, tableName, value)) {
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

    public Boolean checkId(String baseUrl, String moduleName, String tableName, int id) {
        return Objects.equals(getDataById(baseUrl, moduleName, tableName, id).getCode(), EnumCode.SUCCESS.getCode());
    }

    public Boolean checkForeignKey(String moduleName, String tableName, JSONObject obj) {
        if (Objects.equals(moduleName, SWConstants.authModulePackageName)) {
            switch (tableName) {
                case SWConstants.followedConferenceTableName:
                    return checkId(
                                    IMConstants.authModuleBaseUrl,
                                    SWConstants.authModuleName,
                                    SWConstants.userTableName,
                                    obj.getIntValue(SWConstants.userId))
                            && checkId(
                                    IMConstants.confjourModuleBaseUrl,
                                    SWConstants.confjourModuleName,
                                    SWConstants.conferenceTableName,
                                    obj.getIntValue(SWConstants.conferenceId));
                case SWConstants.followedJournalTableName:
                    return checkId(
                                    IMConstants.authModuleBaseUrl,
                                    SWConstants.authModuleName,
                                    SWConstants.userTableName,
                                    obj.getIntValue(SWConstants.userId))
                            && checkId(
                                    IMConstants.confjourModuleBaseUrl,
                                    SWConstants.confjourModuleName,
                                    SWConstants.journalTableName,
                                    obj.getIntValue(SWConstants.journalId));
                case SWConstants.conferenceCfpTableName:
                    return checkId(
                            IMConstants.confjourModuleBaseUrl,
                            SWConstants.confjourModuleName,
                            SWConstants.conferenceTableName,
                            obj.getIntValue(SWConstants.conferenceId));
                case SWConstants.journalCfpTableName:
                    return checkId(
                            IMConstants.confjourModuleBaseUrl,
                            SWConstants.confjourModuleName,
                            SWConstants.journalTableName,
                            obj.getIntValue(SWConstants.journalId));
                case SWConstants.journalIssueTableName:
                    return checkId(
                            IMConstants.confjourModuleBaseUrl,
                            SWConstants.confjourModuleName,
                            SWConstants.journalTableName,
                            obj.getIntValue(SWConstants.journalId));
                case SWConstants.conferenceGroupTableName:
                    Boolean flag = true;
                    for (String id : obj.getString(SWConstants.userId).split(",")) {
                        flag &= checkId(
                                IMConstants.authModuleBaseUrl,
                                SWConstants.authModuleName,
                                SWConstants.userTableName,
                                Integer.parseInt(id));
                    }
                    return flag;
                default:
                    return true;
            }
        } else {
            switch (tableName) {
                case SWConstants.userFollowersTableName:
                    return checkId(
                                    IMConstants.authModuleBaseUrl,
                                    SWConstants.authModuleName,
                                    SWConstants.userTableName,
                                    obj.getIntValue(SWConstants.userId))
                            && checkId(
                                    IMConstants.authModuleBaseUrl,
                                    SWConstants.authModuleName,
                                    SWConstants.userTableName,
                                    obj.getIntValue(SWConstants.followId))
                            && !Objects.equals(
                                    obj.getIntValue(SWConstants.userId), obj.getIntValue(SWConstants.followId));
                default:
                    return true;
            }
        }
    }
}
