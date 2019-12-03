package com.jack.service;

import com.jack.domain.User;
import com.jack.vo.EsInsertReturn;
import com.jack.vo.EsInsertVo;

/**
 * @author ：liyongjie
 * @ClassName ：EsService
 * @date ： 2019-11-25 17:02
 * @modified By：
 */
public interface EsService {

    User userById(Long id);

    void EsInsert(EsInsertVo esInsertVo);

    EsInsertReturn getEs(Long id);

    Boolean updateEs(EsInsertReturn esInsertReturn);

    Boolean deleteEs(Long id);
}