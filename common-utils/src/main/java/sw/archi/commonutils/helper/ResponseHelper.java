package sw.archi.commonutils.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import sw.archi.commonutils.constants.SWConstants;

public class ResponseHelper {
    public static int requestErrorCode = 515;

    public static int authorityErrorCode = 401;

    public static int successCode = 200;

    public static JSONObject constructSuccessResponse(Object object) {
        JSONObject res = new JSONObject();
        res.put(SWConstants.code, ResponseHelper.successCode);
        res.put(SWConstants.data, JSONObject.parseObject(JSON.toJSONString(object)));
        return res;
    }

    public static JSONObject constructFailedResponse(int code) {
        JSONObject res = new JSONObject();
        res.put(SWConstants.code, code);
        res.put(SWConstants.data, null);
        return res;
    }
}
