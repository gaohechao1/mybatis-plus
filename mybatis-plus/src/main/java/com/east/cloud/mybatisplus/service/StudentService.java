package com.east.cloud.mybatisplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.east.cloud.mybatisplus.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sa
 * @since 2020-06-09
 */
public interface StudentService extends IService<Student> {
//    IPage<Student> getStudents(Map<String,Object> map);
//    List<Student> getStudents1(Map<String,Object> map);
    PageInfo getStudents2(Map<String,Object> map);

}
