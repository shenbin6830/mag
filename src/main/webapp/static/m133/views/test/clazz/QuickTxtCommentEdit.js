/**
 * Created by zmax 
 * QuickTxtCommentListCtrl 抢答的评价编辑
 */
app.controller('QuickTxtCommentEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,QuickTxtCommentService) {
    $log.debug("enter QuickTxtCommentEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
	/**选择 评论情况*/
	$scope.ao_QuickTxtComment_ctype=selectmap_QuickTxtComment_ctype;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("QuickTxtCommentEdit ctrl init "+id);
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
        $log.debug("QuickTxtCommentEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_QuickTxtComment);
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
        $log.debug("QuickTxtCommentEdit ctrl get id=" + id);
        if (isblank0(id)) {
            QuickTxtCommentService.newget().then(function (data) {
                $log.debug("QuickTxtCommentEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            QuickTxtCommentService.get(id).then(function (data) {
                $log.debug("QuickTxtCommentEdit ctrl get then");
                $scope.obj = data;
	            $scope.int2str();
            });
        }
    }
	/**
     * 数字转文本，要不然select无法默认
     */
    $scope.int2str = function () {
		if($scope.obj.ctype)$scope.obj.ctype=''+$scope.obj.ctype;
    }
    /**
     * 保存
     */
    $scope.save=function(){
        $log.debug("QuickTxtComment ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            QuickTxtCommentService.create($scope.obj).then(function (data) {
                $log.debug("QuickTxtComment ctrl save then");
                $scope.obj=data;
                $location.path("/QuickTxtCommentShow/"+$scope.obj.id);
            });
        }else{
            QuickTxtCommentService.update($scope.obj).then(function (data) {
                $log.debug("QuickTxtComment ctrl update then");
                $scope.obj=data;
                $location.path("/QuickTxtCommentShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});