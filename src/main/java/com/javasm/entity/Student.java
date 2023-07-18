package com.javasm.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/18 14:06
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_student")
public class Student implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private String sname;

}
