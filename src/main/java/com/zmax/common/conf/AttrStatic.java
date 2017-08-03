package com.zmax.common.conf;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


/**
 * 整站属性使用的静态类型，展示和代码都会使用，只能增加不能改变。
 * 如果改变对应数字，会造成原有代码的混乱
 * @author zmax
 *
 */
public class AttrStatic {
	/**树的根目录的中文名*/
	public static final String treerootname="根目录";
	/**标准时间格式*/
	public static final String YMDSTD="yyyy-MM-dd HH:mm:ss";
	/**上次生成时间，如果有是放在webset中*/
	public static final String GENLASTDATE="gen.lastmodifyied";
	
	public static Map<String,Map<String,String>> getMap(){
		if(mymap!=null)
			return mymap;
		mymap=new HashMap<String, Map<String,String>>();
		
		mymap.put("all."+"EnumFieldType", EnumFieldType.getMap());
		mymap.put("all."+"EnumFromControllerType", EnumFromControllerType.getMap());
		mymap.put("all."+"StatusValidOrNot", StatusValidOrNot.getMap());	
		mymap.put("all."+"StatusApplyPassRefuse", StatusApplyPassRefuse.getMap());	
		
		return mymap;
	}
	/**Map[String,Map[String,String]]版配置列表*/
	public static Map<String,Map<String,String>> mymap=null;

	
	
	/**字段类型的数字解释*/
	public static final String  EnumFieldTypeString="{'0':'文本','1':'数字','2':'Bool','3':'Json文本','4':'时间日期'}";
	/**字段类型的数字解释，用于动态确认字段类型{'0':'文本','1':'数字','2':'Bool','3':'Json文本','4':'时间日期'}*/
	public static enum EnumFieldType { 
		/**0,"文本"*/
		TXT("0","文本")
		/**1,"数字"*/
		,NUM("1","数字")
		/**2,"是否"*/
		,BOOL("2","是否")
		/**3,"Json文本"*/
		,JSON("3","Json文本")
		/**4,"时间日期"*/
		,DATETIME("4","时间日期")
		; 
		// 定义私有变量 
		public String key; 
		public String value;
		// 构造函数，枚举类型只能为私有 
		private EnumFieldType(String key,String value) {
			this.key=key;
			this.value=value;
		} 
		@Override 
		public String toString(){ 
			return "'"+this.key+"':'"+this.value+"'"; 
		} 

		private static Map<String,String> map=null;

		/**
		 * enum转HashMap
		 * @return
		 */
		public static Map<String,String> getMap(){
			if(map!=null)
				return map;
			map=new HashMap<String, String>();
			EnumFieldType[] all = EnumFieldType.values(); 
			for (EnumFieldType obj : all) { 
				map.put(obj.key, obj.value);
			}
			return map;
		}
	} 
	
	

	/**表单控件的数字解释，用于动态生成表单*/
	public static final String EnumFromControllerTypeString="{'0':'自输入单行文本','1':'单选按钮','2':'下拉列表','3':'自输入多行文本','4':'自输入富编辑器','5':'图形选择','6':'文件选择'}";
	/**表单控件的数字解释，用于动态生成表单{'0':'自输入单行文本','1':'单选按钮','2':'下拉列表','3':'自输入多行文本','4':'自输入富编辑器','5':'图形选择','6':'文件选择'}*/
	public static enum EnumFromControllerType { 
		/**0,"自输入单行文本"*/
		TEXT("0","自输入单行文本")
		/**1,"单选按钮"*/
		,RADIO("1","单选按钮")
		/**2,"下拉列表"*/
		,SELECT("2","下拉列表")
		/**3,"自输入多行文本"*/
		,AREA("3","自输入多行文本")
		/**4,"自输入富编辑器"*/
		,FCK("4","自输入富编辑器")
		/**5,"图形选择"*/
		,IMG("5","图形选择")
		/**6,"文件选择"*/
		,FILE("6","文件选择")
		; 
		// 定义私有变量 
		public String key; 
		public String value;
		// 构造函数，枚举类型只能为私有 
		private EnumFromControllerType(String key,String value) {
			this.key=key;
			this.value=value;
		} 
		@Override 
		public String toString(){ 
			return "'"+this.key+"':'"+this.value+"'"; 
		} 

		private static Map<String,String> map=null;

		/**
		 * enum转HashMap
		 * @return
		 */
		public static Map<String,String> getMap(){
			if(map!=null)
				return map;
			map=new HashMap<String, String>();
			EnumFromControllerType[] all = EnumFromControllerType.values(); 
			for (EnumFromControllerType obj : all) { 
				map.put(obj.key, obj.value);
			}
			return map;
		}
	} 

	/**状态之有效或无效*/
	public static final String StatusValidOrNotString="{'1':'有效','0':'无效'}";
	/**状态之有效或无效，用于动态确认字段类型{'1':'有效','0':'无效'}*/
	public static enum StatusValidOrNot { 
		/**"1","有效"*/
		SUCC("1","有效")
		/**"0","无效"*/
		,FAIL("0","无效")
		; 
		// 定义私有变量 
		public String key; 
		public String value;
		// 构造函数，枚举类型只能为私有 
		private StatusValidOrNot(String key,String value) {
			this.key=key;
			this.value=value;
		} 
		@Override 
		public String toString(){ 
			return "'"+this.key+"':'"+this.value+"'"; 
		} 

		private static Map<String,String> map=null;

