/**
 * Created by zmax 
 * CashmemberStatiDayListCtrl 会员现金日统计编辑
 */
app.controller('CashmemberStatiDayEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,CashmemberStatiDayService) {
    $log.debug("enter CashmemberStatiDayEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("CashmemberStatiDayEdit ctrl init "+id);
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
        $log.debug("CashmemberStatiDayEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_CashmemberStatiDay);
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
        $log.debug("CashmemberStatiDayEdit ctrl get id=" + id);
        if (isblank0(id)) {
            CashmemberStatiDayService.newget().then(function (data) {
                $log.debug("CashmemberStatiDayEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            CashmemberStatiDayService.get(id).then(function (data) {
                $log.debug("CashmemberStatiDayEdit ctrl get then");
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
        $log.debug("CashmemberStatiDay ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            CashmemberStatiDayService.create($scope.obj).then(function (data) {
                $log.debug("CashmemberStatiDay ctrl save then");
                $scope.obj=data;
                $location.path("/CashmemberStatiDayShow/"+$scope.obj.id);
            });
        }else{
            CashmemberStatiDayService.update($scope.obj).then(function (data) {
                $log.debug("CashmemberStatiDay ctrl update then");
                $scope.obj=data;
                $location.path("/CashmemberStatiDayShow/"+$scope.obj.id);
            });
        }
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});