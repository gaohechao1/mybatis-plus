package com.east.cloud.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.east.cloud.mybatisplus.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sa
 * @since 2020-06-09
 */
//@Component
public interface StudentMapper extends BaseMapper<Student> {
//    List<Student> getStudents(Page<Student> p, @Param(Constants.WRAPPER) QueryWrapper wrapper);
//    List<Student> getStudents(Page<Student> p, @Param("age")String age,@Param("name")String name);
//    List<Student> getStudents1(Map<String,Object> map);
    List<Student> getStudents2(Map<String,Object> map);
}
