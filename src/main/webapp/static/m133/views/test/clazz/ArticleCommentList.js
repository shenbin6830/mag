/**
 * Created by zmax 
 * ArticleCommentListCtrl 文章的评论列表
 */
app.controller('ArticleCommentListCtrl', function ($scope,$log,$state,$location,Storage,ENV,CommonService,ArticleCommentService) {
    $log.debug("enter ArticleCommentList ctrl");
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
        $log.debug("ArticleCommentList ctrl beforeEnter");
    });
    /**
     * 进入后
     */
    $scope.$on('$ionicView.afterEnter', function() {
        $log.debug("ArticleCommentList ctrl afterEnter");
        if (ctrlReinitMap.get('ArticleCommentListCtrl')) {
            ctrlReinitMap.remove('ArticleCommentListCtrl');
            $log.debug("ArticleCommentList ctrl afterEnter init");
            $scope.init();
        }
    });

    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("ArticleCommentList ctrl init ");
	    $scope.page.where=Storage.get("ArticleCommentList"+"QueryWhere");
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
        ArticleCommentService.first(null,$scope.page).then(function (data) {
            $log.debug("ArticleCommentList ctrl query then");
            $scope.addList(data);
            $scope.$broadcast('scroll.refreshComplete');
        });

    };

    /**
     * 下拉加载更多查询
     */
    $scope.more=function(){
        $log.debug("ArticleCommentList ctrl more=========");
        if(!$scope.page.hasNextPage){
            return;
        }
        ArticleCommentService.more($scope.page).then(function (data) {
            $log.debug("ArticleCommentList ctrl more then");
            $scope.addList(data);
            $scope.$broadcast('scroll.infiniteScrollComplete');
        });
    };
    //////////////[统一]以上是前端C统一编写



    //////////////[统一]以下是前端C统一编写
    $scope.init();
    ctrlReinitMap.remove('ArticleCommentListCtrl');
});
