package com.jack.service.Impl;

import com.google.gson.Gson;
import com.jack.conf.EsConf;
import com.jack.dao.EsDao;
import com.jack.domain.User;
import com.jack.repo.CommonRepo;
import com.jack.service.EsService;
import com.jack.utils.HibernateUtils;
import com.jack.utils.hibernate.DataSource;
import com.jack.vo.EsInsertReturn;
import com.jack.vo.EsInsertVo;
import com.jack.vo.EsUpdateVo;
import com.jack.vo.UserVo;
import org.apache.commons.collections.ArrayStack;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


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

    @Autowired
    private EsConf esConf;

    @Autowired
    private CommonRepo commonRepo;

    @Override
    @Transactional
    @DataSource(readOnly = true)
    public User userById(Long id) {
        User user = esDao.userByid(id);
        return user;
    }

    @Override
    @Transactional
    @DataSource(readOnly = true)
    public void saveUser(UserVo vo) {
        User user = vo.toVo();
        HibernateUtils.getSession().save(user);
    }

    @Override
    public void EsInsert(EsInsertVo esInsertVo) {
        long date = new Date().getTime();
        esInsertVo.setCreateDate(date);
        commonRepo.insert(esConf.getIndex(), esConf.getType(), new Gson().toJson(esInsertVo, EsInsertVo.class), esInsertVo.getId());
    }

    @Override
    public EsInsertReturn getEs(String id) {
        EsInsertVo esInsertVo = (EsInsertVo) commonRepo.get(id, esConf.getIndex(), esConf.getType(), EsInsertVo.class);
        return esInsertVo.toVo();
    }

    @Override
    public Boolean updateOrInsertEs(EsInsertReturn esInsertReturn) {
        Boolean flag = commonRepo.updateOrInsert(esConf.getIndex(), esConf.getType(), String.valueOf(esInsertReturn.getId()), new Gson().toJson(esInsertReturn, EsInsertReturn.class), EsInsertReturn.class);
        return flag;
    }

    @Override
    public Boolean update(EsUpdateVo esUpdateVo) {
        //单纯的修改数据格式一定要保持如下
        Map<String, Object> map = new HashMap<>();
        map.put("doc", esUpdateVo);
        Boolean flag = commonRepo.update(esConf.getIndex(), esConf.getType(), String.valueOf(esUpdateVo.getId()), new Gson().toJson(map, Map.class));
        return flag;
    }

    @Override
    public Boolean deleteEs(String id) {
        Boolean flag = commonRepo.delete(String.valueOf(id), esConf.getIndex(), esConf.getType());
        return flag;
    }

    @Override
    public Boolean deleteIndex(String index) {
        Boolean flag = commonRepo.deleteIndex(index);
        return flag;
    }

    @Override
    public Boolean create(String index) {
        Boolean flag = commonRepo.create(index);
        return flag;
    }

    public List<EsInsertReturn> getIds(String[] ids) {
        List<Object> list = commonRepo.getIds(EsInsertVo.class, ids, esConf.getIndex(), esConf.getType());
        List<EsInsertVo> result = new ArrayList<>();
        for (Object temp : list) {
            result.add((EsInsertVo) temp);
        }
        List<EsInsertReturn> resultList = result.stream().map(EsInsertVo::toVo).collect(Collectors.toList());
        return resultList;
    }
}
