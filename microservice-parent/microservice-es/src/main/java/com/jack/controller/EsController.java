package com.jack.controller;

import com.jack.domain.User;
import com.jack.service.EsService;
import com.jack.service.Impl.AfterDemo;
import com.jack.utils.RedisUtils;
import com.jack.utils.exception.ServiceException;
import com.jack.utils.execl.ExcelImportParam;
import com.jack.utils.execl.ExcelImportUtils;
import com.jack.utils.jwt.AccessToken;
import com.jack.utils.jwt.AccessTokenJwtUtils;
import com.jack.utils.jwt.JSONUtils;
import com.jack.vo.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

/**
 * @author ：liyongjie
 * @ClassName ：EsController
 * @date ： 2019-11-26 09:06
 * @modified By：
 */
//es搜索
@RestController
@RequestMapping("/EsController")
//@Api(value = "/EsController",description = "es搜索")
public class EsController {

    @Autowired
    private EsService esService;

    @Autowired
    private AfterDemo afterDemo;

    //execl导入数据utils
    @ApiOperation(value = "数据导入")
    @PostMapping("/demo")
    public void demo(@ApiParam(value = "excel数据文件()", required = true) MultipartFile file) throws Exception {
        List<ExcelImportParam> list = new ArrayList<>(2);
        list.add(new ExcelImportParam(0, "name", true, "", "", "", ExcelImportParam.VALIDATE_TYPE_DEFAULT));
        list.add(new ExcelImportParam(1, "age", true, "", "", "", ExcelImportParam.VALIDATE_TYPE_DEFAULT));
        List<ResultVo> vos = ExcelImportUtils.doExcelImport(ResultVo.class, list, file, 0);
        for (ResultVo temp : vos) {
            System.out.println(temp);
        }
    }

    //token认证通过jwt获取用户信息，一般字符较长带特殊字符时采用以下方式接收防止数据丢失
    @ApiOperation(value = "jwt转uid")
    @GetMapping(value = "/{jwt}/jwtToUid")//字段长的数据 在url上不能在最后显示 存在数据丢失
    public void jwtToUid(@PathVariable(value = "jwt") String jwt){
        AccessToken accessToken = AccessTokenJwtUtils.dncrypt(jwt);
        System.out.println(jwt);
        System.out.println(accessToken.getUid());
    }

    //通过uid获取jwt
    @ApiOperation("uid转jwt")
    @PostMapping(value = "/{uid}/toJwt")
    public String uidToJwt(@PathVariable("uid") Long uid) {
        AccessToken accessToken = new AccessToken();
        accessToken.setUid(uid);
        String str = AccessTokenJwtUtils.encrypt(accessToken);
        return str;
    }

    //连接本地的Redis
    @ApiOperation("redis测试")
    @PostMapping(value = "/redisConst")
    public String redisConst() {
        afterDemo.access();
        JedisPool jedisPool = RedisUtils.getJedisPool("order");
        String str;
        try (Jedis jedis = jedisPool.getResource()) {
            str = jedis.get("inv_1_10000");
        }
        return str;
    }

    @ApiOperation("根据id查询数据(查询主库)测试")
    @GetMapping(value = "/userHibernateUtilsById/{id}")
    public User userHibernateUtilsById(@PathVariable("id") Long id) {
        User user = esService.userById(id);
        return user;
    }

    @ApiOperation("测试主从库(添加从库)测试")
    @GetMapping(value = "/saveUser")
    public void saveUser(@RequestBody UserVo vo) {
        esService.saveUser(vo);
    }

    //es操作
    @ApiOperation("es添加数据")
    @PostMapping(value = "/EsInsert")
    public void EsInsert(@RequestBody EsInsertVo esInsertVo) {
        esService.EsInsert(esInsertVo);
    }

    @ApiOperation("es查询数据")
    @GetMapping(value = "/getEs/{id}")
    public EsInsertReturn getEs(@PathVariable("id") String id) {
        EsInsertReturn esInsertReturn = esService.getEs(id);
        System.out.println(esInsertReturn);
        return esInsertReturn;
    }

    @ApiOperation("es修改数据/添加数据")
    @PostMapping(value = "/updateOrInsertEs")
    public Boolean updateOrInsertEs(@RequestBody EsInsertReturn esInsertReturn) {
        Boolean flag = esService.updateOrInsertEs(esInsertReturn);
        return flag;
    }

    @ApiOperation("es修改数据")
    @PostMapping(value = "/update")
    public Boolean update(@RequestBody EsUpdateVo esUpdateVo) {
        return esService.update(esUpdateVo);
    }

    @ApiOperation("es删除数据(清空文档)")
    @DeleteMapping(value = "/deleteEs/{id}")
    public Boolean deleteEs(@PathVariable("id") String id) {
        Boolean flag = esService.deleteEs(id);
        return flag;
    }

    @ApiOperation("es删除索引")
    @DeleteMapping(value = "/deleteIndex/{index}")
    public Boolean deleteIndex(@PathVariable("index") String index) {
        Boolean flag = esService.deleteIndex(index);
        return flag;
    }

    @ApiOperation("添加索引")
    @GetMapping(value = "/create/{index}")
    public Boolean create(@PathVariable("index") String index) {
        return esService.create(index);
    }

    @ApiOperation("根据id查询(多个id)")
    @ApiImplicitParam(name = "ids", value = "多个id查询", required = true, dataTypeClass =String.class, paramType = "query")
    @RequestMapping(value = "/getIds", method = RequestMethod.GET)
    public List<EsInsertReturn> getIds(@RequestParam List<String> ids) {
        return esService.getIds(ids);
    }

    @ApiOperation("对es复杂查询 排序(排序要配置type是否允许排序)")
    @GetMapping(value = "/getListAll")
    @ApiImplicitParam(name = "/getListAll",value = "排序",required = true,dataTypeClass = String.class,paramType = "query")
    public List<EsInsertReturn> getListAll(@RequestParam List<String> searchContents){
       return esService.getListAll(searchContents);
    }

    @ApiOperation("json")
    @GetMapping(value = "/demo")
    //json转Map<String,List<Map<Long,List<String>>>>
    public String demo(){
//        Map<String, Map<Long, List<String>>> specialUseCouponMap = new HashMap<>();
        Map<String, List<Map<Long, List<String>>>> specialUseCouponMap = new HashMap<>();
        Map<Long,List<String>> listMap=new HashMap<>();
        List<String> str=new ArrayList<>();
        str.add("张");
        str.add("王");
        for (int i = 0; i <2; i++) {
            listMap.put(Long.valueOf(i),str);
        }
        List<Map<Long, List<String>>> count=new ArrayList<>();
        for(int i=0;i<3;i++){
            count.add(listMap);
        }
        for (int i = 0; i <2 ; i++) {
          String uuid=UUID.randomUUID().toString();
            specialUseCouponMap.put(uuid.substring(0,4),count);
        }
        String json="";
        try{
         json= JSONUtils.toJSON(specialUseCouponMap);
            System.out.println(json);
        }catch (Exception e){
            System.err.println("error"+e);
        }
        esService.demo(json,listMap);
       return json;
    }

    @ApiOperation(value = "es取出所有数据")
    @GetMapping(value = "/getAll")
    public void getAll(){
    }



}
