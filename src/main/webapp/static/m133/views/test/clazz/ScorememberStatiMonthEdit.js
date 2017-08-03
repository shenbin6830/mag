/**
 * Created by zmax 
 * ScorememberStatiMonthListCtrl 会员积分月统计编辑
 */
app.controller('ScorememberStatiMonthEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,ScorememberStatiMonthService) {
    $log.debug("enter ScorememberStatiMonthEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("ScorememberStatiMonthEdit ctrl init "+id);
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
        $log.debug("ScorememberStatiMonthEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_ScorememberStatiMonth);
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
        $log.debug("ScorememberStatiMonthEdit ctrl get id=" + id);
        if (isblank0(id)) {
            ScorememberStatiMonthService.newget().then(function (data) {
                $log.debug("ScorememberStatiMonthEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            ScorememberStatiMonthService.get(id).then(function (data) {
                $log.debug("ScorememberStatiMonthEdit ctrl get then");
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
        $log.debug("ScorememberStatiMonth ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            ScorememberStatiMonthService.create($scope.obj).then(function (data) {
                $log.debug("ScorememberStatiMonth ctrl save then");
                $scope.obj=data;
                $location.path("/ScorememberStatiMonthShow/"+$scope.obj.id);
            });
        }else{
            ScorememberStatiMonthService.update($scope.obj).then(function (data) {
                $log.debug("ScorememberStatiMonth ctrl update then");
                $scope.obj=data;
                $location.path("/ScorememberStatiMonthShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});