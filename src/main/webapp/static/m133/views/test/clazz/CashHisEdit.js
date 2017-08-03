/**
 * Created by zmax 
 * CashHisListCtrl 现金流水编辑
 */
app.controller('CashHisEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,CashHisService) {
    $log.debug("enter CashHisEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
	/**选择 类型*/
	$scope.ao_CashHis_otype=selectmap_CashHis_otype;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("CashHisEdit ctrl init "+id);
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
        $log.debug("CashHisEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_CashHis);
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
        $log.debug("CashHisEdit ctrl get id=" + id);
        if (isblank0(id)) {
            CashHisService.newget().then(function (data) {
                $log.debug("CashHisEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            CashHisService.get(id).then(function (data) {
                $log.debug("CashHisEdit ctrl get then");
                $scope.obj = data;
	            $scope.int2str();
            });
        }
    }
	/**
     * 数字转文本，要不然select无法默认
     */
    $scope.int2str = function () {
		if($scope.obj.otype)$scope.obj.otype=''+$scope.obj.otype;
    }
    /**
     * 保存
     */
    $scope.save=function(){
        $log.debug("CashHis ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            CashHisService.create($scope.obj).then(function (data) {
                $log.debug("CashHis ctrl save then");
                $scope.obj=data;
                $location.path("/CashHisShow/"+$scope.obj.id);
            });
        }else{
            CashHisService.update($scope.obj).then(function (data) {
                $log.debug("CashHis ctrl update then");
                $scope.obj=data;
                $location.path("/CashHisShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});