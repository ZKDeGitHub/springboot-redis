package com.javasm.service.impl;

import com.javasm.entity.Student;
import com.javasm.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/18 21:44
 * @description: ToDo
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceImplTest {
    @Resource
    private StudentService studentService;

    @Test
    public void test1() {
        Student student = studentService.getById(2);
        System.out.println(student);
    }
    @Test
    public void test2(){
        boolean b = studentService.removeById(1);
        System.out.println(b);
    }
    @Test
    public void test3(){
        Student student = new Student(2, "渣机");
        boolean b = studentService.updateById(student);
        System.out.println(b);
    }
}