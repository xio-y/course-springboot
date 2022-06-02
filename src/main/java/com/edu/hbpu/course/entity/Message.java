package com.edu.hbpu.course.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("message")
public class Message {
    private Long sendId;
    private Long receiveId;
}
