package com.course.generator.server;

import com.course.generator.util.FreemarkerUtil;

import java.util.HashMap;

/**
 * @author: xyl
 * @time: 2020/10/21 10:42
 * @description:
 */
public class ServerGenerator {
    static String MODULE = "business";
    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";
    static String toControllerPath = MODULE+"\\src\\main\\java\\com\\course\\"+MODULE+"\\controller\\admin\\";

    public static void main(String[] args) throws Exception{

        String Domain = "Section";
        String domain = "section";
        String tableNameCn = "小节";
        String module = MODULE;
        HashMap<String, Object> map = new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);
        map.put("tableNameCn", tableNameCn);
        map.put("module", module);

        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath+Domain+"Service.java",map);
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);
    }
}
