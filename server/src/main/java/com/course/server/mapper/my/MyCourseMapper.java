package com.course.server.mapper.my;

import com.course.server.dto.SortDto;
import org.apache.ibatis.annotations.Param;

/**
 * @author: xyl
 * @time: 2020/10/28 9:38
 * @description:
 */
public interface MyCourseMapper {

    int updateTime(@Param("courseId") String courseId);


    int updateSort(SortDto sortDto);

    int moveSortsBackward(SortDto sortDto);

    int moveSortsForward(SortDto sortDto);
}
