package com.edu.hbpu.course.serviceImpl;

import com.edu.hbpu.course.entity.Course;
import com.edu.hbpu.course.mapper.CourseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.hbpu.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-19
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public Course getById(int courseid) {
        return courseMapper.getById(courseid);
    }

    @Override
    public void addhasnums(int courseid) {
        courseMapper.addhasnums(courseid);
    }

    @Override
    public void reducehasnums(int courseid) {
        courseMapper.reducehasnums(courseid);
    }
}

