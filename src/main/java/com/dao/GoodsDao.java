package com.dao;

import com.entity.Goods;

import java.util.List;

/**
 * @author zzh
 * @description
 * @date
 */
public interface GoodsDao {

    int insert(Goods goods);
    int delete(int goodId);
    int update(Goods goods);
    int  select (Goods goods);
    List<Goods> findAll();


}
