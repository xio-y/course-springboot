package com.edu.hbpu.course.service;

import com.edu.hbpu.course.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-19
 */
public interface CourseService extends IService<Course> {
    Course getById(int cookid);

    void addhasnums(int courseid);

    void reducehasnums(int courseid);

}