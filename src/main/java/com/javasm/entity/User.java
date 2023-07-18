package com.javasm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2023-07-18 19:39:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 299480378935570999L;
    
    private Integer id;
    
    private String userName;
    
    private String birthday;
    
    private String gender;
    
    private String address;
    
    private Integer status;
    
    private Integer version;

}

