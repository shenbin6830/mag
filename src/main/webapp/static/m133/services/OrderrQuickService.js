/**
 * 服务:OrderrQuick，订单之抢答问题提问
 */
app
    .service('OrderrQuickService', function($rootScope,$q,$http,$log,ENV) {
        $log.debug("OrderrQuickService in");
        var aurl=ENV.api + "/OrderrQuick";
        return {
            /**
             * 初次查询
             * @param _where 条件 可以为空
             * @param _page 页码对象，如果为空使用全局页码对象
             */
            first: function(_page) {
                return this.query(_page);
            },
            /**
             * 更多查询
             * @param _page 页码对象，如果为空使用全局页码对象
             */
            more: function(_page) {
                if(_page && _page.hasNextPage){
                    _page.pageNo=_page.pageNo+1;
                    return this.query(_page);
                }
            },
            /**
             * 查询Promise版
             * @param _page 页码对象，如果为空使用全局页码对象
             * @returns {*}
             */
            query: function(_page) {
                var thispage=(_page)?_page:_Page;
                var deferred = $q.defer();
                var url =aurl+"/query";
                $http({
                    method: 'GET',
                    url: url,
                    params: thispage
                }).success(
                    function (data, status, header, config) {
                        if(data&&_page&&data.length<_page.pageSize){
                            _page.hasNextPage=false;
                        }
                        deferred.resolve(data);
                    }
                );
                return deferred.promise;
            },
            /**
             * 获取一个新对象，加上各种LIST让用户选择，和get/0一个效果
             * @param _page 页码对象，如果为空使用全局页码对象
             * @returns {*}
             */
            newget: function(_page) {
                var thispage=(_page)?_page:_Page;
                var deferred = $q.defer();
                var url =aurl+"/getnew";
                $http({
                    method: 'GET',
                    url: url,
                    params: thispage
                }).success(
                    function (data, status, header, config) {
                        deferred.resolve(data);
                    }
                );
                return deferred.promise;
            }, 
			/**
             * 创建Promise版
             * @param OrderrQuick 要被创建的对象
             * @param _page 页码对象，如果为空使用全局页码对象
             */
            create: function(OrderrQuick,_page) {
                var thispage=(_page)?_page:_Page;
                var tmpparam=_.omit(OrderrQuick, function(value, key, object) {
                    return _.isObject(value) || _.isArray(value)
                        || key.indexOf("String") != -1 || key.indexOf("gmt") != -1
                        ;
                });
                tmpparam=_.extend(tmpparam, {cmd: thispage.cmd});
                $log.debug("OrderrQuickService create");
                $log.debug(tmpparam);
                var deferred = $q.defer();
                var url =aurl+"/create";
                $http({
                    method: 'POST',
                    url: url,
                    params: tmpparam
                }).success(
                    function (data, status, header, config) {
                        deferred.resolve(data);
                    }
                );
                return deferred.promise;
            },
            /**
             * 更新Promise版
             * @param OrderrQuick 要被更新的对象
             * @param _page 页码对象，如果为空使用全局页码对象
             */
            update: function(OrderrQuick,_page) {
                var thispage=(_page)?_page:_Page;
                var tmpparam=_.omit(OrderrQuick, function(value, key, object) {
                    return _.isObject(value) || _.isArray(value)
                        || key.indexOf("String") != -1 || key.indexOf("gmt") != -1
                        ;
                });
                tmpparam=_.extend(tmpparam, {cmd: thispage.cmd});
                $log.debug("OrderrQuickService update");
                $log.debug(tmpparam);
                var deferred = $q.defer();
                var url =aurl+"/update";
                $http({
                    method: 'POST',
                    url: url,
                    params: tmpparam
                }).success(
                    function (data, status, header, config) {
                        deferred.resolve(data);
                    }
                );
                return deferred.promise;
            }, 
            /**
             * 获取数据，通过网络查询
             * @param id 如果id=0，返回一个新对象
             * @param _page 页码对象，如果为空使用全局页码对象
             * @returns {*}
             */
            get: function(id,_page) {
                var thispage=(_page)?_page:_Page;
                var deferred = $q.defer();
                var url =aurl+"/get/"+id;
                $http({
                    method: 'GET',
                    url: url,
                    params: thispage
                }).success(
                    function (data, status, header, config) {
                        deferred.resolve(data);
                    }
                );
                return deferred.promise;
            },
            newObj: function() {
                return _.clone(_OrderrQuick);
            }
        };
    })

