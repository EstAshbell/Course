package com.course.business.controller.admin;

import com.course.server.domain.Chapter;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.util.ValidatorUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: xyl
 * @time: 2020/9/21 11:14
 * @description:
 */
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    public static final String BUSINESS_NAME = "大章";

    @Resource
    private ChapterService chapterService;

    /*
     * @title : 查询
     * @date : 2020/10/20 21:04
     * @param: pageDto
     * @return com.course.server.dto.ResponseDto
     * @throws
     * @author xyl
     * @description
     *
     */
    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        chapterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
    /*
     * @title : 保存
     * @date : 2020/10/20 21:04
     * @param: chapterDto
     * @return com.course.server.dto.ResponseDto
     * @throws
     * @author xyl
     * @description
     *
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        ValidatorUtil.require(chapterDto.getName(), "名称");
        ValidatorUtil.require(chapterDto.getCourseId(), "课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(), "名称",1,8);

        ResponseDto<Object> responseDto = new ResponseDto<>();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }
    /*
     * @title : 删除
     * @date : 2020/10/20 21:03
     * @param: id
     * @return com.course.server.dto.ResponseDto
     * @throws
     * @author xyl
     * @description
     *
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable("id") String id) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        chapterService.delete(id);
        return responseDto;
    }
}
