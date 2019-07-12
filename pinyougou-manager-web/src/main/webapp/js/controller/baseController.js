
app.controller("baseController",function($scope){
    //分页导航条绑定对象
    //分页控件配置，currentPage:当前页  totalItems：总记录数 itemsPerPage：每页记录数 perPageOptions：分页选项
    //onchange：当页码变更后自动触发的方法
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 100,
        itemsPerPage: 5,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            $scope.reloadList();
        }
    };
    //刷新列表，重新加载列表，数据
    $scope.reloadList = function(){
        // $scope.findByPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
        //切换页码          paginationConf  分页设置    currentPage 当前页    itemsPerPage  每页显示条数
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    }

 //收集勾选的id的数组,以及方法
    $scope.selectIds = []; //保存勾选的品牌id

    $scope.updateSelection = function(event,id) {
        if(event.target.checked) {
            $scope.selectIds.push(id);
        }else {
            var index = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(index,1);
        }
    }

    //作用
   $scope.format = function(jsonStr,key) {
        var value = "";
        //JSON格式字符串转化为JSON数组
       var json = JSON.parse(jsonStr);
       //迭代JSON数组获取每一个JSON对象
       for(var i=0;i<json.length;i++) {

           var jsonObject = json[i];
           //获取对象中key对应值
           var v = jsonObject[key];
           //将值拼接value中，最后一次不拼接,
           if(i == json.length - 1) {
               value += v
           }else {
               value += v + ",";
           }

       }

       return value;
   }

});