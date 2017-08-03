/**
 * Created by zmax 
 * QuestionLinkMemberViewListCtrl 观看问题的会员编辑
 */
app.controller('QuestionLinkMemberViewEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,QuestionLinkMemberViewService) {
    $log.debug("enter QuestionLinkMemberViewEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
	/**选择 评论情况*/
	$scope.ao_QuestionLinkMemberView_ctype=selectmap_QuestionLinkMemberView_ctype;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("QuestionLinkMemberViewEdit ctrl init "+id);
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
        $log.debug("QuestionLinkMemberViewEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_QuestionLinkMemberView);
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
        $log.debug("QuestionLinkMemberViewEdit ctrl get id=" + id);
        if (isblank0(id)) {
            QuestionLinkMemberViewService.newget().then(function (data) {
                $log.debug("QuestionLinkMemberViewEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            QuestionLinkMemberViewService.get(id).then(function (data) {
                $log.debug("QuestionLinkMemberViewEdit ctrl get then");
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
        $log.debug("QuestionLinkMemberView ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            QuestionLinkMemberViewService.create($scope.obj).then(function (data) {
                $log.debug("QuestionLinkMemberView ctrl save then");
                $scope.obj=data;
                $location.path("/QuestionLinkMemberViewShow/"+$scope.obj.id);
            });
        }else{
            QuestionLinkMemberViewService.update($scope.obj).then(function (data) {
                $log.debug("QuestionLinkMemberView ctrl update then");
                $scope.obj=data;
                $location.path("/QuestionLinkMemberViewShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});