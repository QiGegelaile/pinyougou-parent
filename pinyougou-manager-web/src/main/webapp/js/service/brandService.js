app.service("brandService",function($http){
    //在服务中定义方法。
    //查询所有品牌,列表
    this.findAll = function() {
        return  $http.get("../brand/findAll.do");
    }

    this.findByPage = function(currPage,pageSize) {
        return  $http.get("../brand/findByPage.do?currPage="+currPage+"&pageSize="+pageSize+"");
    }
    //根据ID查找
    this.findOne = function(id) {
        return $http.get("../brand/findOne.do?id="+id);
    }

    this.save = function(entity) {
        return $http.post("../brand/save.do",entity);
    }
    //修改
    this.update = function(entity) {
        return $http.post("../brand/update.do",entity);
    }
    //根据ID查询
    this.delete = function(selectIds) {
        return  $http.get("../brand/delete.do?ids="+selectIds);
    }
    //根据条件查询
    this.search = function(currPage,pageSize,searchEntity) {
        return  $http.post("../brand/search.do?currPage="+currPage+"&pageSize="+pageSize+"",searchEntity);
    }
    //选择的选项列表
    this.selectOptionList = function() {
        return  $http.get("../brand/selectOptionList.do");
    }
});