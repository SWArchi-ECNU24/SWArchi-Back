package sw.archi.auth.service;

import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Method;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sw.archi.auth.dao.UserRepository;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.commonutils.helper.DataHelper;

@Service
public class GeneralService {

    @Autowired
    public UserRepository userRepository;

    public JSONObject getDataById(String tableName, int id) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        SWConstants.authModulePackageName,
                        SWConstants.daoClassPackageName,
                        tableName + SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Method findByIdMethod = dataDaoClass.getMethod(SWConstants.findByIdMethodName, Object.class);

        return DataHelper.getResultResponse(
                this.getClass().getDeclaredFields(), dataDaoClass, findByIdMethod, this, id);
    }

    public JSONObject getData(String tableName) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        SWConstants.authModulePackageName,
                        SWConstants.daoClassPackageName,
                        tableName + SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Method findAllMethod = dataDaoClass.getMethod(SWConstants.findAllMethodName);

        return DataHelper.getResultResponse(
                this.getClass().getDeclaredFields(), dataDaoClass, findAllMethod, this, null);
    }

    public JSONObject deleteData(String tableName, int id) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        SWConstants.authModulePackageName,
                        SWConstants.daoClassPackageName,
                        tableName + SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Method findByIdMethod = dataDaoClass.getMethod(SWConstants.findByIdMethodName, Object.class);

        Method deleteByIdMethod = dataDaoClass.getMethod(SWConstants.deleteByIdMethodName, Object.class);

        return DataHelper.deleteResultResponse(
                this.getClass().getDeclaredFields(), dataDaoClass, findByIdMethod, deleteByIdMethod, this, id);
    }

    public JSONObject addAndUpdateData(String tableName, Integer id, JSONObject value) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        SWConstants.authModulePackageName,
                        SWConstants.daoClassPackageName,
                        tableName + SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Class<?> dataEntityClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        SWConstants.authModulePackageName,
                        SWConstants.entityClassPackageName,
                        tableName),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        if (Objects.equals(id, null)) {
            Method addMethod = dataDaoClass.getMethod(SWConstants.addMethodName, Object.class);

            return DataHelper.addResultResponse(
                    this.getClass().getDeclaredFields(), dataDaoClass, addMethod, this, dataEntityClass, value);
        } else {
            Method findByIdMethod = dataDaoClass.getMethod(SWConstants.findByIdMethodName, Object.class);

            Method updateMethod = dataDaoClass.getMethod(SWConstants.addMethodName, Object.class);

            return DataHelper.updateResultResponse(
                    this.getClass().getDeclaredFields(),
                    dataDaoClass,
                    findByIdMethod,
                    updateMethod,
                    this,
                    id,
                    dataEntityClass,
                    value);
        }
    }
}
