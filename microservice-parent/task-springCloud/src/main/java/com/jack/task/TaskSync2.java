package com.jack.task;

import com.jack.entity.User;
import com.jack.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ：liyongjie
 * @ClassName ：TaskSync2
 * @date ： 2020-09-27 09:40
 * @modified By：
 */
@Component
public class TaskSync2 {

    @Autowired
    private TaskService taskService;

    @Scheduled(fixedDelay = 3000)
    public void run(){
      User user=taskService.getAll();
      System.out.println(user+"每三十秒执行");
    }

}
