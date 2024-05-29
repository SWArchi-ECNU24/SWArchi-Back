package sw.archi.commonutils.helper;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.commonutils.struct.EnumCode;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HttpDataHelper<T> {
    private int code; // 状态码
    private String message; // 提示消息
    private T data; // 响应结果集数据

    public void parserEnum(EnumCode enumCode) {
        this.code = enumCode.getCode();
        this.message = enumCode.getMessage();
    }

    public static <T> HttpDataHelper<T> success(T data) {

        HttpDataHelper<T> HttpDataHelper = new HttpDataHelper<T>();
        HttpDataHelper.parserEnum(EnumCode.SUCCESS);
        HttpDataHelper.setData(data);
        return HttpDataHelper;
    }

    public static <T> HttpDataHelper<T> error(EnumCode enumCode) {

        HttpDataHelper<T> HttpDataHelper = new HttpDataHelper<T>();
        HttpDataHelper.parserEnum(enumCode);
        return HttpDataHelper;
    }

    public static <T> HttpDataHelper<T> generator(int code, String message) {
        HttpDataHelper<T> HttpDataHelper = new HttpDataHelper<T>();
        HttpDataHelper.setCode(code);
        HttpDataHelper.setMessage(message);
        return HttpDataHelper;
    }

    public ResponseEntity<JSONObject> toResponseEntity() {
        JSONObject responseBody = new JSONObject();
        responseBody.put(SWConstants.code, this.code);
        responseBody.put(SWConstants.message, this.message);
        responseBody.put(SWConstants.data, this.data);
        return ResponseEntity.status(this.code).body(responseBody);
    }
}
