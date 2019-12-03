package com.jack.service.Impl;

import com.google.gson.Gson;
import com.jack.conf.EsConf;
import com.jack.dao.EsDao;
import com.jack.domain.User;
import com.jack.repo.CommonRepo;
import com.jack.service.EsService;
import com.jack.vo.EsInsertReturn;
import com.jack.vo.EsInsertVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * @author ：liyongjie
 * @ClassName ：EsServiceImpl
 * @date ： 2019-11-26 09:07
 * @modified By：
 */
@Service
public class EsServiceImpl implements EsService {

    @Autowired
    private EsDao esDao;

//    @Resource(name = "EsClientFactory")
//    private EsClientFactory esClientFactory;

    @Autowired
    private EsConf esConf;

    @Autowired
    private CommonRepo commonRepo;

    @Override
    @Transactional(readOnly = false)
    public User userById(Long id) {
        User user = esDao.userByid(id);
        return user;
    }

    @Override
    public void EsInsert(EsInsertVo esInsertVo) {
        long date = new Date().getTime();
        esInsertVo.setCreateDate(date);
        commonRepo.insert(esConf.getIndex(), esConf.getType(), new Gson().toJson(esInsertVo, EsInsertVo.class), EsInsertVo.class);
    }

    @Override
    public EsInsertReturn getEs(Long id) {
        EsInsertVo esInsertVo = (EsInsertVo) commonRepo.get(String.valueOf(id), esConf.getIndex(), esConf.getType(), EsInsertVo.class);
        return esInsertVo.toVo();
    }

    @Override
    public Boolean updateEs(EsInsertReturn esInsertReturn) {
        Boolean flag = commonRepo.update(esConf.getIndex(), esConf.getType(), String.valueOf(esInsertReturn.getId()), new Gson().toJson(esInsertReturn, EsInsertReturn.class), EsInsertReturn.class);
        return flag;
    }

    @Override
    public Boolean deleteEs(Long id) {
        Boolean flag = commonRepo.delete(String.valueOf(id), esConf.getIndex(), esConf.getType());
        return flag;
    }
}
