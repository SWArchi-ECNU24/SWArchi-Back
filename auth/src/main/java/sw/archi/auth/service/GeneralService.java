package sw.archi.auth.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sw.archi.auth.dao.UserFollowersRepository;
import sw.archi.auth.dao.UserRepository;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.commonutils.helper.GeneralServiceHelper;

@Service
public class GeneralService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserFollowersRepository userFollowersRepository;

    public JSONObject getDataById(String tableName, int id) throws Exception {
        return GeneralServiceHelper.getDataById(tableName, id, SWConstants.authModulePackageName, this);
    }

    public JSONObject getData(String tableName) throws Exception {
        return GeneralServiceHelper.getData(tableName, SWConstants.authModulePackageName, this);
    }

    public JSONObject deleteData(String tableName, int id) throws Exception {
        return GeneralServiceHelper.deleteData(tableName, id, SWConstants.authModulePackageName, this);
    }

    public JSONObject addAndUpdateData(String tableName, Integer id, JSONObject value) throws Exception {
        return GeneralServiceHelper.addAndUpdateData(tableName, id, value, SWConstants.authModulePackageName, this);
    }
}
