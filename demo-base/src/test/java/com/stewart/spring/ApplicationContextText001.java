package com.stewart.spring;

import com.stewart.spring.entity.Demo;
import com.stewart.spring.entity.Example;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Spring ApplicationContext中的一些基本方法
 *
 * @author stewart
 */
public class ApplicationContextText001 {

    /**
     * 基于FileSystemXmlApplicationContext创建容器，并通过类型获取bean
     */
    @Test
    public void contextTest001() {
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:application-context-001.xml");
        Demo demo = context.getBean(Demo.class);
        Assert.assertNotNull(demo);
        System.out.println(demo);
        System.out.println();
    }

    /**
     * 基于ClassPathXmlApplicationContext创建容器
     */
    @Test
    public void contextTest002() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context-001.xml");
        // 通过id获取指定bean
        Demo demo1 = (Demo) context.getBean("demo");
        // 按类型获取bean,按类型获取时，容器中只能有一个指定类型的bean
        Demo demo2 = context.getBean(Demo.class);
        // 按id+类型获取bean
        Demo demo3 = context.getBean("demo", Demo.class);
        System.out.println(demo1);
        Assert.assertEquals(demo1, demo2);
        Assert.assertEquals(demo1, demo3);
        System.out.println();
    }

    @Test
    public void contextTest003() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context-001.xml");
        //spring容器中bean的数量
        int beanDefinitionCount = context.getBeanDefinitionCount();
        System.out.println("beanDefinitionCount : " + beanDefinitionCount);

        //获取spring容器中所有bean的id值，无id值的bean获取其name值，无id值和name值的获取默认值
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanName : " + beanDefinitionName);
        }
        System.out.println();
        // 获取spring容器中指定类型的bean的id值，无id值的bean获取其name值，无id值和name值的获取默认值
        String[] beanNames = context.getBeanNamesForType(Example.class);
        for (String beanName : beanNames) {
            System.out.println("Example bean name : " + beanName);
        }
        System.out.println();
    }

    @Test
    public void contextTest004() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context-001.xml");
        //containsBeanDefinition bean有定义id值时，对应bean只有对id值判断结果为true；
        //当bean无id值时对name值判断也为true
        String name = "example";
        boolean example = context.containsBeanDefinition(name);
        System.out.println("是否包含命名为[" + name + "]的bean定义 " + example);
        Assert.assertTrue(example);

        name = "example0";
        boolean example0 = context.containsBeanDefinition(name);
        System.out.println("是否包含命名为[" + name + "]的bean定义 " + example0);
        // 因该bean已有id值，所以结果为false
        Assert.assertFalse(example0);

        name = "example1";
        boolean example1 = context.containsBeanDefinition(name);
        System.out.println("是否包含命名为[" + name + "]的bean定义 " + example1);
        Assert.assertTrue(example1);

        //containsBean 不论id还是name，只要有定义，结果就为true
        example = context.containsBean("example");
        example0 = context.containsBean("example0");
        example1 = context.containsBean("example1");
        Assert.assertTrue(example);
        Assert.assertTrue(example0);
        Assert.assertTrue(example1);
    }

}
