package com.pinyougou.sellersgoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.PageResult;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellersgoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> findAll() {
        TbBrandExample tbBrandExample = new TbBrandExample();

        return tbBrandMapper.selectByExample(tbBrandExample);
    }

    @Override
    public PageResult<TbBrand> findPage(int currPage, int pageSize) {
        //获取当前页的数据
        PageHelper.startPage(currPage,pageSize);
        List<TbBrand> tbBrands = tbBrandMapper.selectByExample(new TbBrandExample());

        //获取分页对象
        PageInfo<TbBrand> pageInfo = new PageInfo<TbBrand>(tbBrands);

        //将总记录数和总页数封装到pageResult中
        PageResult<TbBrand> pageResult = new PageResult<TbBrand>(pageInfo.getTotal(),pageInfo.getList());
        return pageResult;
    }

    /**
     * 品牌添加
     * @param tbBrand
     */
    @Override
    public void add(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult<TbBrand> findPage(TbBrand tbBrand, Integer currPage, Integer pageSize) {
        //获取当前页的数据
        PageHelper.startPage(currPage,pageSize);
        //组装条件
        TbBrandExample tbBrandExample = new TbBrandExample();
        TbBrandExample.Criteria criteria = tbBrandExample.createCriteria();
        if (tbBrand != null) {
            if(tbBrand.getName() != null && !tbBrand.getName().equals("") ) {
                criteria.andNameLike("%" +tbBrand.getName()+"%");
            }

            if(tbBrand.getFirstChar() != null && !tbBrand.getFirstChar().equals("") ) {
                //criteria.andFirstCharLike("%" +tbBrand.getFirstChar()+"%");
                criteria.andFirstCharEqualTo(tbBrand.getFirstChar());
            }
        }
        List<TbBrand> tbBrands = tbBrandMapper.selectByExample( tbBrandExample);
        //获取分页对象
        PageInfo<TbBrand> pageInfo = new PageInfo<TbBrand>(tbBrands);
        //将总记录数和总页数封装到pageResult中
        PageResult<TbBrand> pageResult = new PageResult<TbBrand>(pageInfo.getTotal(),pageInfo.getList());
        return pageResult;
    }

    @Override
    public List<Map> selectOptionList() {
        return tbBrandMapper.selectOptionList();
    }


}
