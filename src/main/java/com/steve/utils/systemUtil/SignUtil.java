package com.steve.utils.systemUtil;


import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.SortedMap;

/**
 * @date 2019/9/17
 * 签名类
 */
public class SignUtil {

    /**
     * 与三方tc交互使用 获取参数拼接串  Map<String, Object>
     *
     * @param
     * @return
     */
    public static String getParamByOb(SortedMap<String, Object> smap) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> m : smap.entrySet()) {
            Object o =  m.getValue();
            String value=null;
            if(o!=null){
                value = m.getValue().toString();
            }
            if (value != null && StringUtils.isNotBlank(value)) {
                stringBuffer.append(m.getKey()).append("=").append(value).append("&");
            }
        }
        return stringBuffer.toString();
    }
}
