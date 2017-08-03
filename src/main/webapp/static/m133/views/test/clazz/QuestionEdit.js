/**
 * Created by zmax 
 * QuestionListCtrl 一对一问题编辑
 */
app.controller('QuestionEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,QuestionService) {
    $log.debug("enter QuestionEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
	/**选择 状态*/
	$scope.ao_Question_status=selectmap_Question_status;
	/**选择 公开状态*/
	$scope.ao_Question_ptype=selectmap_Question_ptype;
	/**选择 评论情况*/
	$scope.ao_Question_ctype=selectmap_Question_ctype;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("QuestionEdit ctrl init "+id);
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
        $log.debug("QuestionEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_Question);
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
        $log.debug("QuestionEdit ctrl get id=" + id);
        if (isblank0(id)) {
            QuestionService.newget().then(function (data) {
                $log.debug("QuestionEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            QuestionService.get(id).then(function (data) {
                $log.debug("QuestionEdit ctrl get then");
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
		if($scope.obj.ctype)$scope.obj.ctype=''+$scope.obj.ctype;
    }
    /**
     * 保存
     */
    $scope.save=function(){
        $log.debug("Question ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            QuestionService.create($scope.obj).then(function (data) {
                $log.debug("Question ctrl save then");
                $scope.obj=data;
                $location.path("/QuestionShow/"+$scope.obj.id);
            });
        }else{
            QuestionService.update($scope.obj).then(function (data) {
                $log.debug("Question ctrl update then");
                $scope.obj=data;
                $location.path("/QuestionShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});