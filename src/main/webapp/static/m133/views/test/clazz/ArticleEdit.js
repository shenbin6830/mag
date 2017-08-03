/**
 * Created by zmax 
 * ArticleListCtrl 文章编辑
 */
app.controller('ArticleEditCtrl', function ($scope,$log,$state,$stateParams,$location,Storage,ENV,ArticleService,uploadService) {
    $log.debug("enter ArticleEdit ctrl");
    //////////////前端C统一的函数包括 init addList getlocal get
    //////////////自行修改的包括：

    //////////////[统一]以下是前端C统一编写
    /**参数 0表示新建 */
    var id = $stateParams.id;
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("ArticleEdit ctrl init "+id);
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
        $log.debug("ArticleEdit ctrl getlocal id="+id);
        if(isblank0(id)){
            $scope.obj= _.clone(_Article);
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
        $log.debug("ArticleEdit ctrl get id=" + id);
        if (isblank0(id)) {
            ArticleService.newget().then(function (data) {
                $log.debug("ArticleEdit ctrl newget then");
                $scope.obj = data;
	            $scope.int2str();
            });
        } else {
            ArticleService.get(id).then(function (data) {
                $log.debug("ArticleEdit ctrl get then");
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
        $log.debug("Article ctrl save id="+id);
        $log.debug($scope.obj)
        if(isblank0(id)) {
            ArticleService.create($scope.obj).then(function (data) {
                $log.debug("Article ctrl save then");
                $scope.obj=data;
                $location.path("/ArticleShow/"+$scope.obj.id);
            });
        }else{
            ArticleService.update($scope.obj).then(function (data) {
                $log.debug("Article ctrl update then");
                $scope.obj=data;
                $location.path("/ArticleShow/"+$scope.obj.id);
            });
        }
    };
    /**
     * 上传图片
     * @param files 文件们
     * @param obj 返回对象
     * @param objfield 返回对象的字段
     */
    $scope.uploadImg = function (files,obj,objfield) {
        if(!files)
            return;
        var file=files[0];
        uploadService.upload(file).then(
            function(data){
                //console.log("upload.ret=",data);
                obj[objfield]=data.msg;
            }
        );
    };
    //////////////[统一]以上是前端C统一编写

    //////////////[统一]
    $scope.init();
});