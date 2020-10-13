package com.course.business.controller.admin;

import com.course.server.domain.Chapter;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import org.apache.ibatis.annotations.Param;
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

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        chapterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDto save(@PathVariable("id") String id) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        chapterService.delete(id);
        return responseDto;
    }
}
