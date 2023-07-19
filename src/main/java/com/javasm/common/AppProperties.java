package com.javasm.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/19 15:00
 * @description: 加载自定义的数据
 */
@Component
@Data
@ConfigurationProperties(prefix = "app.token")
public class AppProperties {
   private String privateKey;
   private Integer expireTime;
}
