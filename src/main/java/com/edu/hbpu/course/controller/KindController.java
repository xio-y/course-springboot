package com.edu.hbpu.course.controller;


import com.edu.hbpu.course.entity.Kind;
import com.edu.hbpu.course.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/cookbook/kind")
public class KindController{
        @Autowired
        KindService kindService;
        @Autowired
        RedisTemplate redisTemplate;
        @GetMapping("/list")
        List<Kind> list(){
            ListOperations listOperations=redisTemplate.opsForList();
            Long size=listOperations.size("kindList");
            if(size!=null&&size>0){
            List range = listOperations.range("kindList", 0,size-1);
            return range;
            }else {
            List<Kind> list=kindService.list();
            listOperations.rightPushAll("kindList",list);
            return list;
            }
        }

        @GetMapping("/getNumsByKind")
        List<Kind> getNumsByKind(){
            return  kindService.getNumsByKind();
        }
}

