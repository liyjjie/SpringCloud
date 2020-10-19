package com.jack.task;

import com.jack.entity.User;
import com.jack.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author ：liyongjie
 * @ClassName ：TaskSync1
 * @date ： 2020-09-27 09:36
 * @modified By：
 */
@Component
public class TaskSync1 implements CommandLineRunner {

    @Autowired
    private TaskService taskService;

    @Override
    public void run(String... strings) throws Exception {
      User user=taskService.getAll();
      System.err.println(user.toString());
    }
}
