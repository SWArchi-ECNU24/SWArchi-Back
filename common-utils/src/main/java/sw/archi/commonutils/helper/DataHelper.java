package sw.archi.commonutils.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import sw.archi.commonutils.constants.SWConstants;

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
                Optional<?> getRes = Objects.equals(param, null)
                        ? (Optional<?>) getMethod.invoke(field.get(targetObject))
                        : (Optional<?>) getMethod.invoke(field.get(targetObject), param);

                if (getRes.isEmpty()) {
                    return ResponseHelper.constructResponse(false, ResponseHelper.requestErrorCode, null);
                } else {
                    return ResponseHelper.constructResponse(true, null, getRes.get());
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

                    return ResponseHelper.constructSuccessResponse(getRes.get());
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
                Optional<?> addRes = (Optional<?>) addMethod.invoke(field.get(targetObject), entity);

                return ResponseHelper.constructSuccessResponse(addRes.get());
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
                    Optional<?> updateRes = (Optional<?>) updateMethod.invoke(field.get(targetObject), entity);

                    return ResponseHelper.constructSuccessResponse(updateRes.get());
                }
            }
        }

        return ResponseHelper.constructResponse(false, ResponseHelper.requestErrorCode, null);
    }
}
