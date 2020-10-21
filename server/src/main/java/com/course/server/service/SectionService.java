package com.course.server.service;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.SectionMapper;
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
public class SectionService {

    @Resource
    private SectionMapper sectionMapper;

    /*
     * @title : 查询列表
     */
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> sectionDtos = new ArrayList<>();
        for (int i = 0; i < sectionList.size(); i++) {
            Section section = sectionList.get(i);
            SectionDto sectionDto = new SectionDto();
            BeanUtils.copyProperties(section,sectionDto);
            sectionDtos.add(sectionDto);
        }
        pageDto.setList(sectionDtos);
    }
    /*
     * @title : 有id时修改，没有时新增
     */
    public void save(SectionDto sectionDto){
        Section section = CopyUtil.copy(sectionDto, Section.class);
        if(StringUtils.isEmpty(section.getId())){
            insert(section);
        }else{
            update(section);
        }
    }

    /*
     * @title : 新增
     */
    private void insert(Section section){
        section.setId(UuidUtil.getShortUuid());
        sectionMapper.insert(section);
    }
    /*
     * @title : 更新
     */
    private void update(Section section){
        sectionMapper.updateByPrimaryKey(section);
    }
    /*
     * @title : 删除
     */
    public void delete(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }
}
