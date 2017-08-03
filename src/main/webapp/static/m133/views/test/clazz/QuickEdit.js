/**
 * Created by zmax 
 * QuickListCtrl 抢答编辑
 */
app.controller('QuickEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,QuickService) {
    $log.debug("enter QuickEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
	/**选择 状态*/
	$scope.ao_Quick_status=selectmap_Quick_status;
	/**选择 公开状态*/
	$scope.ao_Quick_ptype=selectmap_Quick_ptype;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("QuickEdit ctrl init "+id);
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
        $log.debug("QuickEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_Quick);
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
        $log.debug("QuickEdit ctrl get id=" + id);
        if (isblank0(id)) {
            QuickService.newget().then(function (data) {
                $log.debug("QuickEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            QuickService.get(id).then(function (data) {
                $log.debug("QuickEdit ctrl get then");
                $scope.obj = data;
	            $scope.int2str();
            });
        }
    }
	/**
     * 数字转文本，要不然select无法默认
     */
    $scope.int2str = function () {
		if($scope.obj.status)$scope.obj.status=''+$scope.obj.status;
		if($scope.obj.ptype)$scope.obj.ptype=''+$scope.obj.ptype;
    }
    /**
     * 保存
     */
    $scope.save=function(){
        $log.debug("Quick ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            QuickService.create($scope.obj).then(function (data) {
                $log.debug("Quick ctrl save then");
                $scope.obj=data;
                $location.path("/QuickShow/"+$scope.obj.id);
            });
        }else{
            QuickService.update($scope.obj).then(function (data) {
                $log.debug("Quick ctrl update then");
                $scope.obj=data;
                $location.path("/QuickShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});