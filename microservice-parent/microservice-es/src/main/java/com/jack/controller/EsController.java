package com.jack.controller;

import com.jack.domain.User;
import com.jack.service.EsService;
import com.jack.utils.RedisUtils;
import com.jack.utils.execl.ExcelImportParam;
import com.jack.utils.execl.ExcelImportUtils;
import com.jack.utils.jwt.AccessToken;
import com.jack.utils.jwt.AccessTokenJwtUtils;
import com.jack.vo.EsInsertReturn;
import com.jack.vo.EsInsertVo;
import com.jack.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：EsController
 * @date ： 2019-11-26 09:06
 * @modified By：
 */
//es搜索
@RestController
@Api(value = "")
public class EsController {

    @Autowired
    private EsService esService;

    //execl导入数据utils
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

    //token认证通过jwt获取用户信息
    @PostMapping("/{jwt}/demo")
    public void jwt(@PathVariable("jwt") String jwt) {
        AccessToken accessToken = AccessTokenJwtUtils.dncrypt(jwt);
        System.out.println(accessToken.getUid());
    }

    //通过uid获取jwt
    @PostMapping("/{uid}/toJwt")
    public String uidToJwt(@PathVariable("uid") Long uid) {
        AccessToken accessToken = new AccessToken();
        accessToken.setUid(uid);
        String str = AccessTokenJwtUtils.encrypt(accessToken);
        return str;
    }

    //连接本地的Redis
    @PostMapping("/redisConst")
    public void redisConst() {
        JedisPool jedisPool = RedisUtils.getJedisPool("order");
        try (Jedis jedis = jedisPool.getResource()) {
            String value = jedis.get("inv_1_1");
            System.out.println(value);
        }
    }

    //使用hibernate连接本地数据库
    @GetMapping("/userHibernateUtilsById/{id}")
    public User userHibernateUtilsById(@PathVariable("id") Long id) {
        User user = esService.userById(id);
        return user;
    }

    //es操作
    @ApiOperation("es添加数据")
    @PostMapping("/EsInsert")
    public void EsInsert(@RequestBody EsInsertVo esInsertVo) {
        esService.EsInsert(esInsertVo);
    }

    @ApiOperation("es查询数据")
    @GetMapping("/getEs/{id}")
    public EsInsertReturn getEs(@PathVariable("id") Long id) {
        EsInsertReturn esInsertReturn = esService.getEs(id);
        System.out.println(esInsertReturn);
        return esInsertReturn;
    }

    @ApiOperation("es修改数据")
    @PostMapping("/updateEs")
    public Boolean updateEs(@RequestBody EsInsertReturn esInsertReturn) {
        Boolean flag = esService.updateEs(esInsertReturn);
        return flag;
    }

    @ApiOperation("es删除数据")
    @DeleteMapping("/deleteEs/{id}")
    public Boolean deleteEs(@PathVariable("id") Long id) {
        Boolean flag = esService.deleteEs(id);
        return flag;

    }
}
