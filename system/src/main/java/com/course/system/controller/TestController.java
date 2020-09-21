package com.course.system.controller;

import com.course.system.domain.Test;
import com.course.system.mapper.TestMapper;
import com.course.system.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: xyl
 * @time: 2020/9/21 11:14
 * @description:
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("test")
    public List<Test> test1(){
        return testService.test();
    }
}
