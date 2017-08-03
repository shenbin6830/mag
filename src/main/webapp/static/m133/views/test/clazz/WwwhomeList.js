/**
 * Created by zmax 
 * WwwhomeListCtrl 手机页首页配置列表
 */
app.controller('WwwhomeListCtrl', function ($scope,$log,$state,$location,Storage,ENV,CommonService,WwwhomeService) {
    $log.debug("enter WwwhomeList ctrl");
    //////////////前端C统一的函数包括 init addList checkHeadAll show page* query searchquery searchquerycancel
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**页码*/
    $scope.page=_.clone(_Page);
    $scope.page.totalpage=1;
    /**页面显示的列表*/
    $scope.list=[];
    /**已经被选择的map*/
    $scope.ao_selected={};
    /**
     * 进入前
     */
    $scope.$on('$ionicView.beforeEnter', function() {
        $log.debug("WwwhomeList ctrl beforeEnter");
    });
    /**
     * 进入后
     */
    $scope.$on('$ionicView.afterEnter', function() {
        $log.debug("WwwhomeList ctrl afterEnter");
        if (ctrlReinitMap.get('WwwhomeListCtrl')) {
            ctrlReinitMap.remove('WwwhomeListCtrl');
            $log.debug("WwwhomeList ctrl afterEnter init");
            $scope.init();
        }
    });

    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("WwwhomeList ctrl init ");
	    $scope.page.where=Storage.get("WwwhomeList"+"QueryWhere");
		$scope.page.pageNo=1;
        $scope.page.hasNextPage=false;
        $scope.list=[];
        $scope.first();
    };
    /**
     * 上拉刷新
     */
    $scope.doRefresh=function(){
        $scope.init();
    };
    /**
     * 给list上加数据
     * @param data
     */
    $scope.addList=function(data){
        angular.forEach(data, function (item) {
            $scope.list.push(item);
        });
        if(data && data.length < $scope.page.pageSize){
            $scope.page.hasNextPage=false;
        }else{
            $scope.page.hasNextPage=true;
        }
    };

    /**
     * 第一次查询
     */
    $scope.first=function(){
        WwwhomeService.first(null,$scope.page).then(function (data) {
            $log.debug("WwwhomeList ctrl query then");
            $scope.addList(data);
            $scope.$broadcast('scroll.refreshComplete');
        });

    };

    /**
     * 下拉加载更多查询
     */
    $scope.more=function(){
        $log.debug("WwwhomeList ctrl more=========");
        if(!$scope.page.hasNextPage){
            return;
        }
        WwwhomeService.more($scope.page).then(function (data) {
            $log.debug("WwwhomeList ctrl more then");
            $scope.addList(data);
            $scope.$broadcast('scroll.infiniteScrollComplete');
        });
    };
    //////////////[统一]以上是前端C统一编写



    //////////////[统一]以下是前端C统一编写
    $scope.init();
    ctrlReinitMap.remove('WwwhomeListCtrl');
});
