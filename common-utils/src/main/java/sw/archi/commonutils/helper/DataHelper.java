package sw.archi.commonutils.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.commonutils.struct.KeyTriple;

public class DataHelper {
    /**
     *
     * @param toBeJudged      Value to be judged.
     * @param referencedValue Value of reference, aka value to be compared to.
     * @return If toBeJudged is null, then return true; otherwise, return toBeJudged
     *         == referencedValue.
     */
    public static Boolean softEquals(Integer toBeJudged, int referencedValue) {
        return Objects.equals(toBeJudged, null) ? true : Objects.equals(referencedValue, toBeJudged);
    }

    public static Boolean softEquals(Double toBeJudged, double referencedValue) {
        return Objects.equals(toBeJudged, null) ? true : Objects.equals(referencedValue, toBeJudged);
    }

    public static Boolean softEquals(Character toBeJudged, char referencedValue) {
        return Objects.equals(toBeJudged, null) ? true : Objects.equals(referencedValue, toBeJudged);
    }

    public static Boolean softEquals(String toBeJudged, String referencedValue) {
        try {
            return (Objects.equals(toBeJudged, null) || Objects.equals(toBeJudged, ""))
                    ? true
                    : Objects.equals(referencedValue, URLDecoder.decode(toBeJudged, SWConstants.UTF8));
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    public static Boolean softEquals(Date toBeJudged, Date referencedValue) {
        return Objects.equals(toBeJudged, null) ? true : Objects.equals(referencedValue, toBeJudged);
    }

    /**
     *
     * @param child  Value to be judged.
     * @param parent Value of reference.
     * @return If child is null or empty string, then return true; otherwise, return
     *         whether parent includes child.
     */
    public static Boolean softIncludes(String child, String parent) {
        child = (Objects.equals(child, null) || Objects.equals(child, "")) ? "" : child;
        try {
            return !Objects.equals(parent.indexOf(URLDecoder.decode(child, SWConstants.UTF8)), -1);
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    public static Date stringToDate(String javaDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return sdf.parse(URLDecoder.decode(javaDate, SWConstants.UTF8));
        } catch (Exception e) {
            return null;
        }
    }

    public static String generateClassName(String... names) {
        return Arrays.asList(names).stream().collect(Collectors.joining("."));
    }

    public static Method getTargetMethod(Class<?> targetClass, String targetMethodName) {
        Method targetMethod = null;

        for (Method classMethods : targetClass.getMethods()) {
            if (Objects.equals(classMethods.getName(), targetMethodName)) {
                targetMethod = classMethods;
            }
        }

        return targetMethod;
    }

    /**
     * @param fieldRange Field objects to filter, i.e. @Autowired xRepository, @Autowired yRepository.
     * @param targetClass The class to whom the method to be invoked belongs.
     * @param getMethod The method to be invoked.
     * @param targetObject Namespace of whatever calls this function, i.e. GeneralService.
     * @param param The single Param of the method to be invoked, which can be null or i.e. int id.
     */
    public static JSONObject getResultResponse(
            Field[] fieldRange, Class<?> targetClass, Method getMethod, Object targetObject, Object param)
            throws Exception {
        for (Field field : fieldRange) {
            if (Objects.equals(field.getType().getName(), targetClass.getName())) {

                if (Objects.equals(param, null)) {
                    return ResponseHelper.constructSuccessListResponse(getMethod.invoke(field.get(targetObject)));
                } else {
                    Optional<?> getRes = (Optional<?>) getMethod.invoke(field.get(targetObject), param);
                    if (getRes.isEmpty()) {
                        return ResponseHelper.constructResponse(false, ResponseHelper.requestErrorCode, null);
                    } else {
                        return ResponseHelper.constructResponse(true, null, getRes.get());
                    }
                }
            }
        }

        return ResponseHelper.constructResponse(false, ResponseHelper.requestErrorCode, null);
    }

    /**
     * @param fieldRange Field objects to filter, i.e. @Autowired xRepository, @Autowired yRepository.
     * @param targetClass The class to whom the methods to be invoked belongs.
     * @param getMethod The get method to be invoked.
     * @param deleteMethod The delete method to be invoked.
     * @param targetObject Namespace of whatever calls this function, i.e. GeneralService.
     * @param param The single Param of the get method to be invoked, i.e. int id.
     */
    public static JSONObject deleteResultResponse(
            Field[] fieldRange,
            Class<?> targetClass,
            Method getMethod,
            Method deleteMethod,
            Object targetObject,
            Object param)
            throws Exception {
        for (Field field : fieldRange) {
            if (Objects.equals(field.getType().getName(), targetClass.getName())) {
                Optional<?> getRes = (Optional<?>) getMethod.invoke(field.get(targetObject), param);

                if (getRes.isEmpty()) {
                    return ResponseHelper.constructResponse(false, ResponseHelper.requestErrorCode, null);
                } else {
                    deleteMethod.invoke(field.get(targetObject), param);

                    return ResponseHelper.constructResponse(true, null, getRes.get());
                }
            }
        }

        return ResponseHelper.constructResponse(false, ResponseHelper.requestErrorCode, null);
    }

    /**
     * @param fieldRange Field objects to filter, i.e. @Autowired xRepository, @Autowired yRepository.
     * @param targetClass The class to whom the method to be invoked belongs.
     * @param addMethod The add method to be invoked.
     * @param targetObject Namespace of whatever calls this function, i.e. GeneralService.
     * @param entityClass The class of the entity, or object, to be created.
     * @param value Value of the entity, or object, to be created.
     */
    public static JSONObject addResultResponse(
            Field[] fieldRange,
            Class<?> targetClass,
            Method addMethod,
            Object targetObject,
            Class<?> entityClass,
            JSONObject value)
            throws Exception {
        for (Field field : fieldRange) {
            if (Objects.equals(field.getType().getName(), targetClass.getName())) {
                Object entity = JSON.toJavaObject(value, entityClass);

                return ResponseHelper.constructResponse(true, null, addMethod.invoke(field.get(targetObject), entity));
            }
        }

        return ResponseHelper.constructResponse(false, ResponseHelper.requestErrorCode, null);
    }

    /**
     * @param fieldRange Field objects to filter, i.e. @Autowired xRepository, @Autowired yRepository.
     * @param targetClass The class to whom the methods to be invoked belongs.
     * @param getMethod The get method to be invoked.
     * @param updateMethod The update method to be invoked.
     * @param targetObject Namespace of whatever calls this function, i.e. GeneralService.
     * @param param The single Param of the get method to be invoked, i.e. int id.
     * @param entityClass The class of the entity, or object, to be updated.
     * @param value Value of the entity, or object, to be updated.
     */
    public static JSONObject updateResultResponse(
            Field[] fieldRange,
            Class<?> targetClass,
            Method getMethod,
            Method updateMethod,
            Object targetObject,
            Object param,
            Class<?> entityClass,
            JSONObject value)
            throws Exception {
        for (Field field : fieldRange) {
            if (Objects.equals(field.getType().getName(), targetClass.getName())) {
                Optional<?> getRes = (Optional<?>) getMethod.invoke(field.get(targetObject), param);

                if (getRes.isEmpty()) {
                    return ResponseHelper.constructResponse(false, ResponseHelper.requestErrorCode, null);
                } else {
                    Object entity = JSON.toJavaObject(value, entityClass);

                    return ResponseHelper.constructResponse(
                            true, null, updateMethod.invoke(field.get(targetObject), entity));
                }
            }
        }

        return ResponseHelper.constructResponse(false, ResponseHelper.requestErrorCode, null);
    }

    public static JSONArray filterEqualKeys(JSONArray origin, List<KeyTriple> keys) {
        return JSON.parseArray(JSON.toJSONString(origin.toJavaList(JSONObject.class).stream()
                .filter(singleton -> {
                    if (keys.size() == 0) {
                        return false;
                    }

                    boolean flag = true;
                    for (KeyTriple key : keys) {
                        flag &= Objects.equals(
                                singleton.getObject(key.getKeyName(), key.getClassName()),
                                key.getClassName().cast(key.getKeyValue()));
                    }
                    return flag;
                })
                .collect(Collectors.toList())));
    }

    public static JSONArray filterKeyWords(JSONArray origin, Map<String, String> keys) {
        return JSON.parseArray(JSON.toJSONString(origin.toJavaList(JSONObject.class).stream()
                .filter(singleton -> {
                    boolean flag = true;
                    for (Map.Entry<String, String> key : keys.entrySet()) {
                        flag &= DataHelper.softIncludes(key.getValue(), singleton.getString(key.getKey()));
                    }
                    return flag;
                })
                .collect(Collectors.toList())));
    }

    public static JSONArray filterJSONObjectKeys(JSONObject object, JSONArray origin, Map<String, Class<?>> keyNames) {
        List<KeyTriple> keys = new ArrayList<>();

        keyNames.forEach((keyName, className) -> {
            KeyTriple singleton = new KeyTriple(keyName, className, object.getObject(keyName, className));
            keys.add(singleton);
        });

        return filterEqualKeys(origin, keys);
    }

    public static JSONArray filterJSONObjectKeywords(JSONArray origin, JSONObject queryObject) {
        Map<String, String> keys = new HashMap<>();

        for (Map.Entry<String, Object> singleton : queryObject.entrySet()) {
            keys.put(singleton.getKey(), String.valueOf(singleton.getValue()));
        }

        return filterKeyWords(origin, keys);
    }

    public static JSONArray filterData(JSONArray origin, JSONObject queryObject, Map<String, Class<?>> keyTypes) {
        List<KeyTriple> keys = new ArrayList<>();

        for (Map.Entry<String, Object> singleton : queryObject.entrySet()) {
            KeyTriple obj = new KeyTriple(
                    singleton.getKey(),
                    keyTypes.get(singleton.getKey()),
                    keyTypes.get(singleton.getKey()).cast(singleton.getValue()));
            keys.add(obj);
        }

        return JSON.parseArray(JSON.toJSONString(origin.toJavaList(JSONObject.class).stream()
                .filter(singleton -> {
                    boolean flag = true;
                    for (KeyTriple key : keys) {
                        flag &= Objects.equals(key.getClassName(), String.class)
                                ? DataHelper.softIncludes(
                                        String.valueOf(key.getKeyValue()), singleton.getString(key.getKeyName()))
                                : Objects.equals(key.getClassName().cast(key.getKeyValue()), null)
                                        || Objects.equals(
                                                key.getClassName().cast(key.getKeyValue()),
                                                singleton.getObject(key.getKeyName(), key.getClassName()));
                    }
                    return flag;
                })
                .collect(Collectors.toList())));
    }
}
