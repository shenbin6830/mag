/**
 * Created by zmax 
 * ScorememberStatiDayListCtrl 会员积分日统计编辑
 */
app.controller('ScorememberStatiDayEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,ScorememberStatiDayService) {
    $log.debug("enter ScorememberStatiDayEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("ScorememberStatiDayEdit ctrl init "+id);
        if($scope.getlocal()){
            $scope.int2str();
        }else{
            $scope.get();
        }
    };
    /**
     * 获取本地对象
     * @returns {boolean} 是否取到
     */

    $scope.getlocal=function(){
        $log.debug("ScorememberStatiDayEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_ScorememberStatiDay);
        }else{
            $scope.obj=Storage.get(LOCAL_TMP_OBJ);
        }
        if(null==$scope.obj)
            return false;
        //$log.debug($scope.obj);
        return true;
    };
    /**
     * 获取网络对象
     */
    $scope.get=function() {
        $log.debug("ScorememberStatiDayEdit ctrl get id=" + id);
        if (isblank0(id)) {
            ScorememberStatiDayService.newget().then(function (data) {
                $log.debug("ScorememberStatiDayEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            ScorememberStatiDayService.get(id).then(function (data) {
                $log.debug("ScorememberStatiDayEdit ctrl get then");
                $scope.obj = data;
	            $scope.int2str();
            });
        }
    }
	/**
     * 数字转文本，要不然select无法默认
     */
    $scope.int2str = function () {
    }
    /**
     * 保存
     */
    $scope.save=function(){
        $log.debug("ScorememberStatiDay ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            ScorememberStatiDayService.create($scope.obj).then(function (data) {
                $log.debug("ScorememberStatiDay ctrl save then");
                $scope.obj=data;
                $location.path("/ScorememberStatiDayShow/"+$scope.obj.id);
            });
        }else{
            ScorememberStatiDayService.update($scope.obj).then(function (data) {
                $log.debug("ScorememberStatiDay ctrl update then");
                $scope.obj=data;
                $location.path("/ScorememberStatiDayShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});