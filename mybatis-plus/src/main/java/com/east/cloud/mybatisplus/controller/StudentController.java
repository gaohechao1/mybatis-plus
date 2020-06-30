package com.east.cloud.mybatisplus.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.east.cloud.mybatisplus.entity.Student;
import com.east.cloud.mybatisplus.service.StudentService;
import com.east.cloud.mybatisplus.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sa
 * @since 2020-06-09
 */
@RestController
@RequestMapping("v1/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Value("${server.port}")
    private String port;

        /*
     * 使用mybatisplus查询数据
     * */
    @RequestMapping("/getStudents")
    public JsonResult getstudents(@RequestParam Map<String, Object> map) {

        JsonResult jsonResult = new JsonResult();
        try {
            LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
            if (!map.containsKey("name")){
                map.put("name", "");
            }
            if (!map.containsKey("pageNum") || !map.containsKey("pageSize")) {
                map.put("pageNum", 1);
                map.put("pageSize", 20);
            }
            Integer pageNum = Integer.valueOf(map.get("pageNum").toString());
            Integer pageSize = Integer.valueOf(map.get("pageSize").toString());
            String name = map.get("name").toString();
            queryWrapper.like(Student::getName, name);
            IPage<Student> page = studentService.page(new Page<>(pageNum, pageSize), queryWrapper);
            if (page.getTotal() > 0) {
                jsonResult.setState(JsonResult.SUCCESS);
                jsonResult.setData(page);
                jsonResult.setMessage("查询成功，端口号：" + port);
            } else {
                jsonResult.setState(JsonResult.FAILED);
                jsonResult.setData(null);
                jsonResult.setMessage("暂无数据");
            }
        } catch (Exception e) {
            jsonResult.setState(JsonResult.SYSTEMERROR);
            jsonResult.setData(0);
            jsonResult.setMessage(e.getMessage());
        } finally {
            return jsonResult;
        }
    }
//
//    /*
//     * 使用xml查询数据（带分页）
//     * */
//    @RequestMapping("/getStudents_1")
//    public JsonResult getstudents_1(@RequestParam Map<String, Object> map) {
//
//        JsonResult jsonResult = new JsonResult();
//        try {
//            Student student = JSON.parseObject(JSON.toJSONString(map), Student.class);
//            LocalDateTime localDateTime = LocalDateTime.now();
//            student.setInputDate(localDateTime);
//            IPage<Student> page = studentService.getStudents(map);
//            if (page.getSize() > 0) {
//                jsonResult.setState(JsonResult.SUCCESS);
//                jsonResult.setData(page);
//                jsonResult.setMessage("查询成功");
//            } else {
//                jsonResult.setState(JsonResult.FAILED);
//                jsonResult.setData(null);
//                jsonResult.setMessage("暂无数据");
//            }
//        } catch (Exception e) {
//            jsonResult.setState(JsonResult.SYSTEMERROR);
//            jsonResult.setData(0);
//            jsonResult.setMessage(e.getMessage());
//        } finally {
//            return jsonResult;
//        }
//    }
//
//    /*
//     * 使用xml查询数据（不带分页）
//     * */
//    @RequestMapping("/getStudents_2")
//    public JsonResult getstudents_2(@RequestParam Map<String, Object> map) {
//
//        JsonResult jsonResult = new JsonResult();
//        try {
//            Student student = JSON.parseObject(JSON.toJSONString(map), Student.class);
//            LocalDateTime localDateTime = LocalDateTime.now();
//            student.setInputDate(localDateTime);
//            List<Student> page = studentService.getStudents1(map);
//            if (page.size() > 0) {
//                jsonResult.setState(JsonResult.SUCCESS);
//                jsonResult.setData(page);
//                jsonResult.setMessage("查询成功");
//            } else {
//                jsonResult.setState(JsonResult.FAILED);
//                jsonResult.setData(null);
//                jsonResult.setMessage("暂无数据");
//            }
//        } catch (Exception e) {
//            jsonResult.setState(JsonResult.SYSTEMERROR);
//            jsonResult.setData(0);
//            jsonResult.setMessage(e.getMessage());
//        } finally {
//            return jsonResult;
//        }
//    }
    /*
     * 使用xml查询数据（带分页和其他参数）
     * */
    @RequestMapping("/getStudents_3")
    public JsonResult getstudents_3(@RequestParam Map<String, Object> map) {

        JsonResult jsonResult = new JsonResult();
        try {
            PageInfo page = studentService.getStudents2(map);
            if (page.getSize() > 0) {
                jsonResult.setState(JsonResult.SUCCESS);
                jsonResult.setData(page);
                jsonResult.setMessage("查询成功");
            } else {
                jsonResult.setState(JsonResult.FAILED);
                jsonResult.setData(null);
                jsonResult.setMessage("暂无数据");
            }
        } catch (Exception e) {
            jsonResult.setState(JsonResult.SYSTEMERROR);
            jsonResult.setData(0);
            jsonResult.setMessage(e.getMessage());
        } finally {
            return jsonResult;
        }
    }

    /*
     * 添加数据
     * */
    @RequestMapping("/addStudent")
    public JsonResult addStudent(@RequestParam Map<String, Object> map) {
        JsonResult jsonResult = new JsonResult();
        try {
            Student student = JSON.parseObject(JSON.toJSONString(map), Student.class);
            LocalDateTime localDateTime = LocalDateTime.now();
            student.setInputDate(localDateTime);
            Boolean result = studentService.save(student);
//            List<Student> students = new ArrayList<>();
//            students.add(student);
//            Student student1 = new Student();
//            student1.setName("小飞");
//            student1.setAge(16);
//            student1.setSex("男");
//            student1.setInputDate(localDateTime);
//            students.add(student1);
//            Boolean result = studentService.saveBatch(students); 添加多条数据数使用事务

            if (result) {
                jsonResult.setState(JsonResult.SUCCESS);
                jsonResult.setData(student.getId());
                jsonResult.setMessage("添加成功");
            } else {
                jsonResult.setState(JsonResult.FAILED);
                jsonResult.setData(0);
                jsonResult.setMessage("添加失败");
            }
        } catch (Exception e) {
            jsonResult.setState(JsonResult.SYSTEMERROR);
            jsonResult.setData(0);
            jsonResult.setMessage(e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            return jsonResult;
        }
    }

    /*
     * 根据id修改数据
     * */
    @RequestMapping("/updateStudentById")
    public JsonResult updateStudentById(@RequestParam Map<String, Object> map) {
        JsonResult jsonResult = new JsonResult();
        try {
            Student student = JSON.parseObject(JSON.toJSONString(map), Student.class);
            LocalDateTime localDateTime = LocalDateTime.now();
            student.setInputDate(localDateTime);
            Boolean result = studentService.updateById(student);
            if (result) {
                jsonResult.setState(JsonResult.SUCCESS);
                jsonResult.setData(1);
                jsonResult.setMessage("修改成功");
            } else {
                jsonResult.setState(JsonResult.FAILED);
                jsonResult.setData(0);
                jsonResult.setMessage("修改失败");
            }
        } catch (Exception e) {
            jsonResult.setState(JsonResult.SYSTEMERROR);
            jsonResult.setData(0);
            jsonResult.setMessage(e.getMessage());
        } finally {
            return jsonResult;
        }
    }

    /*
     * 根据id删除数据
     * */
    @RequestMapping("/delStudentById")
    public JsonResult delStudentById(@RequestParam Map<String, Object> map) {

        JsonResult jsonResult = new JsonResult();
        try {
            String id = map.get("id").toString();
            Boolean result = studentService.removeById(id);
            if (result) {
                jsonResult.setState(JsonResult.SUCCESS);
                jsonResult.setData(1);
                jsonResult.setMessage("删除成功");
            } else {
                jsonResult.setState(JsonResult.FAILED);
                jsonResult.setData(0);
                jsonResult.setMessage("删除失败");
            }
        } catch (Exception e) {
            jsonResult.setState(JsonResult.SYSTEMERROR);
            jsonResult.setData(0);
            jsonResult.setMessage(e.getMessage());
        } finally {
            return jsonResult;
        }
    }
}
