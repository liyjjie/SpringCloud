package com.jack.service;

import com.jack.domain.User;
import com.jack.vo.EsInsertReturn;
import com.jack.vo.EsInsertVo;
import com.jack.vo.UserVo;

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

    void saveUser(UserVo vo);

    Boolean updateOrInsertEs(EsInsertReturn esInsertReturn);

    Boolean update(EsInsertVo esInsertVo);

    Boolean deleteEs(String id);

    Boolean createIndex(String index);

    Boolean deleteIndex(String index);

    Boolean create(String index);
}
