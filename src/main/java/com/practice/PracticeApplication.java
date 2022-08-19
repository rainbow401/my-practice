package com.practice;

import com.practice.domain.springlearn.ioc.annotation.QuickStartConfiguration;
import com.practice.domain.springlearn.ioc.aware.AwareConfiguration;
import com.practice.domain.springlearn.ioc.aware.AwareTestBean;
import com.practice.domain.springlearn.ioc.lazyinject.Person;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
@MapperScan("com.practice.mapper")
@Slf4j
public class PracticeApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(PracticeApplication.class, args);
//    }

//    public static void main(String[] args) throws Exception{
//
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(QuickStartConfiguration.class);
//        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
//            System.out.println("beanDefinitionName = " + beanDefinitionName);
//        }
//        System.out.println("ctx.getBean(Person.class) = " + ctx.getBean(Person.class));
//    }

//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AwareConfiguration.class);
//        AwareTestBean awareTestBean = applicationContext.getBean(AwareTestBean.class);
//        System.out.println("awareTestBean.getBeanName() = " + awareTestBean.getBeanName());
//        System.out.println("awareTestBean.getName() = " + awareTestBean.getName());
//        System.out.println("------------");
//        awareTestBean.printBeanNames();
//    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Person.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println("person = " + person);
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}
