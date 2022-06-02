package com.edu.hbpu.course.serviceImpl;

import com.edu.hbpu.course.entity.Log;
import com.edu.hbpu.course.mapper.LogMapper;
import com.edu.hbpu.course.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-03-30
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
