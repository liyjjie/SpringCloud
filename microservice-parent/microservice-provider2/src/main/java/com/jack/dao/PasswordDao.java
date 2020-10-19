package com.jack.dao;

import com.jack.vo.PasswordVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：PasswordDao
 * @date ： 2020-10-14 09:53
 * @modified By：
 */
@Mapper
public interface PasswordDao {

    void passwordInsert(List<PasswordVo> passwordDaos);
}
