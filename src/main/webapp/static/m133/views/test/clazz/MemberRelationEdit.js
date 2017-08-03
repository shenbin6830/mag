/**
 * Created by zmax 
 * MemberRelationListCtrl 会员父子关系编辑
 */
app.controller('MemberRelationEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,MemberRelationService) {
    $log.debug("enter MemberRelationEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("MemberRelationEdit ctrl init "+id);
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
        $log.debug("MemberRelationEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_MemberRelation);
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
        $log.debug("MemberRelationEdit ctrl get id=" + id);
        if (isblank0(id)) {
            MemberRelationService.newget().then(function (data) {
                $log.debug("MemberRelationEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            MemberRelationService.get(id).then(function (data) {
                $log.debug("MemberRelationEdit ctrl get then");
                $scope.obj = data;
	            $scope.int2str();
            });
        }
    }
	/**
     * 数字转文本，要不然select无法默认
     */
    $scope.int2str = function () {
    }
    /**
     * 保存
     */
    $scope.save=function(){
        $log.debug("MemberRelation ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            MemberRelationService.create($scope.obj).then(function (data) {
                $log.debug("MemberRelation ctrl save then");
                $scope.obj=data;
                $location.path("/MemberRelationShow/"+$scope.obj.id);
            });
        }else{
            MemberRelationService.update($scope.obj).then(function (data) {
                $log.debug("MemberRelation ctrl update then");
                $scope.obj=data;
                $location.path("/MemberRelationShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});