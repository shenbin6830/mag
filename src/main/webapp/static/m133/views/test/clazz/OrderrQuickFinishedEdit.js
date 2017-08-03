/**
 * Created by zmax 
 * OrderrQuickFinishedListCtrl 订单之抢答问题提问归档编辑
 */
app.controller('OrderrQuickFinishedEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,OrderrQuickFinishedService) {
    $log.debug("enter OrderrQuickFinishedEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
	/**选择 支付状态*/
	$scope.ao_OrderrQuickFinished_status=selectmap_OrderrQuickFinished_status;
	/**选择 支付方式*/
	$scope.ao_OrderrQuickFinished_itypePay=selectmap_OrderrQuickFinished_itypePay;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("OrderrQuickFinishedEdit ctrl init "+id);
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
        $log.debug("OrderrQuickFinishedEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_OrderrQuickFinished);
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
        $log.debug("OrderrQuickFinishedEdit ctrl get id=" + id);
        if (isblank0(id)) {
            OrderrQuickFinishedService.newget().then(function (data) {
                $log.debug("OrderrQuickFinishedEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            OrderrQuickFinishedService.get(id).then(function (data) {
                $log.debug("OrderrQuickFinishedEdit ctrl get then");
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
		if($scope.obj.itypePay)$scope.obj.itypePay=''+$scope.obj.itypePay;
    }
    /**
     * 保存
     */
    $scope.save=function(){
        $log.debug("OrderrQuickFinished ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            OrderrQuickFinishedService.create($scope.obj).then(function (data) {
                $log.debug("OrderrQuickFinished ctrl save then");
                $scope.obj=data;
                $location.path("/OrderrQuickFinishedShow/"+$scope.obj.id);
            });
        }else{
            OrderrQuickFinishedService.update($scope.obj).then(function (data) {
                $log.debug("OrderrQuickFinished ctrl update then");
                $scope.obj=data;
                $location.path("/OrderrQuickFinishedShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});