		/**
		 * enum转HashMap
		 * @return
		 */
		public static Map<String,String> getMap(){
			if(map!=null)
				return map;
			map=new HashMap<String, String>();
			StatusValidOrNot[] all = StatusValidOrNot.values(); 
			for (StatusValidOrNot obj : all) { 
				map.put(obj.key, obj.value);
			}
			return map;
		}
	} 

	/**状态之申请、审核、拒绝、放弃*/
	public static final String StatusApplyPassRefuseString="{'0':'正在申请','1':'上线','-1':'审核被拒','-2':'下线'}";
	/**状态之申请、审核、拒绝、放弃 "{'0':'正在申请','1':'上线','-1':'审核被拒','-2':'下线'}" */
	public static enum StatusApplyPassRefuse { 
		/**"0","正在申请"*/
		APPLY("0","正在申请")
		/**"1","上线"*/
		,PASS("1","上线")
		/**"-1","审核被拒"*/
		,REFUSE("-1","审核被拒")
		/**"-2","下线"*/
		,DELETE("-2","下线")
		; 
		// 定义私有变量 
		public String key; 
		public String value;
		// 构造函数，枚举类型只能为私有 
		private StatusApplyPassRefuse(String key,String value) {
			this.key=key;
			this.value=value;
		} 
		@Override 
		public String toString(){ 
			return "'"+this.key+"':'"+this.value+"'"; 
		} 

		private static Map<String,String> map=null;

		/**
		 * enum转HashMap
		 * @return
		 */
		public static Map<String,String> getMap(){
			if(map!=null)
				return map;
			map=new HashMap<String, String>();
			StatusApplyPassRefuse[] all = StatusApplyPassRefuse.values(); 
			for (StatusApplyPassRefuse obj : all) { 
				map.put(obj.key, obj.value);
			}
			return map;
		}
	} 

	

	/*枚举之角色及权限*/
	//jsonmap~{'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商',
	//'5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师'}
	//jsonmap~{'0':'超管','1':'一般管理员','4':'组织','5':'会员'}

	/** 超管 */        
	public static final int ROLE_ADMIN=0;        
	/** 普管 */        
	public static final int ROLE_CADMIN=1;    
	/** 店主 */        
	//public static final int ROLE_SHOP=2;    
	/** 店小二 */        
	//public static final int ROLE_OPER=3;    
	/** 企业会员 */        
	//public static final int ROLE_ORG=4;    
	/** 个人企业会员 */        
	public static final int ROLE_MEMBER=5;


	/** 
	 * 演示枚举类型的遍历 
	 */ 
	private static void testTraversalEnum() { 
		EnumFieldType[] allEnumFieldType = EnumFieldType.values (); 
		for (EnumFieldType aEnumFieldType : allEnumFieldType) { 
			System. out .println( " 当前 name ： " + aEnumFieldType.name()); 
			System. out .println( " 当前 ordinal ： " + aEnumFieldType.ordinal()); 
			System. out .println( " 当前： " + aEnumFieldType); 
		} 
	} 

	/** 
	 * 演示 EnumMap 的使用， EnumMap 跟 HashMap 的使用差不多，只不过 key 要是枚举类型 
	 */ 
	private static void testEnumMap() { 
		// 1. 演示定义 EnumMap 对象， EnumMap 对象的构造函数需要参数传入 , 默认是 key 的类的类型 
		EnumMap<EnumFieldType, String> currEnumMap = new EnumMap<EnumFieldType, String>( 
				EnumFieldType. class ); 
		currEnumMap.put(EnumFieldType.TXT , " 红 " ); 
		currEnumMap.put(EnumFieldType.NUM, " 绿 " ); 
		currEnumMap.put(EnumFieldType.BOOL , " 黄 " ); 

		// 2. 遍历对象 
		for (EnumFieldType aEnumFieldType : EnumFieldType.values ()) { 
			System. out .println( "[key=" + aEnumFieldType.name() + ",value=" 
					+ currEnumMap.get(aEnumFieldType) + "]" ); 
		} 
	} 

	/** 
	 * 演示 EnumSet 如何使用， EnumSet 是一个抽象类，获取一个类型的枚举类型内容 <BR/> 
	 * 可以使用 allOf 方法 
	 */ 
	private static void testEnumSet() { 
		EnumSet<EnumFieldType> currEnumSet = EnumSet.allOf (EnumFieldType. class ); 
		for (EnumFieldType aEnumFieldTypeSetElement : currEnumSet) { 
			System. out .println( " 当前 EnumSet 中数据为： " + aEnumFieldTypeSetElement); 
		} 

	} 

//	public static void main(String[] args ) {
//	System. out .println("txt="+EnumFromControllerType.TEXT.key+","+EnumFromControllerType.TEXT.value);
//	System. out .println("txt="+EnumFieldType.TXT.key+","+EnumFieldType.TXT.value);
//	System. out .println("map.size="+EnumFieldType.getMap().size());
//	// 1. 遍历枚举类型 
//	System. out .println( " 演示枚举类型的遍历 ......" ); 
//	testTraversalEnum (); 
//
//	// 2. 演示 EnumMap 对象的使用 
//	System. out .println( " 演示 EnmuMap 对象的使用和遍历 ....." ); 
//	testEnumMap (); 
//
//	// 3. 演示 EnmuSet 的使用 
//	System. out .println( " 演示 EnmuSet 对象的使用和遍历 ....." ); 
//	testEnumSet (); 
//} 

}
