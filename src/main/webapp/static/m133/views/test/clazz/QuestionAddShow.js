/**
 * QuestionAddShow，一对一问题之追加详细
 */
app.controller('QuestionAddShowCtrl', function ($scope,$log,$state,$stateParams,Storage,ENV,QuestionAddService) {
    $log.debug("enter QuestionAddShow ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数*/
    var id = $stateParams.id;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("QuestionAddShow ctrl init "+id);
        $scope.get();
    };
    /**
     * 获取本地对象
     */
    $scope.getlocal=function(){
        $log.debug("QuestionAddShow ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_QuestionAdd);
        }else{
            $scope.obj=Storage.get(LOCAL_TMP_OBJ);
        }
        $log.debug($scope.obj);
    };
    /**
     * 获取网络对象
     */
    $scope.get=function() {
        $log.debug("QuestionAddShow ctrl get id=" + id);
        if (isblank0(id)) {
            QuestionAddService.newget().then(function (data) {
                $log.debug("QuestionAddShow ctrl newget then");
                $scope.obj = data;
            });
        } else {
            QuestionAddService.get(id).then(function (data) {
                $log.debug("QuestionAddShow ctrl get then");
                $scope.obj = data;
            });
        }
    }
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});