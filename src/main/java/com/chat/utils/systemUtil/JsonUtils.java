package com.chat.utils.systemUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.chat.common.result.ResultResponse;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletResponse;

/**
 * @Description: 自定义响应结构, 转换类
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     *  clazz 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }


    /**
     * 判断一个字符串是不是json格式
     *
     * @param jsonString
     * @return
     */
    public static boolean isJson(String jsonString) {
        try {
            if (StringUtils.isEmpty(jsonString)) {
                return false;
            }
            JSONObject.parse(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //返回数据
    public static void write(ServletResponse response, ResultResponse result)
            throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(result));
            out.flush();
            out.close();
        } catch (Exception e) {

        }
    }



    /**
     * 将json字符串转换成map对象
     *
     * @param json
     * @return
     */
    public static SortedMap<String, Object> jsonToSortMap(String json) {
        JSONObject obj = (JSONObject) JSONObject.parse(json);
        return jsonToSortedMap(obj);
    }


    /**
     * 将JSONObject转换成map对象
     *
     * @param
     * @return
     */
    public static Map<String, Object> jsonToMap(JSONObject obj) {
        Set<?> set = obj.keySet();
        Map<String, Object> map = new HashMap<String, Object>(set.size());
        for (Object key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof JSONArray) {
                map.put(key.toString(),jsonToList((JSONArray) value));
            } else if (value instanceof JSONObject) {
                map.put(key.toString(),jsonToMap((JSONObject) value));
            } else {
                map.put(key.toString(),obj.get(key));
            }

        }
        return map;
    }

    /**
     * 将JSONArray对象转换成list集合
     *
     * @param jsonArr
     * @return
     */
    public static List<Object> jsonToList(JSONArray jsonArr) {
        List<Object> list = new DiyArrayList<Object>();
        for (Object obj : jsonArr) {
            if (obj instanceof JSONArray) {
                list.add(jsonToList((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                list.add(jsonToMap((JSONObject) obj));
            } else {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 将JSONObject转换成map对象
     *
     * @param
     * @return
     */
    public static SortedMap<String, Object> jsonToSortedMap(JSONObject obj) {
        SortedMap<String, Object> map = new TreeMap();
        for (Object key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof JSONArray) {
                map.put(key.toString(),jsonToList((JSONArray) value));
            } else if (value instanceof JSONObject) {
                map.put(key.toString(),jsonToSortedMap((JSONObject) value));
            } else {
                map.put(key.toString(),obj.get(key));
            }

        }
        return map;
    }


}
