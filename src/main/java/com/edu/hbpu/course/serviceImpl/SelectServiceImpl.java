package com.edu.hbpu.course.serviceImpl;

import com.edu.hbpu.course.entity.Course;
import com.edu.hbpu.course.service.CourseService;
import com.edu.hbpu.course.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SelectServiceImpl implements SelectService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CourseService courseService;
    public List<Course> getCourseByUid(Long userId) {
        SetOperations setOperations=redisTemplate.opsForSet();
        Set members = setOperations.members(userId);
        System.out.println(members);
        List<Course> list=new ArrayList<>();
        for(Object id:members){
            int getint=((Long) id).intValue();
            list.add(courseService.getById(getint));
        }
        return list;
    }


    public boolean clickSelect(Long userId, Long courseId) {
        SetOperations setOperations=redisTemplate.opsForSet();
        setOperations.add(userId,courseId);
        setOperations.add(courseId,userId);
        courseService.addhasnums(courseId.intValue());
        return true;
    }

    public boolean cancelSelect(Long userId, Long courseId) {
        SetOperations setOperations=redisTemplate.opsForSet();
        setOperations.remove(userId,courseId);
        setOperations.remove(courseId,userId);
        courseService.reducehasnums(courseId.intValue());
        return true;
    }

    public boolean ifSelectThis(Long userId, Long courseId) {
        SetOperations setOperations=redisTemplate.opsForSet();
        if(setOperations.isMember(userId,courseId)==true) return true;
        return false;
    }
}
