package sw.archi.intermediator.helper;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.commonutils.helper.DataHelper;

public class FilterHelper {

    public static Map<String, Class<?>> generateKeyNames(String moduleName, String tableName) {

        switch (moduleName) {
            case SWConstants.authModuleName:
                return generateAuthKeyNames(tableName);
            case SWConstants.confjourModuleName:
                return generateConfjourKeyNames(tableName);
            default:
                return new HashMap<String, Class<?>>();
        }
    }

    public static Map<String, Class<?>> generateAuthKeyNames(String tableName) {
        Map<String, Class<?>> keyNames = new HashMap<>();

        switch (tableName) {
            case SWConstants.userTableName:
                keyNames.put(SWConstants.userEmail, String.class);
                break;
            default:
                break;
        }

        return keyNames;
    }

    public static Map<String, Class<?>> generateConfjourKeyNames(String tableName) {
        Map<String, Class<?>> keyNames = new HashMap<>();

        switch (tableName) {
            case SWConstants.conferenceTableName:
                keyNames.put(SWConstants.conferenceName, String.class);
                keyNames.put(SWConstants.conferenceUrl, String.class);
                break;
            case SWConstants.journalTableName:
                keyNames.put(SWConstants.journalName, String.class);
                keyNames.put(SWConstants.journalName, String.class);
                break;
            case SWConstants.groupTableName:
                keyNames.put(SWConstants.groupName, String.class);
                break;
            default:
                break;
        }

        return keyNames;
    }

    public static int getTableId(JSONObject obj) {
        return (int) obj.entrySet().stream()
                .filter(singleton -> DataHelper.softIncludes(SWConstants.idSuffix, singleton.getKey())
                        && Objects.equals(singleton.getValue().getClass(), Integer.class))
                .collect(Collectors.toList())
                .get(0)
                .getValue();
    }
}
