package com.zpf.demo;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zpf.demo.user.filter.UserFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.zpf.demo.user.repository")
public class TestApplication {
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }

    @Bean
    public FilterRegistrationBean<UserFilter> userFilter() {
        FilterRegistrationBean<UserFilter> registrationBean=new FilterRegistrationBean<UserFilter>();
        registrationBean.setFilter(new UserFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TestApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }


}
