/**
 * Created by zmax 
 * QuickTxtListCtrl 抢答回答编辑
 */
app.controller('QuickTxtEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,QuickTxtService) {
    $log.debug("enter QuickTxtEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
	/**选择 中奖状态*/
	$scope.ao_QuickTxt_statusWin=selectmap_QuickTxt_statusWin;
	/**选择 提问者评价*/
	$scope.ao_QuickTxt_ctype=selectmap_QuickTxt_ctype;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("QuickTxtEdit ctrl init "+id);
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
        $log.debug("QuickTxtEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_QuickTxt);
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
        $log.debug("QuickTxtEdit ctrl get id=" + id);
        if (isblank0(id)) {
            QuickTxtService.newget().then(function (data) {
                $log.debug("QuickTxtEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            QuickTxtService.get(id).then(function (data) {
                $log.debug("QuickTxtEdit ctrl get then");
                $scope.obj = data;
	            $scope.int2str();
            });
        }
    }
	/**
     * 数字转文本，要不然select无法默认
     */
    $scope.int2str = function () {
		if($scope.obj.statusWin)$scope.obj.statusWin=''+$scope.obj.statusWin;
		if($scope.obj.ctype)$scope.obj.ctype=''+$scope.obj.ctype;
    }
    /**
     * 保存
     */
    $scope.save=function(){
        $log.debug("QuickTxt ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            QuickTxtService.create($scope.obj).then(function (data) {
                $log.debug("QuickTxt ctrl save then");
                $scope.obj=data;
                $location.path("/QuickTxtShow/"+$scope.obj.id);
            });
        }else{
            QuickTxtService.update($scope.obj).then(function (data) {
                $log.debug("QuickTxt ctrl update then");
                $scope.obj=data;
                $location.path("/QuickTxtShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});