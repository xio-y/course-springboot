package com.edu.hbpu.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.hbpu.course.entity.Chatmsg;
import com.edu.hbpu.course.entity.Message;
import com.edu.hbpu.course.entity.User;
import com.edu.hbpu.course.mapper.UserMapper;
import com.edu.hbpu.course.service.ChatmsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-03-30
 */
@RestController
@RequestMapping("/cookbook/chatmsg")
public class ChatmsgController {
    @Autowired
    ChatmsgService chatmsgService;
    @GetMapping("/list")
    IPage<Chatmsg> list(Page<Chatmsg> page,Chatmsg chatmsg){
        QueryWrapper<Chatmsg> wrapper=new QueryWrapper<>();
        wrapper.eq("fromUser",chatmsg.getFromUser())
                .eq("toUser",chatmsg.getToUser())
                .or()
                .eq("fromUser",chatmsg.getToUser())
                .eq("toUser",chatmsg.getFromUser());
        return chatmsgService.page(page,wrapper);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/addRecent")
    void addRecent(Long sendId,Long receiveId){
        Message message=new Message();
        message.setSendId(sendId);
        message.setReceiveId(receiveId);
        Query query=Query.query(Criteria.where("sendId").is(sendId).and("receiveId").is(receiveId));
        if(mongoTemplate.exists(query,Message.class)) return;
        mongoTemplate.save(message);
    }

    @Autowired
    UserMapper userMapper;
    @GetMapping("/getMessageList")
    public List<User> getMessageList(Long receiveId) {
        Query query=Query.query(Criteria.where("receiveId").is(receiveId));
        List<Message> list=mongoTemplate.find(query,Message.class);
        List<User> userList=new ArrayList<>();
        for(Message message:list){
            QueryWrapper<User> wrapper=new QueryWrapper<>();
            wrapper.select("uid,username,image").eq("uid",message.getSendId());
            User user=userMapper.selectOne(wrapper);
            userList.add(user);
        }
        return userList;
    }

}

