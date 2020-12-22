package com.jack.task;

/**
 * @author ：liyongjie
 * @ClassName ：TaskSync3
 * @date ： 2020-09-27 15:47
 * @modified By：此服务未执行
 */
public class TaskSync3 implements Runnable{
    private static final Boolean flag;

    static{
          flag = true;
    }

    @Override
    public void run() {
        System.out.println(flag+"Runnable实现了");
    }
}
