package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellersgoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;
    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }
    @RequestMapping("/findByPage")
    public PageResult<TbBrand> findByPage(Integer currPage,Integer pageSize) {
        return brandService.findPage(currPage,pageSize);
    }

    @RequestMapping("/save")
    public Result add(@RequestBody TbBrand tbBrand){
        try {
            brandService.add(tbBrand);
            return new Result(true,"保存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(false,"保存失败");
    }

    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand tbBrand){
        try {
            brandService.update(tbBrand);
            return new Result(true,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(true,"更新失败");
    }

    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            brandService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(true,"删除失败");
    }

    //条件查询
    @RequestMapping("/search")
    public PageResult<TbBrand> search(@RequestBody TbBrand tbBrand,Integer currPage,Integer pageSize) {
           return brandService.findPage(tbBrand,currPage, pageSize);
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() {
        return brandService.selectOptionList();
    }


}
