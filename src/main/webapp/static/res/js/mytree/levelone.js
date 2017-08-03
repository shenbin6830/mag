//全站
/**全站版 有效无效状态*/
var selectmap_all_statusApplyPassRefuse={'0':'正在申请','1':'上线','-1':'审核被拒','-2':'下线'};
/**全站版 申请审核状态*/
var selectmap_all_statusValidOrNot={'1':'有效','0':'无效'};
/**全站版 字段类型的数字解释*/
var selectmap_all_enumFieldType={'0':'文本','1':'数字','2':'Bool','3':'Json文本','4':'时间日期'};
/**全站版 表单控件的数字解释，用于动态生成表单*/
var selectmap_all_enumFromControllerType={'0':'自输入单行文本','1':'单选按钮','2':'下拉列表','3':'自输入多行文本','4':'自输入富编辑器','5':'图形选择','6':'文件选择'};

//一级选择器
/**map选择 操作日志 操作类型  */
var selectmap_Oplog_itype={'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'};
/**map选择 共用对象之图文 状态  */
var selectmap_WaEntityArticle_status={'0':'无效','1':'上线'};
/**map选择 微信自定义菜单 菜单类型  */
var selectmap_Wxmenu_mtype={'menu':'菜单','button':'一级菜单','sub_button':'二级菜单'};
/**map选择 微信自定义菜单 响应动作类型  */
var selectmap_Wxmenu_type={'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'};
/**map选择 接收到的消息 消息类型  */
var selectmap_WaRecvmsg_msgtype={'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'};
/**map选择 接收到的消息 事件类型  */
var selectmap_WaRecvmsg_event={'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'};
/**map选择 微信配置 类型  */
var selectmap_Wxcfg_itype={'1':'服务号','2':'企业号','3':'订阅号'};
/**map选择 微信用户关系 角色  */
var selectmap_Wxr_roleId={'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商','5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师'};
/**map选择 微信用户非首推关系 状态  */
var selectmap_Wxrb_status={'0':'初创','1':'业务已处理'};
/**map选择 微信用户 用户信息类型  */
var selectmap_Wxouser_statusuf={'0':'只有openid','1':'完全版'};
/**map选择 微信用户 性别  */
var selectmap_Wxouser_sex={'1':'男','2':'女','0':'未知'};
/**map选择 微信用户 是否订阅  */
var selectmap_Wxouser_subscribe={'0':'没有关注该公众号','1':'关注过了该公众号'};
/**map选择 微信用户关注历史记录 操作  */
var selectmap_Wxousersubscribehis_act={'0':'取消关注','1':'关注'};
/**map选择 二维码场景及转换 是否为临时二维码  */
var selectmap_WaQrcodeticketScene_statusTemporary={'0':'是临时二维码','1':'是永久二维码'};
/**map选择 二维码场景及转换 是否有效  */
var selectmap_WaQrcodeticketScene_statusValid={'0':'无效','1':'有效'};
/**map选择 广告使用的关注二维码 关注后返回方式  */
var selectmap_WaQradv_rettype={'0':'无返回','1':'返回一个纯文本','2':'返回一个图文'};
/**map选择 现金流水 类型  */
var selectmap_CashHis_otype={'-2':'购买积分','-1':'提现','1':'现金充值','2':'积分变现','3':'支付宝充值','4':'微信充值'};
/**map选择 账号信息修改 状态  */
var selectmap_User_status={'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'};
/**map选择 账号信息修改 角色  */
var selectmap_User_roleId={'0':'超管','1':'一般管理员','5':'会员'};
/**map选择 会员 用户类型  */
var selectmap_Member_mtype={'0':'会员','1':'大师'};
/**map选择 会员 类型之个人企业  */
var selectmap_Member_idtype={'0':'个人','1':'企业'};
/**map选择 权限之表设定 角色  */
var selectmap_Permittable_roleId={'0':'超管','1':'一般管理员','5':'会员'};
/**map选择 权限之表设定 表权限  */
var selectmap_Permittable_pall={'0':'无','9':'部分','91':'全部'};
/**map选择 权限之表设定 按钮增  */
var selectmap_Permittable_btnnew={'0':'无','9':'部分','91':'全部'};
/**map选择 权限之表设定 按钮改  */
var selectmap_Permittable_btnedit={'0':'无','9':'部分','91':'全部'};
/**map选择 权限之表设定 按钮删  */
var selectmap_Permittable_btndel={'0':'无','9':'部分','91':'全部'};
/**map选择 权限之表设定 按钮查  */
var selectmap_Permittable_btnshow={'0':'无','9':'部分','91':'全部'};
/**map选择 权限之字段设定 角色  */
var selectmap_Permitfield_roleId={'0':'超管','1':'一般管理员','5':'会员'};
/**map选择 权限之字段设定 增可写字段41  */
var selectmap_Permitfield_pf41={'0':'无','9':'有','1~sessionobj.id':'1~sessionobj.id'};
/**map选择 权限之字段设定 改可写字段42  */
var selectmap_Permitfield_pf42={'0':'无','9':'有'};
/**map选择 权限之字段设定 查可看字段51  */
var selectmap_Permitfield_pf51={'0':'无','9':'有'};
/**map选择 权限之字段设定 列表显示字段52  */
var selectmap_Permitfield_pf52={'0':'无','9':'有'};
/**map选择 积分流水 类型  */
var selectmap_ScoreHis_otype={'-5':'抢答看','-4':'一对一看','-3':'抢答提问','-2':'一对一提问','-1':'提现','1':'充值','2':'回答一对一','3':'回答抢答'};
/**map选择 经验流水 类型  */
var selectmap_ExpHis_otype={'1':'充值','2':'回答一对一','3':'回答抢答'};
/**map选择 订单之一对一问题提问 支付状态  */
var selectmap_OrderrQuestion_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之一对一问题提问 支付方式  */
var selectmap_OrderrQuestion_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 订单之一对一问题提问归档 支付状态  */
var selectmap_OrderrQuestionFinished_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之一对一问题提问归档 支付方式  */
var selectmap_OrderrQuestionFinished_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 订单之一对一问题提问放弃 支付状态  */
var selectmap_OrderrQuestionDiscard_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之一对一问题提问放弃 支付方式  */
var selectmap_OrderrQuestionDiscard_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 订单之抢答问题提问 支付状态  */
var selectmap_OrderrQuick_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之抢答问题提问 支付方式  */
var selectmap_OrderrQuick_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 订单之抢答问题提问归档 支付状态  */
var selectmap_OrderrQuickFinished_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之抢答问题提问归档 支付方式  */
var selectmap_OrderrQuickFinished_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 订单之抢答问题提问放弃 支付状态  */
var selectmap_OrderrQuickDiscard_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之抢答问题提问放弃 支付方式  */
var selectmap_OrderrQuickDiscard_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 订单之一对一问题观看 支付状态  */
var selectmap_OrderrQuestionview_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之一对一问题观看 支付方式  */
var selectmap_OrderrQuestionview_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 订单之一对一问题观看归档 支付状态  */
var selectmap_OrderrQuestionviewFinished_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之一对一问题观看归档 支付方式  */
var selectmap_OrderrQuestionviewFinished_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 订单之一对一问题观看放弃 支付状态  */
var selectmap_OrderrQuestionviewDiscard_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之一对一问题观看放弃 支付方式  */
var selectmap_OrderrQuestionviewDiscard_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 订单之抢答问题观看 支付状态  */
var selectmap_OrderrQuickview_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之抢答问题观看 支付方式  */
var selectmap_OrderrQuickview_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 订单之抢答问题观看归档 支付状态  */
var selectmap_OrderrQuickviewFinished_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之抢答问题观看归档 支付方式  */
var selectmap_OrderrQuickviewFinished_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 订单之抢答问题观看放弃 支付状态  */
var selectmap_OrderrQuickviewDiscard_status={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'};
/**map选择 订单之抢答问题观看放弃 支付方式  */
var selectmap_OrderrQuickviewDiscard_itypePay={'0':'余额支付','2':'微信支付','3':'支付宝支付'};
/**map选择 一对一问题 状态  */
var selectmap_Question_status={'0':'未支付','1':'已支付','2':'已回答'};
/**map选择 一对一问题 公开状态  */
var selectmap_Question_ptype={'0':'收费公开','1':'不公开'};
/**map选择 一对一问题 评论情况  */
var selectmap_Question_ctype={'0':'以后再评','1':'好评','-1':'差评'};
/**map选择 观看问题的会员 评论情况  */
var selectmap_QuestionLinkMemberView_ctype={'0':'以后再评','1':'好评','-1':'差评'};
/**map选择 抢答 状态  */
var selectmap_Quick_status={'0':'未支付','1':'已支付','2':'开始抢答','3':'抢答结束'};
/**map选择 抢答 公开状态  */
var selectmap_Quick_ptype={'0':'收费公开','1':'不公开'};
/**map选择 抢答回答 中奖状态  */
var selectmap_QuickTxt_statusWin={'0':'没中奖','1':'中奖'};
/**map选择 抢答回答 提问者评价  */
var selectmap_QuickTxt_ctype={'0':'以后再评','1':'好评','-1':'差评'};
/**map选择 抢答的评价 评论情况  */
var selectmap_QuickTxtComment_ctype={'0':'以后再评','1':'好评','-1':'差评'};

