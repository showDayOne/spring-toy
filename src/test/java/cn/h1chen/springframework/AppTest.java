package cn.h1chen.springframework;

import cn.h1chen.springframework.bean.UserDao;
import cn.h1chen.springframework.bean.UserService;
import cn.h1chen.springframework.beans.PropertyValue;
import cn.h1chen.springframework.beans.PropertyValues;
import cn.h1chen.springframework.beans.factory.config.BeanDefinition;
import cn.h1chen.springframework.beans.factory.config.BeanReference;
import cn.h1chen.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService", "h1chen");
        userService.queryUserInfo();
    }
}
