package com.muguifang.test;

import com.muguifang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Slf4j
public class MyTest {
    private ApplicationContext applicationContext;
    @Autowired
    private TUserMapper userMapper;
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 导入需要测试的
        userMapper = applicationContext.getBean(TUserMapper.class);
        userService = applicationContext.getBean(UserService.class);
    }
    @Test
    public void test1(){
//        CacheManager singletonManager=CacheManager.getInstance();
//        Cache tUserCache = singletonManager.getCache("tUserCache");
//        singletonManager.clearAll();
//        singletonManager.shutdown();

    }

}