package com.sunny.dubbo.nacos.bootstrap;



import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@EnableDubbo(scanBasePackages = "com.sunny.dubbo.nacos.service")
@PropertySource(value = "classpath:/config/provider.properties")
//@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
//@NacosPropertySource(dataId = "nacos-provider.properties", autoRefreshed = true)
public class ProviderBootstrap {

    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 @Configuration Class
        context.register(ProviderBootstrap.class);
        // 启动当前上下文
        context.refresh();
        System.out.println("服务提供者已启动...");
        System.in.read();
    }
}
