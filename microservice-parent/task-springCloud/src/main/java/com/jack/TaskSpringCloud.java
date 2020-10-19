package com.jack;

import com.jack.task.TaskSync;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ：liyongjie
 * @ClassName ：TaskSpringCloud
 * @date ： 2020-09-25 15:36
 * @modified By：
 */
@EnableScheduling
@SpringBootApplication
public class TaskSpringCloud {

    @Value("${spring.datasource.url}")
    private String url;

    public static void main(String[] args) {
//        new SpringApplicationBuilder(TaskSpringCloud.class).web(false).run(args);


        SpringApplication sa = new SpringApplication(TaskSpringCloud.class);
        sa.setWebEnvironment(false);
        ApplicationContext ctx = sa.run(args);

        Thread reissueBagThread = new Thread(ctx.getBean(TaskSync.class));

        reissueBagThread.setName("ReissueBagThread");
        reissueBagThread.start();
    }

    @Bean
    public String demo(){
        System.out.println(url);
        return url;
    }
}
