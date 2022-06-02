package com.edu.hbpu.course.mapper;

import com.edu.hbpu.course.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbpu
 * @since 2022-05-19
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    @Select("SELECT c.*,k.content AS kindName,u.userName,u.image FROM course c \r\n" +
            "LEFT JOIN kind k ON c.kindId=k.kindId \r\n" +
            "LEFT JOIN user u ON c.uid=u.uid WHERE courseId=#{courseid}")
    Course getById(int courseid);

    @Update("update course set hasnums=hasnums+1 where courseId=#{courseid}")
    void addhasnums(int courseid);

    @Update("update course set hasnums=hasnums-1 where courseId=#{courseid}")
    void reducehasnums(int courseid);

}
