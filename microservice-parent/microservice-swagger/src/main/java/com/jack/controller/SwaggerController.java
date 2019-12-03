package com.jack.controller;

import com.jack.entity.User;
import com.jack.service.SwaggerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author ：liyongjie
 * @ClassName ：SwaggerController
 * @date ： 2019-11-05 14:31
 * @modified By：
 */
@RestController
@RequestMapping(value = "/SwaggerController")
@Api(value = "/SwaggerController",description = "根据url的id获取哟用户详细信息")
public class SwaggerController {

    @Autowired
    private SwaggerService swaggerService;

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @GetMapping(value="/UserById/{id}")
    public User UserById(@PathVariable(value = "id") Integer id) {
        return swaggerService.getUserById(id);
    }

}
