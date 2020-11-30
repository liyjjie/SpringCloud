package com.jack.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jack.conf.EsConf;
import com.jack.dao.EsDao;
import com.jack.domain.User;
import com.jack.repo.CommonRepo;
import com.jack.service.EsService;
import com.jack.utils.HibernateUtils;
import com.jack.utils.hibernate.DataSource;
import com.jack.utils.jwt.JSONUtils;
import com.jack.vo.EsInsertReturn;
import com.jack.vo.EsInsertVo;
import com.jack.vo.EsUpdateVo;
import com.jack.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.log.LogInputStream;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author ：liyongjie
 * @ClassName ：EsServiceImpl
 * @date ： 2019-11-26 09:07
 * @modified By：
 */
@Service
public class EsServiceImpl implements EsService {

    private static final Logger logger = LoggerFactory.getLogger(EsServiceImpl.class);

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private EsDao esDao;

    @Autowired
    private EsConf esConf;

    @Autowired
    private CommonRepo commonRepo;

    @Override
    @Transactional
    @DataSource(readOnly = true)
    public User userById(Long id) {
        User user = esDao.userByid(id);
        return user;
    }

    @Override
    @Transactional
    @DataSource(readOnly = true)
    public void saveUser(UserVo vo) {
        User user = vo.toVo();
        HibernateUtils.getSession().save(user);
    }

    @Override
    public void EsInsert(EsInsertVo esInsertVo) {
        long date = new Date().getTime();
        esInsertVo.setCreateDate(date);
        commonRepo.insert(esConf.getIndex(), esConf.getType(), new Gson().toJson(esInsertVo, EsInsertVo.class), esInsertVo.getId());
    }

    @Override
    public EsInsertReturn getEs(String id) {
        EsInsertVo esInsertVo = (EsInsertVo) commonRepo.get(id, esConf.getIndex(), esConf.getType(), EsInsertVo.class);
        return esInsertVo.toVo();
    }

    @Override
    public Boolean updateOrInsertEs(EsInsertReturn esInsertReturn) {
        Boolean flag = commonRepo.updateOrInsert(esConf.getIndex(), esConf.getType(), String.valueOf(esInsertReturn.getId()), new Gson().toJson(esInsertReturn, EsInsertReturn.class), EsInsertReturn.class);
        return flag;
    }

    @Override
    public Boolean update(EsUpdateVo esUpdateVo) {
        //单纯的修改数据格式一定要保持如下
        Map<String, Object> map = new HashMap<>();
        map.put("doc", esUpdateVo);
        Boolean flag = commonRepo.update(esConf.getIndex(), esConf.getType(), String.valueOf(esUpdateVo.getId()), new Gson().toJson(map, Map.class));
        return flag;
    }

    @Override
    public Boolean deleteEs(String id) {
        Boolean flag = commonRepo.delete(String.valueOf(id), esConf.getIndex(), esConf.getType());
        return flag;
    }

    @Override
    public Boolean deleteIndex(String index) {
        Boolean flag = commonRepo.deleteIndex(index);
        return flag;
    }

    @Override
    public Boolean create(String index) {
        Boolean flag = commonRepo.create(index);
        return flag;
    }

