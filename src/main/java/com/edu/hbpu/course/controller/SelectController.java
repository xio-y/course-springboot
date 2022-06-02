package com.edu.hbpu.course.controller;


import com.edu.hbpu.course.entity.Course;
import com.edu.hbpu.course.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cookbook/select")
public class SelectController {

    @Autowired
    SelectService selectService;

    @GetMapping("/clickSelect")
    public boolean clickSelect(Long userId, Long courseId) {
        return selectService.clickSelect(userId, courseId);
    }

    @GetMapping("/cancelSelect")
    public boolean cancelSelect(Long userId, Long courseId) {
        return selectService.cancelSelect(userId, courseId);
    }

    @GetMapping("/ifSelectThis")
    public boolean ifSelectThis(Long userId, Long courseId) {
        return selectService.ifSelectThis(userId, courseId);
    }

    @GetMapping("/getCourseByUid")
    public List<Course> getCourseByUid(Long userId) {
        return selectService.getCourseByUid(userId);
    }
}