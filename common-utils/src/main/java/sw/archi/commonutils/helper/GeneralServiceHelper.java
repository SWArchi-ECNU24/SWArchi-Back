package sw.archi.commonutils.helper;

import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Method;
import java.util.Objects;
import sw.archi.commonutils.constants.SWConstants;

public class GeneralServiceHelper {

    public static JSONObject getDataById(String tableName, int id, String packageName, Object obj) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        packageName,
                        SWConstants.daoClassPackageName,
                        tableName + SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Method findByIdMethod = dataDaoClass.getMethod(SWConstants.findByIdMethodName, Object.class);

        return DataHelper.getResultResponse(obj.getClass().getDeclaredFields(), dataDaoClass, findByIdMethod, obj, id);
    }

    public static JSONObject getData(String tableName, String packageName, Object obj) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        packageName,
                        SWConstants.daoClassPackageName,
                        tableName + SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Method findAllMethod = dataDaoClass.getMethod(SWConstants.findAllMethodName);

        return DataHelper.getResultResponse(obj.getClass().getDeclaredFields(), dataDaoClass, findAllMethod, obj, null);
    }

    public static JSONObject deleteData(String tableName, int id, String packageName, Object obj) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        packageName,
                        SWConstants.daoClassPackageName,
                        tableName + SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Method findByIdMethod = dataDaoClass.getMethod(SWConstants.findByIdMethodName, Object.class);

        Method deleteByIdMethod = dataDaoClass.getMethod(SWConstants.deleteByIdMethodName, Object.class);

        return DataHelper.deleteResultResponse(
                obj.getClass().getDeclaredFields(), dataDaoClass, findByIdMethod, deleteByIdMethod, obj, id);
    }

    public static JSONObject addAndUpdateData(
            String tableName, Integer id, JSONObject value, String packageName, Object obj) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        packageName,
                        SWConstants.daoClassPackageName,
                        tableName + SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Class<?> dataEntityClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName, packageName, SWConstants.entityClassPackageName, tableName),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        if (Objects.equals(id, null)) {
            Method addMethod = dataDaoClass.getMethod(SWConstants.addMethodName, Object.class);

            return DataHelper.addResultResponse(
                    obj.getClass().getDeclaredFields(), dataDaoClass, addMethod, obj, dataEntityClass, value);
        } else {
            Method findByIdMethod = dataDaoClass.getMethod(SWConstants.findByIdMethodName, Object.class);

            Method updateMethod = dataDaoClass.getMethod(SWConstants.addMethodName, Object.class);

            return DataHelper.updateResultResponse(
                    obj.getClass().getDeclaredFields(),
                    dataDaoClass,
                    findByIdMethod,
                    updateMethod,
                    obj,
                    id,
                    dataEntityClass,
                    value);
        }
    }
}