    @Override
    public List<EsInsertReturn> getIds(List<String> ids) {
        List<Object> list = commonRepo.getIds(EsInsertVo.class, ids, esConf.getIndex(), esConf.getType());
        List<EsInsertVo> result = new ArrayList<>();
        for (Object temp : list) {
            result.add((EsInsertVo) temp);
        }
        List<EsInsertReturn> resultList = result.stream().map(EsInsertVo::toVo).collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<EsInsertReturn> getListAll(List<String> searchContents) {
        List<Object> list = commonRepo.getListAll(esConf.getIndex(), esConf.getType(), EsInsertVo.class, searchContents);
        List<EsInsertVo> result = new ArrayList<>();
        for (Object temp : list) {
            result.add((EsInsertVo) temp);
        }
        List<EsInsertReturn> resultList = result.stream().map(EsInsertVo::toVo).collect(Collectors.toList());
        return resultList;
    }

    /**
     * 针对多层嵌套json字符串使用
     *
     * @param json
     * @param test
     */
    @Override
    public void demo(String json, Map<Long, List<String>> test) {
        //Map<String, List<Map<Long, String[]>>> specialUseCouponMap = new HashMap<>();
        //Map<String, Map<Long, List<String>>> result = new HashMap<>();
        Map<String, List<Map<Long, List<String>>>> specialUseCouponMap = new HashMap<>();
        try {
            Map<String, Object> iterator = mapper.readValue(json, Map.class);
            for (Map.Entry<String, Object> temp : iterator.entrySet()) {
                List<Map<Long, List<String>>> mapObject = mapper.readValue(JSONUtils.toJSON(temp.getValue()), new TypeReference<List<Map<Long, List>>>() {
                });
                Integer count = mapObject.size();
                specialUseCouponMap.put(temp.getKey(), mapObject);
                System.out.println(count);
            }
//            Map<String,Object> iterator = mapper.readValue(json, Map.class);
//            for (Map.Entry<String,Object> temp: iterator.entrySet() ) {
//                Map<Long, List<String>> mapObject = mapper.readValue(JSONUtils.toJSON(temp.getValue()), new TypeReference<Map<Long, List>>() {});
//                result.put(temp.getKey(),mapObject);
//            }
        } catch (Exception e) {
            //logger 方便记录数据
            logger.error(String.format("调用红包迁移服务异常,旧 userid：%s,新 userid：%s,res：%s", 123, "dasdasd", "dsafasfsafw"));
        }
    }

    //json字符串转Map,List,Set只针对单独的一层嵌套使用,多层嵌套使用可以使用上面的方法
    public void jsonTOCollectors(String json) throws Exception {
        Map<Long, List<String>> result = mapper.readValue(JSONUtils.toJSON(json), mapper.getTypeFactory().constructMapType(HashMap.class, Long.class, List.class));
        for (Map.Entry<Long, List<String>> temp : result.entrySet()) {
            System.out.println(temp.getKey() + "--" + temp.getValue().toString());
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode recursive1(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {   // 递归结束条件
            return null;
        }
        int l1Val = l1 == null ? 0 : l1.val;
        int l2Val = l2 == null ? 0 : l2.val;
        int curSum = l1Val + l2Val + carry;
        ListNode curNode = new ListNode(curSum % 10);
        curNode.next = recursive1(l1 == null ? null : l1.next, l2 == null ? null : l2.next, curSum / 10);
        return curNode;
    }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        return recursive1(l1, l2, 0);
    }

    /**
     *无法转成数值类型的数据进行相加utils(如"1000000000000000000000000000001"与"234")
     * @param a
     * @param b
     * @return
     */
    public static String bigNumberPlus(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        if(lenA > lenB) {
            b = StringUtils.leftPad(b, lenA, "0");
        } else {
            a = StringUtils.leftPad(a, lenB, "0");
        }
        int[] arrC = new int[a.length() + 1];
        for(int i = a.length()-1; i>=0; i--) {
            int ai = Integer.parseInt(a.charAt(i) + "" );
            int bi = Integer.parseInt(b.charAt(i) + "" );
            int ci = arrC[i+1];
            int t = ai + bi + ci;
            arrC[i+1] = t%10;
            arrC[i] = t/10;
        }
        StringBuffer res = new StringBuffer();
        for(int i = 0; i<arrC.length; i++) {
            if(i==0 && arrC[i]==0) continue;
            res.append(arrC[i]);
        }
        return res.toString();
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        List<Integer> list = new ArrayList<>();
        while (true) {
            if (head != null) {
                list.add(head.val);
            } else {
                break;
            }
            head = head.next;
        }
        if(list.size()>=n){
            list.remove(list.size()-n);
        }
        ListNode listNode = null;
        for (int i = list.size(); i > 0; i--) {
            if (i == list.size()) {
                listNode = new ListNode(list.get(i-1));
            } else {
                listNode = new ListNode(list.get(i-1), listNode);
            }
        }
        return listNode;
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     *
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：
     *
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     * 动态条件 计算爬楼梯的方式
     */
    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * 示例 1：
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * 解释：输入数组表示数字 123。
     * 示例 2：
     * 输入：digits = [4,3,2,1]
     * 输出：[4,3,2,2]
     * 解释：输入数组表示数字 4321。
     * 示例 3：
     * 输入：digits = [0]
     * 输出：[1]
     * 输入带9的数据时 向前一位进一
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 示例 1:
     * 输入: 123
     * 输出: 321
     *  示例 2:
     * 输入: -123
     * 输出: -321
     * 示例 3:
     * 输入: 120
     * 输出: 21
     * 注意:
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int y = 0;
        while (x != 0) {
            if (y > 214748364 || y < -214748364) {
                return 0;
            }
            y = y * 10 + x % 10;
            x = x / 10;
        }
        return y;
    }
    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 你可以假设数组中无重复元素。
     * 示例 1:
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * 示例 2:
     * 输入: [1,3,5,6], 2
     * 输出: 1
     * 示例 3:
     * 输入: [1,3,5,6], 7
     * 输出: 4
     * 示例 4:
     * 输入: [1,3,5,6], 0
     * 输出: 0
     * 使用二分法 进行计算
     */
   public static int searchInsert(int[] nums,int target){
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * 示例 1：
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     * 示例 2：
     * 输入：lists = []
     * 输出：[]
     * 示例 3：
     * 输入：lists = [[]]
     * 输出：[]
     * 提示：
     * k == lists.length
     * 0 <= k <= 10^4
     * 0 <= lists[i].length <= 500
     * -10^4 <= lists[i][j] <= 10^4
     * lists[i] 按 升序 排列
     * lists[i].length 的总和不超过 10^4
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        List<Integer> list=new ArrayList<>();
        for (ListNode temp: lists) {
            while(true){
                if(temp!=null){
                   list.add(temp.val);
                }else{
                    break;
                }
                temp=temp.next;
            }
        }
        Collections.sort(list);
        ListNode listNode=null;
        for(int i=list.size()-1;i>=0;i--){
            if(i==list.size()-1){
                listNode=new ListNode(list.get(i));
            }else{
                listNode=new ListNode(list.get(i),listNode);
            }
        }
        return listNode;
    }
}
