package com.pinyougou.sellersgoods.service.impl;
import java.util.List;
import java.util.Map;

import com.pinyougou.entitygroup.Specification;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.sellersgoods.service.SpecificationService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
	    //保存规格信息
        TbSpecification tbSpecification = specification.getSpecification();
		List<TbSpecificationOption> specificationOptions = specification.getSpecificationOptions();
        specificationMapper.insert(tbSpecification);

        //获取规格主键
        Long specId = tbSpecification.getId();


        for (TbSpecificationOption specificationOption : specificationOptions) {
            //设置规格选项的外键
            specificationOption.setSpecId(specId);
            specificationOptionMapper.insert(specificationOption);
        }
    }

	
	/**
	 * 修改
	 * 第三天  保存修改结果
	 */
	@Override
	public void update(Specification specification){
		//保存修改的规格
        TbSpecification tbSpecification = specification.getSpecification();
		//保存规格
        specificationMapper.updateByPrimaryKey(tbSpecification);

		//删除原有的规格选项
        TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
		//指定规格ID,为条件
        criteria.andSpecIdEqualTo(tbSpecification.getId());
        //删除
        specificationOptionMapper.deleteByExample(tbSpecificationOptionExample);

		//循环插入规格选项
        List<TbSpecificationOption> specificationOptions = specification.getSpecificationOptions();
        for (TbSpecificationOption specificationOption : specificationOptions) {
            specificationOption.setSpecId(tbSpecification.getId());
            specificationOptionMapper.insert(specificationOption);
        }
    }
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
	    //根据规格id查询规格对象
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);

        //根据规格id查询规格选项集合
        TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> tbSpecificationOptions = specificationOptionMapper.selectByExample(tbSpecificationOptionExample);

        //封装
        Specification specification = new Specification();
        specification.setSpecification(tbSpecification);
        specification.setSpecificationOptions(tbSpecificationOptions);
        return specification;
    }

	/**
	 * 批量删除
	 * 第三天,删除规格
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
		    //根据规格id删除对应的规格选项
            TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
            criteria.andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(tbSpecificationOptionExample);

            //删除规格
            specificationMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<Map> selectOptionList() {
		return specificationMapper.selectOptionList();
	}


}
