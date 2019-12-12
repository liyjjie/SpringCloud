package com.jack.controller;

import com.jack.domain.User;
import com.jack.service.EsService;
import com.jack.utils.RedisUtils;
import com.jack.utils.execl.ExcelImportParam;
import com.jack.utils.execl.ExcelImportUtils;
import com.jack.utils.jwt.AccessToken;
import com.jack.utils.jwt.AccessTokenJwtUtils;
import com.jack.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@Api(value = "/EsController")
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

    //token认证通过jwt获取用户信息，一般字符较长带特殊字符时采用以下方式接收防止数据丢失
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

    @ApiOperation("根据id查询数据(查询主库)测试")
    @GetMapping("/userHibernateUtilsById/{id}")
    public User userHibernateUtilsById(@PathVariable("id") Long id) {
        User user = esService.userById(id);
        return user;
    }

    @ApiOperation("测试主从库(添加从库)测试")
    @GetMapping("/saveUser")
    public void saveUser(@RequestBody UserVo vo) {
        esService.saveUser(vo);
    }

    //es操作
    @ApiOperation("es添加数据")
    @PostMapping("/EsInsert")
    public void EsInsert(@RequestBody EsInsertVo esInsertVo) {
        esService.EsInsert(esInsertVo);
    }

    @ApiOperation("es查询数据")
    @GetMapping("/getEs/{id}")
    public EsInsertReturn getEs(@PathVariable("id") String id) {
        EsInsertReturn esInsertReturn = esService.getEs(id);
        System.out.println(esInsertReturn);
        return esInsertReturn;
    }

    @ApiOperation("es修改数据/添加数据")
    @PostMapping("/updateOrInsertEs")
    public Boolean updateOrInsertEs(@RequestBody EsInsertReturn esInsertReturn) {
        Boolean flag = esService.updateOrInsertEs(esInsertReturn);
        return flag;
    }

    @ApiOperation("es修改数据")
    @PostMapping("/update")
    public Boolean update(@RequestBody EsUpdateVo esUpdateVo) {
        return esService.update(esUpdateVo);
    }

    @ApiOperation("es删除数据(清空文档)")
    @DeleteMapping("/deleteEs/{id}")
    public Boolean deleteEs(@PathVariable("id") String id) {
        Boolean flag = esService.deleteEs(id);
        return flag;
    }

    @ApiOperation("es删除索引")
    @DeleteMapping("/deleteIndex/{index}")
    public Boolean deleteIndex(@PathVariable("index") String index) {
        Boolean flag = esService.deleteIndex(index);
        return flag;
    }

    @ApiOperation("添加索引")
    @GetMapping("/create/{index}")
    public Boolean create(@PathVariable("index") String index) {
        return esService.create(index);
    }

    @ApiOperation("根据id查询(多个id)")
    @ApiImplicitParam(name = "ids", value = "多个id查询", required = true, dataTypeClass =String.class, paramType = "query")
    @RequestMapping(value = "/getIds", method = RequestMethod.GET)
    public List<EsInsertReturn> getIds(@RequestParam List<String> ids) {
        return esService.getIds(ids);
    }
}
