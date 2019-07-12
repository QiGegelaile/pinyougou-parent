//品牌控制层
app.controller("brandController",function($scope,$http,brandService,$controller){

    $controller("baseController",{$scope:$scope});

    //查询品牌列表
    //brandService  品牌服务
    //读取列表数据绑定到表单中
    $scope.findAll = function(){
        brandService.findAll().success(function(data){
            $scope.list = data;
        });
    }

    //localhost:8080/admin/brand.html
    //localhost:8080/brand/findByPage.do
    $scope.findByPage = function(currPage,pageSize) {
        brandService.findByPage(currPage,pageSize).success(function(response){
            //response.rows
            //response.total
            $scope.list=response.rows;
            $scope.paginationConf.totalItems = response.total;
        })
    }


    //查询实体
    $scope.findOne = function(id) {
        brandService.findOne(id).success(function(response){
            $scope.entity = response;
        });
    }
    //保存
    $scope.save = function() {
        var service ; //服务层对象
        if($scope.entity.id != null) {
            service =brandService.update($scope.entity); //修改
        }else {
            service =brandService.save($scope.entity); //增加
        }
        service.success(function(response){
            if(response.success) {
                //重新查询
                $scope.reloadList(); //重新加载
            }else {
                alert(response.message); //message 消息,留言,通报
            }        //response 响应

        });
    }


//批量删除,
    $scope.delete = function(){
        //获取选中的复选框
        brandService.delete($scope.selectIds).success(function(response){
            if(response.success) {
                $scope.reloadList(); //刷新列表
            }else {
                alert(response.message); //响应消息
            }
            $scope.selectIds = [];
        });
    }

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function(currPage,pageSize) {
        brandService.search(currPage,pageSize,$scope.searchEntity).success(function(response){
            //response.rows
            //response.total
            $scope.list=response.rows; //更新当前数据
            $scope.paginationConf.totalItems = response.total; //更新每页显示条数
        })        //pagina 分页
    }
})