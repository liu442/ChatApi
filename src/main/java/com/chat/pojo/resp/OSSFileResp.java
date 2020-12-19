package com.chat.pojo.resp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author
 * @date 2020/4/10
 */
@Setter
@Getter
public class OSSFileResp {
    //原来的文件名
    private String fileName;
    //上传到oss 后的文件名
    private String ossFileName;
}
