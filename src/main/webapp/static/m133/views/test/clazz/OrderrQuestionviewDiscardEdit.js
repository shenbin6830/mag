/**
 * Created by zmax 
 * OrderrQuestionviewDiscardListCtrl 订单之一对一问题观看放弃编辑
 */
app.controller('OrderrQuestionviewDiscardEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,OrderrQuestionviewDiscardService) {
    $log.debug("enter OrderrQuestionviewDiscardEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
	/**选择 支付状态*/
	$scope.ao_OrderrQuestionviewDiscard_status=selectmap_OrderrQuestionviewDiscard_status;
	/**选择 支付方式*/
	$scope.ao_OrderrQuestionviewDiscard_itypePay=selectmap_OrderrQuestionviewDiscard_itypePay;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("OrderrQuestionviewDiscardEdit ctrl init "+id);
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
        $log.debug("OrderrQuestionviewDiscardEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_OrderrQuestionviewDiscard);
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
        $log.debug("OrderrQuestionviewDiscardEdit ctrl get id=" + id);
        if (isblank0(id)) {
            OrderrQuestionviewDiscardService.newget().then(function (data) {
                $log.debug("OrderrQuestionviewDiscardEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            OrderrQuestionviewDiscardService.get(id).then(function (data) {
                $log.debug("OrderrQuestionviewDiscardEdit ctrl get then");
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
        $log.debug("OrderrQuestionviewDiscard ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            OrderrQuestionviewDiscardService.create($scope.obj).then(function (data) {
                $log.debug("OrderrQuestionviewDiscard ctrl save then");
                $scope.obj=data;
                $location.path("/OrderrQuestionviewDiscardShow/"+$scope.obj.id);
            });
        }else{
            OrderrQuestionviewDiscardService.update($scope.obj).then(function (data) {
                $log.debug("OrderrQuestionviewDiscard ctrl update then");
                $scope.obj=data;
                $location.path("/OrderrQuestionviewDiscardShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});