/**手机页首页配置*/
var _Wwwhome={
	logo: null, //String 图标 
	sharetxt: null, //String 分享转发说明 
	headimg1: null, //String 图1 
	headimgtxt1: null, //String 图说明1 
	headimglink1: null, //String 图链接1 
	headimg2: null, //String 图2 
	headimgtxt2: null, //String 图说明2 
	headimglink2: null, //String 图链接2 
	headimg3: null, //String 图3 
	headimgtxt3: null, //String 图说明3 
	headimglink3: null, //String 图链接3 
    obj1:null,
    obj2:null,
    obj3:null
}
/**会员父子关系*/
var _MemberRelation={
	memberId: null, //Integer 父亲 
	memberCh: null, //Integer 孩子 
    obj1:null,
    obj2:null,
    obj3:null
}
/**签到*/
var _Signin={
	memberId: null, //Integer 签到者 
	sindex: null, //Integer 顺序号 
    obj1:null,
    obj2:null,
    obj3:null
}
/**文章*/
var _Article={
	priority: null, //Integer 排列顺序 
	articlechannelId: null, //Integer 文章频道内序号 
	memberId: null, //Integer 作者内序号 
	title: null, //String 名称 
	intro: null, //String 简介 
	author: null, //String 作者 
	linkto: null, //String 直接链接到 
	img1: null, //String 图1 
	hittimes: null, //Integer 点击次数 
	genurl: null, //String 生成相对地址 
    obj1:null,
    obj2:null,
    obj3:null
}
/**文章内容*/
var _ArticleExtTxt={
	txt: null, //String 详细介绍 
    obj1:null,
    obj2:null,
    obj3:null
}
/**频道*/
var _Articlechannel={
	ckey: null, //String 关键字 
	parentid: null, //Integer 父ID 
	priority: null, //Integer 排列顺序 
	childrennum: null, //Integer 孩子数量 
	family: null, //String 祖先 
	title: null, //String 名称 
    obj1:null,
    obj2:null,
    obj3:null
}
/**文章的评论*/
var _ArticleComment={
	articleId: null, //Integer 文章内序号 
	memberAu: null, //Integer 作者内序号 
	memberCo: null, //Integer 评论者内序 
	msg: null, //String 评论 
    obj1:null,
    obj2:null,
    obj3:null
}
/**短消息*/
var _Message={
	memberFr: null, //Integer 发送者 
	memberTo: null, //Integer 接收者 
	msg: null, //String 内容 
    obj1:null,
    obj2:null,
    obj3:null
}
/**现金流水*/
var _CashHis={
	memberId: null, //Integer 会员 
	otype: null, //Integer 类型 
	num: null, //Integer 数量 
	oid: null, //Integer 订单的id 
	cmemo: null, //String 备注说明 
    obj1:null,
    obj2:null,
    obj3:null
}
/**会员现金日统计*/
var _CashmemberStatiDay={
	gmtStati: null, //Date 被统计日 
	memberId: null, //Integer 会员 
	num: null, //Integer 本日增减数量 
	nstart: null, //Integer 日初数 
	nend: null, //Integer 日末数 
	cmemo: null, //String 备注说明 
    obj1:null,
    obj2:null,
    obj3:null
}
/**会员现金月统计*/
var _CashmemberStatiMonth={
	gmtStati: null, //Date 被统计月 
	memberId: null, //Integer 会员 
	num: null, //Integer 本月增减数量 
	nstart: null, //Integer 月初数 
	nend: null, //Integer 月末数 
	cmemo: null, //String 备注说明 
    obj1:null,
    obj2:null,
    obj3:null
}
/**账号信息修改*/
var _User={
	status: null, //Integer 状态 
	roleId: null, //Integer 角色 
	username: null, //String 账号 
	password: null, //String 密码 
	nickname: null, //String 昵称 
	userId: null, //Integer 隶属于 
	openid: null, //String 微信openid 
	userobj: null, //String 类型对象 
	pmtmap: null, //String 权限列表 
	objmap: null, //String 拥有的对象 
	openidmd5: null, //String 微信openidMd5 
	token: null, //String token 
    obj1:null,
    obj2:null,
    obj3:null
}
/**会员*/
var _Member={
	mtype: null, //Integer 用户类型 
	price: null, //Double 咨询费 
	name: null, //String 姓名或名称 
	intro: null, //String 简介 
	img1: null, //String 头像图 
	imgqr: null, //String 二维码图 
	idtype: null, //Integer 类型之个人企业 
	idnum: null, //String 证件号码 
	addr: null, //String 地址 
	zip: null, //String 邮编 
	mobile: null, //String 手机 
	email: null, //String 电子邮件 
	score: null, //Integer 积分 
	cash: null, //Integer 余额 
	exp: null, //Integer 经验 
	mlevel: null, //Integer 等级 
    obj1:null,
    obj2:null,
    obj3:null
}
/**积分流水*/
var _ScoreHis={
	memberId: null, //Integer 会员 
	otype: null, //Integer 类型 
	num: null, //Integer 数量 
	oid: null, //Integer 订单的id 
	cmemo: null, //String 备注说明 
    obj1:null,
    obj2:null,
    obj3:null
}
/**会员积分日统计*/
var _ScorememberStatiDay={
	gmtStati: null, //Date 被统计日 
	memberId: null, //Integer 会员 
	num: null, //Integer 本日增减数量 
	nstart: null, //Integer 日初数 
	nend: null, //Integer 日末数 
	cmemo: null, //String 备注说明 
    obj1:null,
    obj2:null,
    obj3:null
}
/**会员积分月统计*/
var _ScorememberStatiMonth={
	gmtStati: null, //Date 被统计月 
	memberId: null, //Integer 会员 
	num: null, //Integer 本月增减数量 
	nstart: null, //Integer 月初数 
	nend: null, //Integer 月末数 
	cmemo: null, //String 备注说明 
    obj1:null,
    obj2:null,
    obj3:null
}
/**经验流水*/
var _ExpHis={
	memberId: null, //Integer 会员 
	otype: null, //Integer 类型 
	num: null, //Integer 数量 
	oid: null, //Integer 订单的id 
	cmemo: null, //String 备注说明 
    obj1:null,
    obj2:null,
    obj3:null
}
/**会员经验日统计*/
var _ExpmemberStatiDay={
	gmtStati: null, //Date 被统计日 
	memberId: null, //Integer 会员 
	num: null, //Integer 本日增减数量 
	nstart: null, //Integer 日初数 
	nend: null, //Integer 日末数 
	cmemo: null, //String 备注说明 
    obj1:null,
    obj2:null,
    obj3:null
}
/**会员经验月统计*/
var _ExpmemberStatiMonth={
	gmtStati: null, //Date 被统计月 
	memberId: null, //Integer 会员 
	num: null, //Integer 本月增减数量 
	nstart: null, //Integer 月初数 
	nend: null, //Integer 月末数 
	cmemo: null, //String 备注说明 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之一对一问题提问*/
var _OrderrQuestion={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	questionId: null, //Integer 一对一问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之一对一问题提问归档*/
var _OrderrQuestionFinished={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	questionId: null, //Integer 一对一问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之一对一问题提问放弃*/
var _OrderrQuestionDiscard={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	questionId: null, //Integer 一对一问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之抢答问题提问*/
var _OrderrQuick={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	quickId: null, //Integer 抢答问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之抢答问题提问归档*/
var _OrderrQuickFinished={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	quickId: null, //Integer 抢答问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之抢答问题提问放弃*/
var _OrderrQuickDiscard={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	quickId: null, //Integer 抢答问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之一对一问题观看*/
var _OrderrQuestionview={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	questionId: null, //Integer 一对一问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之一对一问题观看归档*/
var _OrderrQuestionviewFinished={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	questionId: null, //Integer 一对一问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之一对一问题观看放弃*/
var _OrderrQuestionviewDiscard={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	questionId: null, //Integer 一对一问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之抢答问题观看*/
var _OrderrQuickview={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	quickId: null, //Integer 抢答问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之抢答问题观看归档*/
var _OrderrQuickviewFinished={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	quickId: null, //Integer 抢答问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**订单之抢答问题观看放弃*/
var _OrderrQuickviewDiscard={
	gmtPay: null, //Date 支付时间 
	status: null, //Integer 支付状态 
	itypePay: null, //Integer 支付方式 
	memberId: null, //Integer 会员 
	name: null, //String 姓名 
	mobile: null, //String 手机 
	quickId: null, //Integer 抢答问题ID 
	title: null, //String 问题 
	price: null, //Double 总价 
	paywxh5: null, //String 微信支付H5对象 
    obj1:null,
    obj2:null,
    obj3:null
}
/**一对一问题*/
var _Question={
	gmtPay: null, //Date 支付时间 
	gmtAnswer: null, //Date 回答时间 
	priority: null, //Integer 排列顺序 
	status: null, //Integer 状态 
	ptype: null, //Integer 公开状态 
	articlechannelId: null, //Integer 频道内序号 
	memberQu: null, //Integer 提问者 
	memberAn: null, //Integer 回答者 
	price: null, //Double 咨询费 
	viewprice: null, //Double 观看费 
	answerwords: null, //Integer 回答字数 
	viewnum: null, //Integer 观看人数 
	goodnum: null, //Integer 好评人数 
	badnum: null, //Integer 差评人数 
	title: null, //String 标题 
	quest: null, //String 问题 
	ctype: null, //Integer 评论情况 
	canread: null, //Boolean 能看不 
	questionTxt: null, //String 答案 
    obj1:null,
    obj2:null,
    obj3:null
}
/**一对一问题回答*/
var _QuestionTxt={
	answer: null, //String 回答 
    obj1:null,
    obj2:null,
    obj3:null
}
/**一对一问题之追加*/
var _QuestionAdd={
	questionId: null, //Integer 一对一问题 
	memberQu: null, //Integer 提问者 
	memberAn: null, //Integer 回答者 
	qa: null, //String 内容 
    obj1:null,
    obj2:null,
    obj3:null
}
/**观看问题的会员*/
var _QuestionLinkMemberView={
	questionId: null, //Integer 问题内序号 
	memberQu: null, //Integer 提问者 
	memberAn: null, //Integer 回答者 
	viewprice: null, //Double 观看费 
	memberVi: null, //Integer 观看者 
	ctype: null, //Integer 评论情况 
    obj1:null,
    obj2:null,
    obj3:null
}
/**抢答*/
var _Quick={
	gmtPay: null, //Date 支付时间 
	gmtStart: null, //Date 开始时间 
	gmtOver: null, //Date 结束时间 
	status: null, //Integer 状态 
	ptype: null, //Integer 公开状态 
	priority: null, //Integer 排列顺序 
	articlechannelId: null, //Integer 频道内序号 
	memberQu: null, //Integer 提问者 
	priceeach: null, //Double 每个奖金 
	pricenum: null, //Integer 奖金数量 
	price: null, //Double 总奖金 
	viewprice: null, //Double 观看费 
	answernum: null, //Integer 回答人数 
	viewnum: null, //Integer 观看人数 
	title: null, //String 标题 
	question: null, //String 问题 
	canread: null, //Boolean 能看不 
	listQuickTxt: null, //String 答案列表 
    obj1:null,
    obj2:null,
    obj3:null
}
/**抢答回答*/
var _QuickTxt={
	quickId: null, //Integer 问题内序号 
	statusWin: null, //Integer 中奖状态 
	memberQu: null, //Integer 提问者 
	memberAn: null, //Integer 回答者 
	goodnum: null, //Integer 好评人数 
	badnum: null, //Integer 差评人数 
	ctype: null, //Integer 提问者评价 
	answer: null, //String 回答 
    obj1:null,
    obj2:null,
    obj3:null
}
/**抢答之追加*/
var _QuickAdd={
	quickId: null, //Integer 抢答 
	memberQu: null, //Integer 提问者 
	memberAn: null, //Integer 回答者 
	qa: null, //String 内容 
    obj1:null,
    obj2:null,
    obj3:null
}
/**观看抢答的会员*/
var _QuickLinkMemberView={
	quickId: null, //Integer 抢答ID 
	memberQu: null, //Integer 提问者 
	viewprice: null, //Double 观看费 
	memberVi: null, //Integer 观看者 
    obj1:null,
    obj2:null,
    obj3:null
}
/**抢答的评价*/
var _QuickTxtComment={
	quickTxtId: null, //Integer 抢答回答ID 
	memberAn: null, //Integer 回答者 
	memberVi: null, //Integer 观看者 
	ctype: null, //Integer 评论情况 
    obj1:null,
    obj2:null,
    obj3:null
}
