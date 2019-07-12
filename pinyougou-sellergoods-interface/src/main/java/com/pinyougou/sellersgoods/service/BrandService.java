package com.pinyougou.sellersgoods.service;

import entity.PageResult;
import com.pinyougou.pojo.TbBrand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    public List<TbBrand> findAll();

    public PageResult<TbBrand> findPage(int currPage,int pageSize);

    public void add(TbBrand tbBrand);

    public TbBrand findOne(Long id);


    public void update(TbBrand tbBrand);

    public void delete(Long[] ids);

    PageResult<TbBrand> findPage(TbBrand tbBrand, Integer currPage, Integer pageSize);

    List<Map> selectOptionList();
}
