package sw.archi.conferencejournal.service;

import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.commonutils.helper.ResponseHelper;

@Service
public class GeneralService {

    public JSONObject getDataById(String tableName, int id) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                SWConstants.projectPackageName + "." + SWConstants.confjourModulePackageName + "."
                        + SWConstants.daoClassPackageName
                        + "." + tableName
                        + SWConstants.daoClassSuffix,
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Method getByIdMethod = null;

        for (Method classMethods : dataDaoClass.getMethods()) {
            if (Objects.equals(classMethods.getName(), SWConstants.findByIdMethodName)) {
                getByIdMethod = classMethods;
            }
        }

        for (Field selfField : this.getClass().getDeclaredFields()) {
            if (Objects.equals(selfField.getType().getName(), dataDaoClass.getName())) {
                Optional<?> getIdRes = (Optional<?>) getByIdMethod.invoke(selfField.get(this), id);

                if (getIdRes.isEmpty()) {
                    return ResponseHelper.constructFailedResponse(ResponseHelper.requestErrorCode);
                } else {
                    return ResponseHelper.constructSuccessResponse(getIdRes.get());
                }
            }
        }

        return ResponseHelper.constructFailedResponse(ResponseHelper.requestErrorCode);
    }
}
