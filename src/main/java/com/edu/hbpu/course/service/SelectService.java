package com.edu.hbpu.course.service;

import com.edu.hbpu.course.entity.Course;

import java.util.List;

public interface SelectService {
    List<Course> getCourseByUid(Long userId);
    boolean clickSelect(Long userId,Long courseId);
    boolean cancelSelect(Long userId, Long courseId);
    boolean ifSelectThis(Long userId,Long courseId);
}
