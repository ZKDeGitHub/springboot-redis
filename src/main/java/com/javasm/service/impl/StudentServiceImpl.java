package com.javasm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javasm.entity.Student;
import com.javasm.mapper.StudentMapper;
import com.javasm.service.StudentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/18 21:02
 * @description: ToDo
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

/*
   1，第一步 先去redis中 缓存中查看 有没有数据
   2，如果存在 直接返回  不会去mysql查询 不会执行目标方法
   3，如果没有 去执行目标方法 后续把查询来的学生对象 放入redis中
*/
   @Override
   @Cacheable(value = "student" ,key = "#id")
   public Student getById(Serializable id) {
      return super.getById(id);
   }


   /**
    * @CacheEvict(value = "student")
    * 先去删除mysql中的数据，再去删除redis中的student目录下的数据
    * @param id
    * @return
    */
   @Override
   @CacheEvict(value = "student")
   public boolean removeById(Serializable id) {
      return super.removeById(id);
   }

   /**
    * 修改数据库中内容时，同时删除该条内容在redis中的缓存
    *@CacheEvict(value = "student", key = "#student.id")
    * key值为形参的对象名.id
    *
    * @param student
    * @return
    */
   @Override
   @CacheEvict(value = "student", key = "#student.id")
   public boolean updateById(Student student) {
      return super.updateById(student);
   }
}
