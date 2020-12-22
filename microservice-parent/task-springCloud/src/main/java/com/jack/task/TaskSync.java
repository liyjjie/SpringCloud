package com.jack.task;

import com.jack.entity.User;
import com.jack.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ：liyongjie
 * @ClassName ：TaskSync
 * @date ： 2020-09-25 15:50
 * @modified By：
 */
@Component
public class TaskSync implements DisposableBean,Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TaskSync.class);

    @Autowired
    private TaskService taskService;

    @Override
    public void run() {
        logger.info("线程开始");
        User user=taskService.getAll();
        System.out.println(user);
    }

    //在服务关闭时会自动调用此方法
    @Override
    public void destroy() throws Exception {
        logger.info("服务已释放");
    }
}
