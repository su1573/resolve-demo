package com.tj.resolvedemo.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: resolve-demo
 * @Date: 2021/3/21 22:02
 * @Author: Mr.SU
 * @Description:
 */
@Repository
public interface GzbInfoMapper {

    void insertGzbInfos(HashMap<String, Object> gzbInfos);

    void startInsertGzbInfos(HashMap<String, Object> gzbInfos);


    void startInsertGzbConInfos(ConcurrentHashMap<String, Object> gzbInfos);

















}
