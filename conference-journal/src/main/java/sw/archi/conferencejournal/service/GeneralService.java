package sw.archi.conferencejournal.service;

import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Method;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.commonutils.helper.DataHelper;
import sw.archi.conferencejournal.dao.ConferenceCfpRepository;
import sw.archi.conferencejournal.dao.ConferenceRepository;
import sw.archi.conferencejournal.dao.FollowedConferenceRepository;
import sw.archi.conferencejournal.dao.FollowedJournalRepository;
import sw.archi.conferencejournal.dao.GroupRepository;
import sw.archi.conferencejournal.dao.GroupUserRepository;
import sw.archi.conferencejournal.dao.JournalCfpRepository;
import sw.archi.conferencejournal.dao.JournalIssueRepository;
import sw.archi.conferencejournal.dao.JournalRepository;
import sw.archi.conferencejournal.dao.UserFollowersRepository;

@Service
public class GeneralService {

    @Autowired
    private ConferenceCfpRepository conferenceCfpRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private FollowedConferenceRepository followedConferenceRepository;

    @Autowired
    private FollowedJournalRepository followedJournalRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Autowired
    private JournalCfpRepository journalCfpRepository;

    @Autowired
    private JournalIssueRepository journalIssueRepository;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserFollowersRepository userFollowersRepository;

    public JSONObject getDataById(String tableName, int id) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        SWConstants.confjourModulePackageName,
                        SWConstants.daoClassPackageName,
                        tableName,
                        SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Method findByIdMethod = DataHelper.getTargetMethod(dataDaoClass, SWConstants.findByIdMethodName);

        return DataHelper.getResultResponse(
                this.getClass().getDeclaredFields(), dataDaoClass, findByIdMethod, this, id);
    }

    public JSONObject getData(String tableName) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        SWConstants.confjourModulePackageName,
                        SWConstants.daoClassPackageName,
                        tableName,
                        SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Method findAllMethod = DataHelper.getTargetMethod(dataDaoClass, SWConstants.findAllMethodName);

        return DataHelper.getResultResponse(
                this.getClass().getDeclaredFields(), dataDaoClass, findAllMethod, this, null);
    }

    public JSONObject deleteData(String tableName, int id) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        SWConstants.confjourModulePackageName,
                        SWConstants.daoClassPackageName,
                        tableName,
                        SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Method findByIdMethod = DataHelper.getTargetMethod(dataDaoClass, SWConstants.findByIdMethodName);

        Method deleteByIdMethod = DataHelper.getTargetMethod(dataDaoClass, SWConstants.deleteByIdMethodName);

        return DataHelper.deleteResultResponse(
                this.getClass().getDeclaredFields(), dataDaoClass, findByIdMethod, deleteByIdMethod, this, id);
    }

    public JSONObject addAndUpdateData(String tableName, Integer id, JSONObject value) throws Exception {
        Class<?> dataDaoClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        SWConstants.confjourModulePackageName,
                        SWConstants.daoClassPackageName,
                        tableName,
                        SWConstants.daoClassSuffix),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        Class<?> dataEntityClass = Class.forName(
                DataHelper.generateClassName(
                        SWConstants.projectPackageName,
                        SWConstants.confjourModulePackageName,
                        SWConstants.entityClassPackageName,
                        tableName),
                true,
                Thread.currentThread().getContextClassLoader().getParent());

        if (Objects.equals(id, null)) {
            Method addMethod = DataHelper.getTargetMethod(dataDaoClass, SWConstants.addMethodName);

            return DataHelper.addResultResponse(
                    this.getClass().getDeclaredFields(), dataDaoClass, addMethod, this, dataEntityClass, value);
        } else {
            Method findByIdMethod = DataHelper.getTargetMethod(dataDaoClass, SWConstants.findByIdMethodName);

            Method updateMethod = DataHelper.getTargetMethod(dataDaoClass, SWConstants.updateMethodName);

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
