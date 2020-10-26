package com.jack.controller;

import com.jack.entity.User;
import com.jack.entity.UserEntity;
import com.jack.micservice.MicService;
import com.jack.service.ServiceFeign;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：ControllerFeign
 * @date ： 2019-11-11 10:38
 * @modified By：
 */
@RestController
public class ControllerFeign {

    @Autowired
    private MicService micService;

    @PostMapping(value = "/getUserAll")
    public List<User> getUserAll(){
       return micService.getUserAll();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") int id){
        return micService.getById(id);
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer"))
    @GetMapping("/getUserId/{id}")
    public UserEntity getUserId(@PathVariable("id") int id){
        return micService.getUserById(id);
    }
}
