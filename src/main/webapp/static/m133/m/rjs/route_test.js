/**
 * 路由器 之 表格对象
 * Created by zmax
 */

'use strict';
app.config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
         //手机页首页配置列表
        .state('tab.WwwhomeList', {
            url: '/WwwhomeList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/WwwhomeList.html',
                    controller: 'WwwhomeListCtrl'
                }
            }
        })
         //手机页首页配置详细
        .state('tab.WwwhomeShow', {
            url: '/WwwhomeShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/WwwhomeShow.html',
                    controller: 'WwwhomeShowCtrl'
                }
            }
        })
         //手机页首页配置编辑
        .state('tab.WwwhomeEdit', {
            url: '/WwwhomeEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/WwwhomeEdit.html',
                    controller: 'WwwhomeEditCtrl'
                }
            }
        })
         //会员父子关系列表
        .state('tab.MemberRelationList', {
            url: '/MemberRelationList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/MemberRelationList.html',
                    controller: 'MemberRelationListCtrl'
                }
            }
        })
         //会员父子关系详细
        .state('tab.MemberRelationShow', {
            url: '/MemberRelationShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/MemberRelationShow.html',
                    controller: 'MemberRelationShowCtrl'
                }
            }
        })
         //会员父子关系编辑
        .state('tab.MemberRelationEdit', {
            url: '/MemberRelationEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/MemberRelationEdit.html',
                    controller: 'MemberRelationEditCtrl'
                }
            }
        })
         //签到列表
        .state('tab.SigninList', {
            url: '/SigninList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/SigninList.html',
                    controller: 'SigninListCtrl'
                }
            }
        })
         //签到详细
        .state('tab.SigninShow', {
            url: '/SigninShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/SigninShow.html',
                    controller: 'SigninShowCtrl'
                }
            }
        })
         //签到编辑
        .state('tab.SigninEdit', {
            url: '/SigninEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/SigninEdit.html',
                    controller: 'SigninEditCtrl'
                }
            }
        })
         //文章列表
        .state('tab.ArticleList', {
            url: '/ArticleList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticleList.html',
                    controller: 'ArticleListCtrl'
                }
            }
        })
         //文章详细
        .state('tab.ArticleShow', {
            url: '/ArticleShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticleShow.html',
                    controller: 'ArticleShowCtrl'
                }
            }
        })
         //文章编辑
        .state('tab.ArticleEdit', {
            url: '/ArticleEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticleEdit.html',
                    controller: 'ArticleEditCtrl'
                }
            }
        })
         //文章内容列表
        .state('tab.ArticleExtTxtList', {
            url: '/ArticleExtTxtList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticleExtTxtList.html',
                    controller: 'ArticleExtTxtListCtrl'
                }
            }
        })
         //文章内容详细
        .state('tab.ArticleExtTxtShow', {
            url: '/ArticleExtTxtShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticleExtTxtShow.html',
                    controller: 'ArticleExtTxtShowCtrl'
                }
            }
        })
         //文章内容编辑
        .state('tab.ArticleExtTxtEdit', {
            url: '/ArticleExtTxtEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticleExtTxtEdit.html',
                    controller: 'ArticleExtTxtEditCtrl'
                }
            }
        })
         //频道列表
        .state('tab.ArticlechannelList', {
            url: '/ArticlechannelList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticlechannelList.html',
                    controller: 'ArticlechannelListCtrl'
                }
            }
        })
         //频道详细
        .state('tab.ArticlechannelShow', {
            url: '/ArticlechannelShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticlechannelShow.html',
                    controller: 'ArticlechannelShowCtrl'
                }
            }
        })
         //频道编辑
        .state('tab.ArticlechannelEdit', {
            url: '/ArticlechannelEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticlechannelEdit.html',
                    controller: 'ArticlechannelEditCtrl'
                }
            }
        })
         //文章的评论列表
        .state('tab.ArticleCommentList', {
            url: '/ArticleCommentList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticleCommentList.html',
                    controller: 'ArticleCommentListCtrl'
                }
            }
        })
         //文章的评论详细
        .state('tab.ArticleCommentShow', {
            url: '/ArticleCommentShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticleCommentShow.html',
                    controller: 'ArticleCommentShowCtrl'
                }
            }
        })
         //文章的评论编辑
        .state('tab.ArticleCommentEdit', {
            url: '/ArticleCommentEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ArticleCommentEdit.html',
                    controller: 'ArticleCommentEditCtrl'
                }
            }
        })
         //短消息列表
        .state('tab.MessageList', {
            url: '/MessageList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/MessageList.html',
                    controller: 'MessageListCtrl'
                }
            }
        })
         //短消息详细
        .state('tab.MessageShow', {
            url: '/MessageShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/MessageShow.html',
                    controller: 'MessageShowCtrl'
                }
            }
        })
         //短消息编辑
        .state('tab.MessageEdit', {
            url: '/MessageEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/MessageEdit.html',
                    controller: 'MessageEditCtrl'
                }
            }
        })
         //现金流水列表
        .state('tab.CashHisList', {
            url: '/CashHisList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/CashHisList.html',
                    controller: 'CashHisListCtrl'
                }
            }
        })
         //现金流水详细
        .state('tab.CashHisShow', {
            url: '/CashHisShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/CashHisShow.html',
                    controller: 'CashHisShowCtrl'
                }
            }
        })
         //现金流水编辑
        .state('tab.CashHisEdit', {
            url: '/CashHisEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/CashHisEdit.html',
                    controller: 'CashHisEditCtrl'
                }
            }
        })
         //会员现金日统计列表
        .state('tab.CashmemberStatiDayList', {
            url: '/CashmemberStatiDayList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/CashmemberStatiDayList.html',
                    controller: 'CashmemberStatiDayListCtrl'
                }
            }
        })
         //会员现金日统计详细
        .state('tab.CashmemberStatiDayShow', {
            url: '/CashmemberStatiDayShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/CashmemberStatiDayShow.html',
                    controller: 'CashmemberStatiDayShowCtrl'
                }
            }
        })
         //会员现金日统计编辑
        .state('tab.CashmemberStatiDayEdit', {
            url: '/CashmemberStatiDayEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/CashmemberStatiDayEdit.html',
                    controller: 'CashmemberStatiDayEditCtrl'
                }
            }
        })
         //会员现金月统计列表
        .state('tab.CashmemberStatiMonthList', {
            url: '/CashmemberStatiMonthList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/CashmemberStatiMonthList.html',
                    controller: 'CashmemberStatiMonthListCtrl'
                }
            }
        })
         //会员现金月统计详细
        .state('tab.CashmemberStatiMonthShow', {
            url: '/CashmemberStatiMonthShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/CashmemberStatiMonthShow.html',
                    controller: 'CashmemberStatiMonthShowCtrl'
                }
            }
        })
         //会员现金月统计编辑
        .state('tab.CashmemberStatiMonthEdit', {
            url: '/CashmemberStatiMonthEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/CashmemberStatiMonthEdit.html',
                    controller: 'CashmemberStatiMonthEditCtrl'
                }
            }
        })
         //账号信息修改列表
        .state('tab.UserList', {
            url: '/UserList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/UserList.html',
                    controller: 'UserListCtrl'
                }
            }
        })
         //账号信息修改详细
        .state('tab.UserShow', {
            url: '/UserShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/UserShow.html',
                    controller: 'UserShowCtrl'
                }
            }
        })
         //账号信息修改编辑
        .state('tab.UserEdit', {
            url: '/UserEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/UserEdit.html',
                    controller: 'UserEditCtrl'
                }
            }
        })
         //会员列表
        .state('tab.MemberList', {
            url: '/MemberList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/MemberList.html',
                    controller: 'MemberListCtrl'
                }
            }
        })
         //会员详细
        .state('tab.MemberShow', {
            url: '/MemberShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/MemberShow.html',
                    controller: 'MemberShowCtrl'
                }
            }
        })
         //会员编辑
        .state('tab.MemberEdit', {
            url: '/MemberEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/MemberEdit.html',
                    controller: 'MemberEditCtrl'
                }
            }
        })
         //积分流水列表
        .state('tab.ScoreHisList', {
            url: '/ScoreHisList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ScoreHisList.html',
                    controller: 'ScoreHisListCtrl'
                }
            }
        })
         //积分流水详细
        .state('tab.ScoreHisShow', {
            url: '/ScoreHisShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ScoreHisShow.html',
                    controller: 'ScoreHisShowCtrl'
                }
            }
        })
         //积分流水编辑
        .state('tab.ScoreHisEdit', {
            url: '/ScoreHisEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ScoreHisEdit.html',
                    controller: 'ScoreHisEditCtrl'
                }
            }
        })
         //会员积分日统计列表
        .state('tab.ScorememberStatiDayList', {
            url: '/ScorememberStatiDayList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ScorememberStatiDayList.html',
                    controller: 'ScorememberStatiDayListCtrl'
                }
            }
        })
         //会员积分日统计详细
        .state('tab.ScorememberStatiDayShow', {
            url: '/ScorememberStatiDayShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ScorememberStatiDayShow.html',
                    controller: 'ScorememberStatiDayShowCtrl'
                }
            }
        })
         //会员积分日统计编辑
        .state('tab.ScorememberStatiDayEdit', {
            url: '/ScorememberStatiDayEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ScorememberStatiDayEdit.html',
                    controller: 'ScorememberStatiDayEditCtrl'
                }
            }
        })
         //会员积分月统计列表
        .state('tab.ScorememberStatiMonthList', {
            url: '/ScorememberStatiMonthList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ScorememberStatiMonthList.html',
                    controller: 'ScorememberStatiMonthListCtrl'
                }
            }
        })
         //会员积分月统计详细
        .state('tab.ScorememberStatiMonthShow', {
            url: '/ScorememberStatiMonthShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ScorememberStatiMonthShow.html',
                    controller: 'ScorememberStatiMonthShowCtrl'
                }
            }
        })
         //会员积分月统计编辑
        .state('tab.ScorememberStatiMonthEdit', {
            url: '/ScorememberStatiMonthEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ScorememberStatiMonthEdit.html',
                    controller: 'ScorememberStatiMonthEditCtrl'
                }
            }
        })
         //经验流水列表
        .state('tab.ExpHisList', {
            url: '/ExpHisList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ExpHisList.html',
                    controller: 'ExpHisListCtrl'
                }
            }
        })
         //经验流水详细
        .state('tab.ExpHisShow', {
            url: '/ExpHisShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ExpHisShow.html',
                    controller: 'ExpHisShowCtrl'
                }
            }
        })
         //经验流水编辑
        .state('tab.ExpHisEdit', {
            url: '/ExpHisEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ExpHisEdit.html',
                    controller: 'ExpHisEditCtrl'
                }
            }
        })
         //会员经验日统计列表
        .state('tab.ExpmemberStatiDayList', {
            url: '/ExpmemberStatiDayList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ExpmemberStatiDayList.html',
                    controller: 'ExpmemberStatiDayListCtrl'
                }
            }
        })
         //会员经验日统计详细
        .state('tab.ExpmemberStatiDayShow', {
            url: '/ExpmemberStatiDayShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ExpmemberStatiDayShow.html',
                    controller: 'ExpmemberStatiDayShowCtrl'
                }
            }
        })
         //会员经验日统计编辑
        .state('tab.ExpmemberStatiDayEdit', {
            url: '/ExpmemberStatiDayEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ExpmemberStatiDayEdit.html',
                    controller: 'ExpmemberStatiDayEditCtrl'
                }
            }
        })
         //会员经验月统计列表
        .state('tab.ExpmemberStatiMonthList', {
            url: '/ExpmemberStatiMonthList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ExpmemberStatiMonthList.html',
                    controller: 'ExpmemberStatiMonthListCtrl'
                }
            }
        })
         //会员经验月统计详细
        .state('tab.ExpmemberStatiMonthShow', {
            url: '/ExpmemberStatiMonthShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ExpmemberStatiMonthShow.html',
                    controller: 'ExpmemberStatiMonthShowCtrl'
                }
            }
        })
         //会员经验月统计编辑
        .state('tab.ExpmemberStatiMonthEdit', {
            url: '/ExpmemberStatiMonthEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/ExpmemberStatiMonthEdit.html',
                    controller: 'ExpmemberStatiMonthEditCtrl'
                }
            }
        })
         //订单之一对一问题提问列表
        .state('tab.OrderrQuestionList', {
            url: '/OrderrQuestionList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionList.html',
                    controller: 'OrderrQuestionListCtrl'
                }
            }
        })
         //订单之一对一问题提问详细
        .state('tab.OrderrQuestionShow', {
            url: '/OrderrQuestionShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionShow.html',
                    controller: 'OrderrQuestionShowCtrl'
                }
            }
        })
         //订单之一对一问题提问编辑
        .state('tab.OrderrQuestionEdit', {
            url: '/OrderrQuestionEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionEdit.html',
                    controller: 'OrderrQuestionEditCtrl'
                }
            }
        })
         //订单之一对一问题提问归档列表
        .state('tab.OrderrQuestionFinishedList', {
            url: '/OrderrQuestionFinishedList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionFinishedList.html',
                    controller: 'OrderrQuestionFinishedListCtrl'
                }
            }
        })
         //订单之一对一问题提问归档详细
        .state('tab.OrderrQuestionFinishedShow', {
            url: '/OrderrQuestionFinishedShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionFinishedShow.html',
                    controller: 'OrderrQuestionFinishedShowCtrl'
                }
            }
        })
         //订单之一对一问题提问归档编辑
        .state('tab.OrderrQuestionFinishedEdit', {
            url: '/OrderrQuestionFinishedEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionFinishedEdit.html',
                    controller: 'OrderrQuestionFinishedEditCtrl'
                }
            }
        })
         //订单之一对一问题提问放弃列表
        .state('tab.OrderrQuestionDiscardList', {
            url: '/OrderrQuestionDiscardList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionDiscardList.html',
                    controller: 'OrderrQuestionDiscardListCtrl'
                }
            }
        })
         //订单之一对一问题提问放弃详细
        .state('tab.OrderrQuestionDiscardShow', {
            url: '/OrderrQuestionDiscardShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionDiscardShow.html',
                    controller: 'OrderrQuestionDiscardShowCtrl'
                }
            }
        })
         //订单之一对一问题提问放弃编辑
        .state('tab.OrderrQuestionDiscardEdit', {
            url: '/OrderrQuestionDiscardEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionDiscardEdit.html',
                    controller: 'OrderrQuestionDiscardEditCtrl'
                }
            }
        })
         //订单之抢答问题提问列表
        .state('tab.OrderrQuickList', {
            url: '/OrderrQuickList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickList.html',
                    controller: 'OrderrQuickListCtrl'
                }
            }
        })
         //订单之抢答问题提问详细
        .state('tab.OrderrQuickShow', {
            url: '/OrderrQuickShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickShow.html',
                    controller: 'OrderrQuickShowCtrl'
                }
            }
        })
         //订单之抢答问题提问编辑
        .state('tab.OrderrQuickEdit', {
            url: '/OrderrQuickEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickEdit.html',
                    controller: 'OrderrQuickEditCtrl'
                }
            }
        })
         //订单之抢答问题提问归档列表
        .state('tab.OrderrQuickFinishedList', {
            url: '/OrderrQuickFinishedList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickFinishedList.html',
                    controller: 'OrderrQuickFinishedListCtrl'
                }
            }
        })
         //订单之抢答问题提问归档详细
        .state('tab.OrderrQuickFinishedShow', {
            url: '/OrderrQuickFinishedShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickFinishedShow.html',
                    controller: 'OrderrQuickFinishedShowCtrl'
                }
            }
        })
         //订单之抢答问题提问归档编辑
        .state('tab.OrderrQuickFinishedEdit', {
            url: '/OrderrQuickFinishedEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickFinishedEdit.html',
                    controller: 'OrderrQuickFinishedEditCtrl'
                }
            }
        })
         //订单之抢答问题提问放弃列表
        .state('tab.OrderrQuickDiscardList', {
            url: '/OrderrQuickDiscardList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickDiscardList.html',
                    controller: 'OrderrQuickDiscardListCtrl'
                }
            }
        })
         //订单之抢答问题提问放弃详细
        .state('tab.OrderrQuickDiscardShow', {
            url: '/OrderrQuickDiscardShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickDiscardShow.html',
                    controller: 'OrderrQuickDiscardShowCtrl'
                }
            }
        })
         //订单之抢答问题提问放弃编辑
        .state('tab.OrderrQuickDiscardEdit', {
            url: '/OrderrQuickDiscardEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickDiscardEdit.html',
                    controller: 'OrderrQuickDiscardEditCtrl'
                }
            }
        })
         //订单之一对一问题观看列表
        .state('tab.OrderrQuestionviewList', {
            url: '/OrderrQuestionviewList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionviewList.html',
                    controller: 'OrderrQuestionviewListCtrl'
                }
            }
        })
         //订单之一对一问题观看详细
        .state('tab.OrderrQuestionviewShow', {
            url: '/OrderrQuestionviewShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionviewShow.html',
                    controller: 'OrderrQuestionviewShowCtrl'
                }
            }
        })
         //订单之一对一问题观看编辑
        .state('tab.OrderrQuestionviewEdit', {
            url: '/OrderrQuestionviewEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionviewEdit.html',
                    controller: 'OrderrQuestionviewEditCtrl'
                }
            }
        })
         //订单之一对一问题观看归档列表
        .state('tab.OrderrQuestionviewFinishedList', {
            url: '/OrderrQuestionviewFinishedList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionviewFinishedList.html',
                    controller: 'OrderrQuestionviewFinishedListCtrl'
                }
            }
        })
         //订单之一对一问题观看归档详细
        .state('tab.OrderrQuestionviewFinishedShow', {
            url: '/OrderrQuestionviewFinishedShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionviewFinishedShow.html',
                    controller: 'OrderrQuestionviewFinishedShowCtrl'
                }
            }
        })
         //订单之一对一问题观看归档编辑
        .state('tab.OrderrQuestionviewFinishedEdit', {
            url: '/OrderrQuestionviewFinishedEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionviewFinishedEdit.html',
                    controller: 'OrderrQuestionviewFinishedEditCtrl'
                }
            }
        })
         //订单之一对一问题观看放弃列表
        .state('tab.OrderrQuestionviewDiscardList', {
            url: '/OrderrQuestionviewDiscardList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionviewDiscardList.html',
                    controller: 'OrderrQuestionviewDiscardListCtrl'
                }
            }
        })
         //订单之一对一问题观看放弃详细
        .state('tab.OrderrQuestionviewDiscardShow', {
            url: '/OrderrQuestionviewDiscardShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionviewDiscardShow.html',
                    controller: 'OrderrQuestionviewDiscardShowCtrl'
                }
            }
        })
         //订单之一对一问题观看放弃编辑
        .state('tab.OrderrQuestionviewDiscardEdit', {
            url: '/OrderrQuestionviewDiscardEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuestionviewDiscardEdit.html',
                    controller: 'OrderrQuestionviewDiscardEditCtrl'
                }
            }
        })
         //订单之抢答问题观看列表
        .state('tab.OrderrQuickviewList', {
            url: '/OrderrQuickviewList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickviewList.html',
                    controller: 'OrderrQuickviewListCtrl'
                }
            }
        })
         //订单之抢答问题观看详细
        .state('tab.OrderrQuickviewShow', {
            url: '/OrderrQuickviewShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickviewShow.html',
                    controller: 'OrderrQuickviewShowCtrl'
                }
            }
        })
         //订单之抢答问题观看编辑
        .state('tab.OrderrQuickviewEdit', {
            url: '/OrderrQuickviewEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickviewEdit.html',
                    controller: 'OrderrQuickviewEditCtrl'
                }
            }
        })
         //订单之抢答问题观看归档列表
        .state('tab.OrderrQuickviewFinishedList', {
            url: '/OrderrQuickviewFinishedList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickviewFinishedList.html',
                    controller: 'OrderrQuickviewFinishedListCtrl'
                }
            }
        })
         //订单之抢答问题观看归档详细
        .state('tab.OrderrQuickviewFinishedShow', {
            url: '/OrderrQuickviewFinishedShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickviewFinishedShow.html',
                    controller: 'OrderrQuickviewFinishedShowCtrl'
                }
            }
        })
         //订单之抢答问题观看归档编辑
        .state('tab.OrderrQuickviewFinishedEdit', {
            url: '/OrderrQuickviewFinishedEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickviewFinishedEdit.html',
                    controller: 'OrderrQuickviewFinishedEditCtrl'
                }
            }
        })
         //订单之抢答问题观看放弃列表
        .state('tab.OrderrQuickviewDiscardList', {
            url: '/OrderrQuickviewDiscardList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickviewDiscardList.html',
                    controller: 'OrderrQuickviewDiscardListCtrl'
                }
            }
        })
         //订单之抢答问题观看放弃详细
        .state('tab.OrderrQuickviewDiscardShow', {
            url: '/OrderrQuickviewDiscardShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickviewDiscardShow.html',
                    controller: 'OrderrQuickviewDiscardShowCtrl'
                }
            }
        })
         //订单之抢答问题观看放弃编辑
        .state('tab.OrderrQuickviewDiscardEdit', {
            url: '/OrderrQuickviewDiscardEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/OrderrQuickviewDiscardEdit.html',
                    controller: 'OrderrQuickviewDiscardEditCtrl'
                }
            }
        })
         //一对一问题列表
        .state('tab.QuestionList', {
            url: '/QuestionList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionList.html',
                    controller: 'QuestionListCtrl'
                }
            }
        })
         //一对一问题详细
        .state('tab.QuestionShow', {
            url: '/QuestionShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionShow.html',
                    controller: 'QuestionShowCtrl'
                }
            }
        })
         //一对一问题编辑
        .state('tab.QuestionEdit', {
            url: '/QuestionEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionEdit.html',
                    controller: 'QuestionEditCtrl'
                }
            }
        })
         //一对一问题回答列表
        .state('tab.QuestionTxtList', {
            url: '/QuestionTxtList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionTxtList.html',
                    controller: 'QuestionTxtListCtrl'
                }
            }
        })
         //一对一问题回答详细
        .state('tab.QuestionTxtShow', {
            url: '/QuestionTxtShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionTxtShow.html',
                    controller: 'QuestionTxtShowCtrl'
                }
            }
        })
         //一对一问题回答编辑
        .state('tab.QuestionTxtEdit', {
            url: '/QuestionTxtEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionTxtEdit.html',
                    controller: 'QuestionTxtEditCtrl'
                }
            }
        })
         //一对一问题之追加列表
        .state('tab.QuestionAddList', {
            url: '/QuestionAddList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionAddList.html',
                    controller: 'QuestionAddListCtrl'
                }
            }
        })
         //一对一问题之追加详细
        .state('tab.QuestionAddShow', {
            url: '/QuestionAddShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionAddShow.html',
                    controller: 'QuestionAddShowCtrl'
                }
            }
        })
         //一对一问题之追加编辑
        .state('tab.QuestionAddEdit', {
            url: '/QuestionAddEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionAddEdit.html',
                    controller: 'QuestionAddEditCtrl'
                }
            }
        })
         //观看问题的会员列表
        .state('tab.QuestionLinkMemberViewList', {
            url: '/QuestionLinkMemberViewList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionLinkMemberViewList.html',
                    controller: 'QuestionLinkMemberViewListCtrl'
                }
            }
        })
         //观看问题的会员详细
        .state('tab.QuestionLinkMemberViewShow', {
            url: '/QuestionLinkMemberViewShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionLinkMemberViewShow.html',
                    controller: 'QuestionLinkMemberViewShowCtrl'
                }
            }
        })
         //观看问题的会员编辑
        .state('tab.QuestionLinkMemberViewEdit', {
            url: '/QuestionLinkMemberViewEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuestionLinkMemberViewEdit.html',
                    controller: 'QuestionLinkMemberViewEditCtrl'
                }
            }
        })
         //抢答列表
        .state('tab.QuickList', {
            url: '/QuickList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickList.html',
                    controller: 'QuickListCtrl'
                }
            }
        })
         //抢答详细
        .state('tab.QuickShow', {
            url: '/QuickShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickShow.html',
                    controller: 'QuickShowCtrl'
                }
            }
        })
         //抢答编辑
        .state('tab.QuickEdit', {
            url: '/QuickEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickEdit.html',
                    controller: 'QuickEditCtrl'
                }
            }
        })
         //抢答回答列表
        .state('tab.QuickTxtList', {
            url: '/QuickTxtList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickTxtList.html',
                    controller: 'QuickTxtListCtrl'
                }
            }
        })
         //抢答回答详细
        .state('tab.QuickTxtShow', {
            url: '/QuickTxtShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickTxtShow.html',
                    controller: 'QuickTxtShowCtrl'
                }
            }
        })
         //抢答回答编辑
        .state('tab.QuickTxtEdit', {
            url: '/QuickTxtEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickTxtEdit.html',
                    controller: 'QuickTxtEditCtrl'
                }
            }
        })
         //抢答之追加列表
        .state('tab.QuickAddList', {
            url: '/QuickAddList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickAddList.html',
                    controller: 'QuickAddListCtrl'
                }
            }
        })
         //抢答之追加详细
        .state('tab.QuickAddShow', {
            url: '/QuickAddShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickAddShow.html',
                    controller: 'QuickAddShowCtrl'
                }
            }
        })
         //抢答之追加编辑
        .state('tab.QuickAddEdit', {
            url: '/QuickAddEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickAddEdit.html',
                    controller: 'QuickAddEditCtrl'
                }
            }
        })
         //观看抢答的会员列表
        .state('tab.QuickLinkMemberViewList', {
            url: '/QuickLinkMemberViewList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickLinkMemberViewList.html',
                    controller: 'QuickLinkMemberViewListCtrl'
                }
            }
        })
         //观看抢答的会员详细
        .state('tab.QuickLinkMemberViewShow', {
            url: '/QuickLinkMemberViewShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickLinkMemberViewShow.html',
                    controller: 'QuickLinkMemberViewShowCtrl'
                }
            }
        })
         //观看抢答的会员编辑
        .state('tab.QuickLinkMemberViewEdit', {
            url: '/QuickLinkMemberViewEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickLinkMemberViewEdit.html',
                    controller: 'QuickLinkMemberViewEditCtrl'
                }
            }
        })
         //抢答的评价列表
        .state('tab.QuickTxtCommentList', {
            url: '/QuickTxtCommentList',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickTxtCommentList.html',
                    controller: 'QuickTxtCommentListCtrl'
                }
            }
        })
         //抢答的评价详细
        .state('tab.QuickTxtCommentShow', {
            url: '/QuickTxtCommentShow/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickTxtCommentShow.html',
                    controller: 'QuickTxtCommentShowCtrl'
                }
            }
        })
         //抢答的评价编辑
        .state('tab.QuickTxtCommentEdit', {
            url: '/QuickTxtCommentEdit/:id',
            views: {
                'tab_a': {
                    templateUrl: 'm/views/test/clazz/QuickTxtCommentEdit.html',
                    controller: 'QuickTxtCommentEditCtrl'
                }
            }
        })
    ;

 });

