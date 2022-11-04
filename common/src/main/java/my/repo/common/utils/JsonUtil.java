package my.repo.common.utils;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import my.repo.common.exception.JsonException;

@Slf4j
public class JsonUtil {


    /**
     * @author gengzhihao
     * @date 2022/11/4 18:36
     * @description 实体转json
    **/
    public static <T> String obj2Str(T object){
        String json = null;
        try {
            json = JSONObject.toJSONString(object);
        } catch (Exception e) {
            log.error("json转换失败",e);
            throw new JsonException(e.getMessage());
        }
        return json;
    }


    /**
     * @author gengzhihao
     * @date 2022/11/4 18:39
     * @description json转实体
    **/
    public static <T> T str2Obj(String string,Class<T> clazz){
        T object = null;
        try {
            object = JSONObject.parseObject(string,clazz);
        } catch (Exception e) {
            log.error("json解析失败",e);
            throw new JsonException(e.getMessage());
        }
        return object;
    }
}
