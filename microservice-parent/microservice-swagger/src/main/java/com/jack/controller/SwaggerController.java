package com.jack.controller;

import com.jack.entity.User;
import com.jack.service.SwaggerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：liyongjie
 * @ClassName ：SwaggerController
 * @date ： 2019-11-05 14:31
 * @modified By：
 */
@RestController
@RequestMapping(name = "/SwaggerController")
public class SwaggerController {

    @Autowired
    private SwaggerService swaggerService;

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"))
    @GetMapping(value="/UserById/{id}")
    public User UserById(@PathVariable(value = "id") Integer id) {
        return swaggerService.getUserById(id);
    }
}
