/**
 * Created by zmax 
 * QuestionListCtrl 一对一问题列表
 */
app.controller('QuestionListCtrl', function ($scope,$log,$state,$location,Storage,ENV,CommonService,QuestionService) {
    $log.debug("enter QuestionList ctrl");
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
	/**选择 状态*/
	$scope.ao_Question_status=selectmap_Question_status;
	/**选择 公开状态*/
	$scope.ao_Question_ptype=selectmap_Question_ptype;
	/**选择 评论情况*/
	$scope.ao_Question_ctype=selectmap_Question_ctype;
    /**
     * 进入前
     */
    $scope.$on('$ionicView.beforeEnter', function() {
        $log.debug("QuestionList ctrl beforeEnter");
    });
    /**
     * 进入后
     */
    $scope.$on('$ionicView.afterEnter', function() {
        $log.debug("QuestionList ctrl afterEnter");
        if (ctrlReinitMap.get('QuestionListCtrl')) {
            ctrlReinitMap.remove('QuestionListCtrl');
            $log.debug("QuestionList ctrl afterEnter init");
            $scope.init();
        }
    });

    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("QuestionList ctrl init ");
	    $scope.page.where=Storage.get("QuestionList"+"QueryWhere");
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
        QuestionService.first(null,$scope.page).then(function (data) {
            $log.debug("QuestionList ctrl query then");
            $scope.addList(data);
            $scope.$broadcast('scroll.refreshComplete');
        });

    };

    /**
     * 下拉加载更多查询
     */
    $scope.more=function(){
        $log.debug("QuestionList ctrl more=========");
        if(!$scope.page.hasNextPage){
            return;
        }
        QuestionService.more($scope.page).then(function (data) {
            $log.debug("QuestionList ctrl more then");
            $scope.addList(data);
            $scope.$broadcast('scroll.infiniteScrollComplete');
        });
    };
    //////////////[统一]以上是前端C统一编写



    //////////////[统一]以下是前端C统一编写
    $scope.init();
    ctrlReinitMap.remove('QuestionListCtrl');
});
