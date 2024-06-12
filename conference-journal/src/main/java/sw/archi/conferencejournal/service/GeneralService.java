package sw.archi.conferencejournal.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.commonutils.helper.GeneralServiceHelper;
import sw.archi.conferencejournal.dao.ConferenceCfpRepository;
import sw.archi.conferencejournal.dao.ConferenceGroupRepository;
import sw.archi.conferencejournal.dao.ConferenceRepository;
import sw.archi.conferencejournal.dao.FollowedConferenceRepository;
import sw.archi.conferencejournal.dao.FollowedJournalRepository;
import sw.archi.conferencejournal.dao.JournalCfpRepository;
import sw.archi.conferencejournal.dao.JournalIssueRepository;
import sw.archi.conferencejournal.dao.JournalRepository;

@Service
public class GeneralService {

    @Autowired
    public ConferenceCfpRepository conferenceCfpRepository;

    @Autowired
    public ConferenceRepository conferenceRepository;

    @Autowired
    public FollowedConferenceRepository followedConferenceRepository;

    @Autowired
    public FollowedJournalRepository followedJournalRepository;

    @Autowired
    public ConferenceGroupRepository conferenceGroupRepository;

    @Autowired
    public JournalCfpRepository journalCfpRepository;

    @Autowired
    public JournalIssueRepository journalIssueRepository;

    @Autowired
    public JournalRepository journalRepository;

    public JSONObject getDataById(String tableName, int id) throws Exception {
        return GeneralServiceHelper.getDataById(tableName, id, SWConstants.confjourModulePackageName, this);
    }

    public JSONObject getData(String tableName) throws Exception {
        return GeneralServiceHelper.getData(tableName, SWConstants.confjourModulePackageName, this);
    }

    public JSONObject deleteData(String tableName, int id) throws Exception {
        return GeneralServiceHelper.deleteData(tableName, id, SWConstants.confjourModulePackageName, this);
    }

    public JSONObject addAndUpdateData(String tableName, Integer id, JSONObject value) throws Exception {
        return GeneralServiceHelper.addAndUpdateData(tableName, id, value, SWConstants.confjourModulePackageName, this);
    }
}
