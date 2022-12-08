package com.practice;

import com.practice.springlearn.ioc.annotation.QuickStartConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@MapperScan("com.practice.*")
@Slf4j
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }

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

//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Person.class);
//        Person person = applicationContext.getBean(Person.class);
//        System.out.println("person = " + person);
//        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
//            System.out.println("beanDefinitionName = " + beanDefinitionName);
//        }
//    }

//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(QuickStartConfiguration.class);
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//
////        Toy toy = applicationContext.getBean(Toy.class);
////        System.out.println("toy = " + toy);
//
//        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
//    }
}
