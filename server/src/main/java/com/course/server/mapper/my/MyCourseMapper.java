package com.course.server.mapper.my;

import org.apache.ibatis.annotations.Param;

/**
 * @author: xyl
 * @time: 2020/10/28 9:38
 * @description:
 */
public interface MyCourseMapper {

    int updateTime(@Param("courseId") String courseId);
}
