package sw.archi.commonutils.helper;

import com.alibaba.fastjson.JSONObject;
import java.util.Objects;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.commonutils.struct.EnumCode;

public class HttpHelper {
    public static HttpDataHelper<JSONObject> forwardHttpDataHelperDirectly(JSONObject apiRes) {
        if (apiRes == null) {
            return HttpDataHelper.error(EnumCode.REQUEST_ERROR);
        }

        if (Objects.equals(apiRes.getInteger(SWConstants.code), EnumCode.SUCCESS.getCode())) {
            return HttpDataHelper.success(apiRes.getJSONObject(SWConstants.data));
        } else {
            return HttpDataHelper.error(EnumCode.getEnumCodeType(apiRes.getInteger(SWConstants.code)));
        }
    }
}
