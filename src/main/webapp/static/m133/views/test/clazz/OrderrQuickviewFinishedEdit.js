/**
 * Created by zmax 
 * OrderrQuickviewFinishedListCtrl 订单之抢答问题观看归档编辑
 */
app.controller('OrderrQuickviewFinishedEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,OrderrQuickviewFinishedService) {
    $log.debug("enter OrderrQuickviewFinishedEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
	/**选择 支付状态*/
	$scope.ao_OrderrQuickviewFinished_status=selectmap_OrderrQuickviewFinished_status;
	/**选择 支付方式*/
	$scope.ao_OrderrQuickviewFinished_itypePay=selectmap_OrderrQuickviewFinished_itypePay;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("OrderrQuickviewFinishedEdit ctrl init "+id);
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
        $log.debug("OrderrQuickviewFinishedEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_OrderrQuickviewFinished);
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
        $log.debug("OrderrQuickviewFinishedEdit ctrl get id=" + id);
        if (isblank0(id)) {
            OrderrQuickviewFinishedService.newget().then(function (data) {
                $log.debug("OrderrQuickviewFinishedEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            OrderrQuickviewFinishedService.get(id).then(function (data) {
                $log.debug("OrderrQuickviewFinishedEdit ctrl get then");
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
        $log.debug("OrderrQuickviewFinished ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            OrderrQuickviewFinishedService.create($scope.obj).then(function (data) {
                $log.debug("OrderrQuickviewFinished ctrl save then");
                $scope.obj=data;
                $location.path("/OrderrQuickviewFinishedShow/"+$scope.obj.id);
            });
        }else{
            OrderrQuickviewFinishedService.update($scope.obj).then(function (data) {
                $log.debug("OrderrQuickviewFinished ctrl update then");
                $scope.obj=data;
                $location.path("/OrderrQuickviewFinishedShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});