package com.course.server.service;

import com.course.server.domain.${Domain};
import com.course.server.domain.${Domain}Example;
import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.${Domain}Mapper;
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
public class ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    /*
     * @title : 查询列表
     */
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotal(pageInfo.getTotal());
        List<${Domain}Dto> ${domain}Dtos = new ArrayList<>();
        for (int i = 0; i < ${domain}List.size(); i++) {
            ${Domain} ${domain} = ${domain}List.get(i);
            ${Domain}Dto ${domain}Dto = new ${Domain}Dto();
            BeanUtils.copyProperties(${domain},${domain}Dto);
            ${domain}Dtos.add(${domain}Dto);
        }
        pageDto.setList(${domain}Dtos);
    }
    /*
     * @title : 有id时修改，没有时新增
     */
    public void save(${Domain}Dto ${domain}Dto){
        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);
        if(StringUtils.isEmpty(${domain}.getId())){
            insert(${domain});
        }else{
            update(${domain});
        }
    }

    /*
     * @title : 新增
     */
    private void insert(${Domain} ${domain}){
        ${domain}.setId(UuidUtil.getShortUuid());
        ${domain}Mapper.insert(${domain});
    }
    /*
     * @title : 更新
     */
    private void update(${Domain} ${domain}){
        ${domain}Mapper.updateByPrimaryKey(${domain});
    }
    /*
     * @title : 删除
     */
    public void delete(String id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }
}
