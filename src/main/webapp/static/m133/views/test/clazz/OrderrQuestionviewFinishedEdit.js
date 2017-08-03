/**
 * Created by zmax 
 * OrderrQuestionviewFinishedListCtrl 订单之一对一问题观看归档编辑
 */
app.controller('OrderrQuestionviewFinishedEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,OrderrQuestionviewFinishedService) {
    $log.debug("enter OrderrQuestionviewFinishedEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
	/**选择 支付状态*/
	$scope.ao_OrderrQuestionviewFinished_status=selectmap_OrderrQuestionviewFinished_status;
	/**选择 支付方式*/
	$scope.ao_OrderrQuestionviewFinished_itypePay=selectmap_OrderrQuestionviewFinished_itypePay;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("OrderrQuestionviewFinishedEdit ctrl init "+id);
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
        $log.debug("OrderrQuestionviewFinishedEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_OrderrQuestionviewFinished);
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
        $log.debug("OrderrQuestionviewFinishedEdit ctrl get id=" + id);
        if (isblank0(id)) {
            OrderrQuestionviewFinishedService.newget().then(function (data) {
                $log.debug("OrderrQuestionviewFinishedEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            OrderrQuestionviewFinishedService.get(id).then(function (data) {
                $log.debug("OrderrQuestionviewFinishedEdit ctrl get then");
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
        $log.debug("OrderrQuestionviewFinished ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            OrderrQuestionviewFinishedService.create($scope.obj).then(function (data) {
                $log.debug("OrderrQuestionviewFinished ctrl save then");
                $scope.obj=data;
                $location.path("/OrderrQuestionviewFinishedShow/"+$scope.obj.id);
            });
        }else{
            OrderrQuestionviewFinishedService.update($scope.obj).then(function (data) {
                $log.debug("OrderrQuestionviewFinished ctrl update then");
                $scope.obj=data;
                $location.path("/OrderrQuestionviewFinishedShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});