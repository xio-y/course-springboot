package com.edu.hbpu.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author hbpu
 * @since 2022-05-19
 */
@Getter
@Setter
@TableName("course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient String kindName;
    private transient String userName;
    private transient String image;

    @TableId(value = "courseId", type = IdType.AUTO)
    private Integer courseId;

    @TableField("kindId")
    private Integer kindId;

    @TableField("course")
    private String course;

    @TableField("credit")
    private Integer credit;

    @TableField("time")
    private LocalDateTime time;

    @TableField("pictures")
    private String pictures;

    @TableField("content")
    private String content;

    @TableField("uid")
    private Integer uid;

    @TableField("teacher")
    private String teacher;

    @TableField("type")
    private Integer type;

    @TableField("hasnums")
    private Integer hasnums;

    @TableField("totalnums")
    private Integer totalnums;

    @TableField("deadline")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate deadline;


}
