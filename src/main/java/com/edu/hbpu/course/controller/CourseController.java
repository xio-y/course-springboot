package com.edu.hbpu.course.controller;


import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.hbpu.course.entity.Course;
import com.edu.hbpu.course.entity.QueryVo;
import com.edu.hbpu.course.entity.User;
import com.edu.hbpu.course.mapper.UserMapper;
import com.edu.hbpu.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/cookbook/cook")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/getPageCook")
    public IPage<Course> getPage(Page<Course> page){
        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("courseId","pictures","course","time","teacher").orderByDesc("courseId");
        return courseService.page(page,queryWrapper);
    }
    @GetMapping("/getByUid")
    public IPage<Course> getByUid(Page<Course> page,Integer uid){
        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("courseId","pictures","course","time")
                .eq("uid",uid)
                .orderByDesc("courseId");
        return courseService.page(page,queryWrapper);
    }

    @GetMapping("/getById")
    public Course getById(int courseId){
        return courseService.getById(courseId);
    }

    @Value("${web.uploadPath}")
    String uploadPath;
    @PostMapping("/add")
    String add(Course course, MultipartFile file){
        if(file.isEmpty()) return "failed";
        String fileName=file.getOriginalFilename();
        String newFileName= UUID.fastUUID().toString(true)
                +"."+ FileNameUtil.extName(fileName);
        File filePath=new File(uploadPath,newFileName);
        try{
            file.transferTo(filePath);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        course.setPictures(newFileName);
        course.setTime(LocalDateTime.now());
        course.setHasnums(0);
        courseService.save(course);
        return "success";
    }

    @GetMapping("getByKindId")
    List<Course> getByKindId(int kindId){
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("courseId","pictures","course","time")
                .eq("kindId",kindId).orderByDesc("courseId");
        return courseService.list(queryWrapper);
    }

    @PostMapping("/getPageCookByTitle")
    IPage<Course> getPageNewsByTitle(@RequestBody QueryVo vo){
        Page<Course> page=new Page<>(vo.getCurrent(),10);
        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("courseId","pictures","course","time")
                .like("course",vo.getTitle()).orderByDesc("courseId");
        return courseService.page(page,queryWrapper);
    }

    @Autowired
    UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/getStudentListByCourseId")
    public List<User> getStudentListByCourseId(Long courseId) {
        SetOperations setOperations=redisTemplate.opsForSet();
        Set members = setOperations.members(courseId);
        List<User> userList=new ArrayList<>();
        for(Object id:members){
            int getint=((Long) id).intValue();
            QueryWrapper<User> wrapper=new QueryWrapper<>();
            wrapper.select("uid,username,image").eq("uid",getint);
            User user=userMapper.selectOne(wrapper);
            userList.add(user);
        }
        return userList;
    }
}


