package com.jack.service.Impl;

import com.carrotsearch.hppc.LongArrayList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.jack.conf.EsConf;
import com.jack.dao.EsDao;
import com.jack.domain.User;
import com.jack.repo.CommonRepo;
import com.jack.service.EsService;
import com.jack.utils.HibernateUtils;
import com.jack.utils.hibernate.DataSource;
import com.jack.utils.jwt.JSONUtils;
import com.jack.vo.EsInsertReturn;
import com.jack.vo.EsInsertVo;
import com.jack.vo.EsUpdateVo;
import com.jack.vo.UserVo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.ArrayStack;
import org.apache.poi.util.StringUtil;
import org.aspectj.lang.annotation.After;
import org.elasticsearch.common.recycler.Recycler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger= LoggerFactory.getLogger(EsServiceImpl.class);

    private final ObjectMapper mapper = new ObjectMapper();

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

    @Override
    public List<EsInsertReturn> getIds(List<String> ids) {
        List<Object> list = commonRepo.getIds(EsInsertVo.class, ids, esConf.getIndex(), esConf.getType());
        List<EsInsertVo> result = new ArrayList<>();
        for (Object temp : list) {
            result.add((EsInsertVo) temp);
        }
        List<EsInsertReturn> resultList = result.stream().map(EsInsertVo::toVo).collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<EsInsertReturn> getListAll(List<String> searchContents) {
        List<Object> list = commonRepo.getListAll(esConf.getIndex(), esConf.getType(), EsInsertVo.class, searchContents);
        List<EsInsertVo> result = new ArrayList<>();
        for (Object temp : list) {
            result.add((EsInsertVo) temp);
        }
        List<EsInsertReturn> resultList = result.stream().map(EsInsertVo::toVo).collect(Collectors.toList());
        return resultList;
    }

    @Override
    public void getAll(){
        commonRepo.getAll();
    }

    /**
     * 针对多层嵌套json字符串使用
     *
     * @param json
     * @param test
     */
    @Override
    public void demo(String json, Map<Long, List<String>> test) {
        //Map<String, List<Map<Long, String[]>>> specialUseCouponMap = new HashMap<>();
        //Map<String, Map<Long, List<String>>> result = new HashMap<>();
        Map<String, List<Map<Long, List<String>>>> specialUseCouponMap = new HashMap<>();
        try {
            Map<String, Object> iterator = mapper.readValue(json, Map.class);
            for (Map.Entry<String, Object> temp : iterator.entrySet()) {
                List<Map<Long, List<String>>> mapObject = mapper.readValue(JSONUtils.toJSON(temp.getValue()), new TypeReference<List<Map<Long, List>>>() {
                });
                Integer count = mapObject.size();
                specialUseCouponMap.put(temp.getKey(), mapObject);
                System.out.println(count);
            }
//            Map<String,Object> iterator = mapper.readValue(json, Map.class);
//            for (Map.Entry<String,Object> temp: iterator.entrySet() ) {
//                Map<Long, List<String>> mapObject = mapper.readValue(JSONUtils.toJSON(temp.getValue()), new TypeReference<Map<Long, List>>() {});
//                result.put(temp.getKey(),mapObject);
//            }
        } catch (Exception e) {
            //logger 方便记录数据
            logger.error(String.format("调用红包迁移服务异常,旧 userid：%s,新 userid：%s,res：%s", 123, "dasdasd", "dsafasfsafw"));
        }
    }

    //json字符串转Map,List,Set只针对单独的一层嵌套使用,多层嵌套使用可以使用上面的方法
    public void jsonTOCollectors(String json) throws Exception {
        Map<Long, List<String>> result = mapper.readValue(JSONUtils.toJSON(json), mapper.getTypeFactory().constructMapType(HashMap.class, Long.class, List.class));
        for (Map.Entry<Long, List<String>> temp : result.entrySet()) {
            System.out.println(temp.getKey() + "--" + temp.getValue().toString());
        }
    }

}
