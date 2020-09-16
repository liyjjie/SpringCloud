package com.jack.service;

import com.jack.domain.User;
import com.jack.vo.EsInsertReturn;
import com.jack.vo.EsInsertVo;
import com.jack.vo.EsUpdateVo;
import com.jack.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * @author ：liyongjie
 * @ClassName ：EsService
 * @date ： 2019-11-25 17:02
 * @modified By：
 */
public interface EsService {

    User userById(Long id);

    void EsInsert(EsInsertVo esInsertVo);

    EsInsertReturn getEs(String id);

    void saveUser(UserVo vo);

    Boolean updateOrInsertEs(EsInsertReturn esInsertReturn);

    Boolean update(EsUpdateVo esUpdateVo);

    Boolean deleteEs(String id);

    Boolean deleteIndex(String index);

    Boolean create(String index);

    List<EsInsertReturn> getIds(List<String> ids);

    List<EsInsertReturn> getListAll(List<String> searchContents);

    void  demo(String json, Map<Long,List<String>> test);

    void getAll();

}
