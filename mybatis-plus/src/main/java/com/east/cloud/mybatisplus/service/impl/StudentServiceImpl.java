package com.east.cloud.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.east.cloud.mybatisplus.entity.Student;
import com.east.cloud.mybatisplus.mapper.StudentMapper;
import com.east.cloud.mybatisplus.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sa
 * @since 2020-06-09
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    //    @Override
//    public IPage<Student> getStudents(Map<String, Object> map) {
//        try {
//            if (!map.containsKey("pageNum") || !map.containsKey("pageSize")) {
//                map.put("pageNum", 1);
//                map.put("pageSize", 20);
//            }
//            Integer pageNum = Integer.valueOf(map.get("pageNum").toString());
//            Integer pageSize = Integer.valueOf(map.get("pageSize").toString());
//            Page<Student> p = new Page<>(pageNum, pageSize);
//            if (!map.containsKey("age")) {
//                map.put("age", "");
//            }
//            if (!map.containsKey("name")) {
//                map.put("name", "");
//            }
//            List<Student> list = studentMapper.getStudents(p, map.get("age").toString(), map.get("name").toString());
//            p.setRecords(list);
//            return p;
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    @Override
//    public List<Student> getStudents1(Map<String, Object> map) {
//        List<Student> list = studentMapper.getStudents1(map);
//        return list;
//    }
    @Override
    public PageInfo getStudents2(Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
        List<Student> data = studentMapper.getStudents2(map);
        PageInfo page = new PageInfo(data);
        return page;
    }
}
