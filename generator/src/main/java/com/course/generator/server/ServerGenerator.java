package com.course.generator.server;

import com.course.generator.util.FreemarkerUtil;

import java.util.HashMap;

/**
 * @author: xyl
 * @time: 2020/10/21 10:42
 * @description:
 */
public class ServerGenerator {
    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";
    static String toControllerPath = "business\\src\\main\\java\\com\\course\\business\\controller\\admin\\";

    public static void main(String[] args) throws Exception{

        String Domain = "Section";
        String domain = "section";
        HashMap<String, Object> map = new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);

        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath+Domain+"Service.java",map);
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);
    }
}
