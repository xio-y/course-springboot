package com.edu.hbpu.course.mapper;

import com.edu.hbpu.course.entity.Kind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
@Mapper
public interface KindMapper extends BaseMapper<Kind> {
    @Select("SELECT COUNT(n.courseId) as nums,k.content from course n "
            + "LEFT JOIN kind k on n.kindId=k.kindId GROUP BY n.kindId ")
    List<Kind> getNumsByKind();
}
