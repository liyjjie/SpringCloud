package com.jack.service.Impl;

import com.jack.utils.Md5Utils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：liyongjie
 * @ClassName ：StreamUtils
 * @date ： 2020-11-16 09:38
 * @modified By：stream工具类
 * map方法 ： 接受一个函数入参,该函数会用在每个元素上,并将其结果映射成一个新的元素.
 * flatMap方法 ：接受一个函数入参,该函数会用在每个元素上,并将流中每个元素换到另一个流中.然后将所有的流连在一起.
 * filter方法
 */
public class StreamUtils {

    public static class StreamUtilsVo{
        private String name;
        private Integer age;

        public String getName(){
           return name;
        }

        public void setName(String name){
            this.name =name;
        }

        public Integer getAge(){
            return age;
        }

        public void setAge(Integer age){
            this.age=age;
        }

        public StreamUtilsVo(){

        }

        public StreamUtilsVo(String name,Integer age){
            this.name=name;
            this.age=age;
        }

        public String toString(){
            return "StreamUtilsVo{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception{
        StreamUtils streamUtils = new StreamUtils();
        // 迭代 创建流
        Stream<Integer> stream3 = Stream.iterate(2, (x) -> x * 2).limit(4);
        // 生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(4);
        List<StreamUtilsVo> list1 = Arrays.asList(new StreamUtilsVo("zhangsan", 18),
                new StreamUtilsVo("lisi", 21),
                new StreamUtilsVo("wangwu", 19),
                new StreamUtilsVo("zhaoliu", 20));
        streamUtils.streamSort(list1);
//        streamUtils.httpUtils("www.baidu.com","appkey","token");
    }

    public List<List<Integer>> streamMap() {
        List<List<Integer>> list = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6));
        List<List<Integer>> lists = new ArrayList<>();
        list.stream().forEach(temp -> {
            List<Integer> list1 = temp.stream().map(i -> i + 10).collect(Collectors.toList());
            lists.add(list1);
        });
        return lists;
    }

    /**
     * 排序
     *
     * @return
     */
    public List<StreamUtilsVo> streamSort(List<StreamUtilsVo> list) {
        List<StreamUtilsVo> list1 = list.stream().sorted(Comparator.comparing(temp -> temp.getName())).collect(Collectors.toList());
        return list1;
    }

    /**
     * 取出最小值
     *
     * @param list
     * @return
     */
    public StreamUtilsVo streamMin(List<StreamUtilsVo> list) {
        StreamUtilsVo list1 = list.stream().max(Comparator.comparing(temp -> temp.getAge())).get();
        return list1;
    }

    /**
     * allMatch：
     * anyMatch：
     * noneMatch：
     *
     * @param list
     */
    public void streamAnyMatch(List<StreamUtilsVo> list) {
        Boolean flag = list.stream().allMatch(temp -> temp.getAge() > 10);
        System.out.println(flag);
    }

    public void streamReduce(List<StreamUtilsVo> list) {
        Integer integer = list.stream().map(temp -> temp.getAge()).reduce(10, Integer::sum);
        System.out.println(integer);
    }

    /**
     * list转map集合
     *
     * @param list
     */
    public void streamListToMap(List<StreamUtilsVo> list) {
        Map<String, Integer> map = list.stream().collect(Collectors.toMap(StreamUtilsVo::getName, StreamUtilsVo::getAge));
    }

    public void streamCollect(List<StreamUtilsVo> list) {
        IntSummaryStatistics summaryStatistics = list.stream().collect(Collectors.summarizingInt(StreamUtilsVo::getAge));
    }

    public void streamCollect1(List<StreamUtilsVo> list) {
    Map<String, List<StreamUtilsVo>> list1=list.stream().collect(Collectors.groupingBy(StreamUtilsVo::getName));
        for (Map.Entry<String,List<StreamUtilsVo>> temp: list1.entrySet()) {
            System.out.println("temp.getKey()+\"--\"+temp.getValue().toString() = " + temp.getKey()+"--"+temp.getValue().toString());
        }
    }

    /**
     * 首先一定要提示大家
     * 当我们创建对象的前提,其实就是类必须要被加载了之后才能创建对象,
     *
     * 那么类是怎么被加载了的呢?
     * 类的加载是通过jvm加载的,jvm在加载类的时候分为3个过程,
     * 1.装载:查找并加载类的二进制数据
     * 2.链接:
     * (1)验证:确保被加载类的正确性
     * (2)准备:为类的静态变量分配内存,并将其初始化为默认值
     * (3)解析:把类中的符号引用转换为直接引用()
     * 什么是符号引用:在编译时，java类并不知道所引用的类的实际地址，因此只能使用符号引用来代替。比如org.simple.People类引用了org.simple.Language类，在编译时People类并不知道Language类的实际内存地址，因此只能使用符号org.simple.Language（假设是这个，当然实际中是由类似于CONSTANT_Class_info的常量来表示的）来表示Language类的地址。
     *
     * 什么是直接引用:
     * （1）直接指向目标的指针（比如，指向“类型”【Class对象】、类变量、 类方法的直接引用可能是指向方法区的指针）
     * （2）相对偏移量（比如，指向实例变量、实例方法的直接引用都是偏移量）
     * （3）一个能间接定位到目标的句柄
     *
     * 3.初始化:为类的静态变量赋予正确的初始值；
     *
     * 当new一个对象,底层会做一些什么事情
     * 1.Jvm加载未加载的字节码,开辟空间
     * 2.静态初始化(1静态代码块和2静态变量)
     * 3.成员变量初始化(1普通代码块和2普通成员变量)
     * 4.构造器初始化(构造函数)
     *
     * 如果这个类有父类的话,那么顺序一定是,1.父类静态代码块,2.父类静态成员,3.子类静态代码块,4.子类静态成员变量,然后是5.父类的普通代码块和6.普通成员变量和7.构造函数,8.子类的普通代码块和9.普通成员变量和10.构造函数
     */
    public String httpUtils(String url,String appkey,String token) throws Exception{
        String datetime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        String sign = Md5Utils.md5(appkey + token + datetime).toUpperCase();
        //timestamp = URLEncoder.encode(timestamp,"utf-8");
        GetMethod method = null;
        HttpClient client = new HttpClient();
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);  //连接超时
        client.getHttpConnectionManager().getParams().setSoTimeout(3000); //读取超时
        method = new GetMethod(url);
        method.setRequestHeader("Content-Type", "application/json;charset=utf-8");
        method.setRequestHeader("appkey", appkey);
        method.setRequestHeader("datetime", datetime);
        method.setRequestHeader("sign", sign);
        client.executeMethod(method);
        String returnStr = method.getResponseBodyAsString();
        return returnStr;
    }

}
