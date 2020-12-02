package com.course.file.controller.admin;

import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.FileService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: xyl
 * @time: 2020/9/21 11:14
 * @description:
 */
@RestController
@RequestMapping("/admin/file")
public class FileController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    public static final String BUSINESS_NAME = "文件";

    @Resource
    private FileService fileService;

    /*
     * @title : 查询
     */
    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        fileService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
    /*
     * @title : 保存
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody FileDto fileDto) {
        //校验
        ValidatorUtil.require(fileDto.getPath(), "相对路径");
        ValidatorUtil.length(fileDto.getPath(),"相对路径",1,100);
        ValidatorUtil.length(fileDto.getName(),"文件名",1,100);
        ValidatorUtil.length(fileDto.getSuffix(),"后缀",1,10);

        ResponseDto<Object> responseDto = new ResponseDto<>();
        fileService.save(fileDto);
        responseDto.setContent(fileDto);
        return responseDto;
    }
    /*
     * @title : 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable("id") String id) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        fileService.delete(id);
        return responseDto;
    }
}
