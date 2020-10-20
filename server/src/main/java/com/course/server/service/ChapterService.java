package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xyl
 * @time: 2020/9/21 20:00
 * @description:
 */
@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    /*
     * @title : 查询列表
     * @date : 2020/10/20 21:08
     * @param: pageDto
     * @return void
     * @throws
     * @author xyl
     * @description
     *
     */
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto> chapterDtos = new ArrayList<>();
        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            chapterDtos.add(chapterDto);
        }
        pageDto.setList(chapterDtos);
    }
    /*
     * @title : 有id时修改，没有时新增
     * @date : 2020/10/20 21:08
     * @param: chapterDto
     * @return void
     * @throws
     * @author xyl
     * @description
     *
     */
    public void save(ChapterDto chapterDto){
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        if(StringUtils.isEmpty(chapter.getId())){
            insert(chapter);
        }else{
            update(chapter);
        }
    }

    /*
     * @title : 新增
     * @date : 2020/10/20 21:07
     * @param: chapter
     * @return void
     * @throws
     * @author xyl
     * @description
     *
     */
    private void insert(Chapter chapter){
        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);
    }
    /*
     * @title : 更新
     * @date : 2020/10/20 21:07
     * @param: chapter
     * @return void
     * @throws
     * @author xyl
     * @description
     *
     */
    private void update(Chapter chapter){
        chapterMapper.updateByPrimaryKey(chapter);
    }
    /*
     * @title : 删除
     * @date : 2020/10/20 21:07
     * @param: id
     * @return void
     * @throws
     * @author xyl
     * @description
     *
     */
    public void delete(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }
}
