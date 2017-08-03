package com.zmax.common.utils.string;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class SqlUtils {
	/**
	 * 补全hql,在前面加上from xxx
	 * @param hqlb
	 * @param tbname
	 * @return
	 */
	public static String makeHqlb(String hqlb,String tbname){
		if(StringUtils.isBlank(hqlb)){
			hqlb="from "+tbname;
		}
		hqlb=hqlb.trim();
		if(hqlb.indexOf("from "+tbname)==-1){
			hqlb="from "+tbname+" "+hqlb;
		}
		return hqlb;
	}
	/**
	 * 补全hqle,如果没有order by 加上
	 * @param hqle
	 * @return
	 */
	public static String makeHqle(String hqle){
		if(StringUtils.isBlank(hqle)){
			return "";
		}
		hqle=hqle.trim();
		if(hqle.indexOf("order by ")==0){
			return hqle;
		}
		return "order by "+hqle;
	}

	public static String makeSqlb(String sqlb,String tbname){
		if(StringUtils.isBlank(sqlb)){
			sqlb="select * from "+tbname;
		}
		return sqlb;
	}
	/**
	 * 两个where相加,where1和2的格式是 " where xxx" 或 "xxx"
	 * @param where1
	 * @param where2
	 * @return where where1 and where2 
	 */
	public static String whereAdd(String where1,String where2){
		String retwhere="";
		if(!StringUtils.isBlank(where1)){
			where1=where1.trim();
			if(where1.indexOf("where")==0)
				where1=where1.substring(5);
		}
		if(!StringUtils.isBlank(where2)){
			where2=where2.trim();
			if(where2.indexOf("where")==0)
				where2=where2.substring(5);
		}

		if(StringUtils.isBlank(where1)){
			if(StringUtils.isBlank(where2)){
				retwhere= "";
			}else{
				retwhere= where2;
			}
		}else{
			if(StringUtils.isBlank(where2)){
				retwhere= where1;
			}else{
				retwhere= where1 +" and "+where2;
			}
		}
		retwhere=retwhere.trim();
		if(StringUtils.isBlank(retwhere))
			return "";
		if(retwhere.equals("where"))
			return "";
		if(retwhere.indexOf("where")!=0)
			retwhere=" where "+retwhere;
		return retwhere;
	}
	/**
	 * 根据ids生成where
	 * @param ids Serializable[]
	 * @return id in (1,2,3) ...
	 */
	public static String whereFromIds(Serializable[] ids){
		String where=" id in ("+ids[0];
		for (int i = 1; i < ids.length; i++) {
			where+=","+ids[i];
		}
		where+=")";
		return where;
	}

	/**
	 * 通过表名，where,order生成jpql
	 * @param Clazz ex.Admin
	 * @param where ex.nickname='aaa'
	 * @param order ex.id desc
	 * @return
	 */
	public static String jpqlFromClazzWhereOrder(String Clazz,String where,String order){
		String jpql = "select t from "+Clazz+" t";
		if(StringUtils.isNotBlank(where)){
			where=where.trim();
			if(where.indexOf("where")==0){
				jpql=jpql+" "+where;
			}else{
				jpql=jpql+" where "+where;
			}
		}

		if(StringUtils.isNotBlank(order)){
			order=order.trim();
			if(order.indexOf("order by")==0){
				jpql=jpql+" "+order;
			}else{
				jpql=jpql+" order by "+order;
			}
		}
			
		return jpql;
	}
	/**
	 * 通过表名，where 生成 查询数量的语句
	 * @param Clazz
	 * @param where
	 * @return
	 */
	public static String jpqlCountFromClazzWhere(String Clazz,String where){
		String jpql = "select count(*) from "+Clazz+" t";
		if(StringUtils.isNotBlank(where)){
			where=where.trim();
			if(where.indexOf("where")==0){
				jpql=jpql+" "+where;
			}else{
				jpql=jpql+" where "+where;
			}
		}
		return jpql;
	}
	/**
	 * 返回SQL6元素，以Map{"select":"","Clazz":clazz...}格式
	 * 查询中用到的关键词主要包含六个，并且他们的顺序依次为 
	 * select-[select]-from-[clazz]-where-[where]-group by-[group_by]-having-[having]-order by-[order_by]
	 * 其中select和from是必须的，其他关键词是可选的，这六个关键词的执行顺序
	 * 与sql语句的书写顺序并不是一样的，而是按照下面的顺序来执行
	 * from--where--group by--having--select--order by,  
	 * @return
	 */
	public static Map<String,String> sql6MapFromSql(String sql){
		Map<String,String> ret=new HashMap<String, String>();
		int at_select=sql.indexOf("select");
		int at_from=sql.indexOf("from");
		int at_where=sql.indexOf("where");
		int at_group_by=sql.indexOf("group by");
		int at_having=sql.indexOf("having");
		int at_order_by=sql.indexOf("order by");
		if(at_select==-1 || at_from==-1)
			return ret;
		if(at_select > at_from)
			return ret;
		String select=null,clazz=null,where=null,group_by=null,having=null,order_by=null;
		int at_where_9999=(at_where==-1)?9999:at_where;
		int at_group_by_9999=(at_group_by==-1)?9999:at_group_by;
		int at_having_9999=(at_having==-1)?9999:at_having;
		int at_order_by_9999=(at_order_by==-1)?9999:at_order_by;
		int len=sql.length();
		//select-[select]-from
		select=sql.substring(at_select+6, at_from).trim();
		//from-[clazz]-where--group by--having--order by-
		int at_clazz_end=StringUtilz.minArr(new Integer[]{at_where_9999,at_group_by_9999,at_having_9999,at_order_by_9999});
		at_clazz_end=(at_clazz_end==9999)?len:at_clazz_end;
		clazz = sql.substring(at_from+4, at_clazz_end).trim();

		if(at_where!=-1){
			//where-[where]-group by-[group_by]-having-[having]-order by-[order_by]
			int at_where_end=StringUtilz.minArr(new Integer[]{at_group_by_9999,at_having_9999,at_order_by_9999});
			at_where_end=(at_where_end==9999)?len:at_where_end;
			where = sql.substring(at_where+5, at_where_end).trim();
		}

		if(at_group_by!=-1){
			//group by-[group_by]-having-[having]-order by-[order_by]
			int at_group_by_end=StringUtilz.minArr(new Integer[]{at_having_9999,at_order_by_9999});
			at_group_by_end=(at_group_by_end==9999)?len:at_group_by_end;
			group_by = sql.substring(at_group_by+8, at_group_by_end).trim();
		}
		
		if(at_having!=-1){
			//having-[having]-order by-[order_by]
			int at_having_end=at_order_by_9999;
			at_having_end=(at_having_end==9999)?len:at_having_end;
			having = sql.substring(at_having+6, at_having_end).trim();
		}
		
		if(at_order_by!=-1){
			order_by = sql.substring(at_order_by+8).trim();
		}
		
		ret.put("select", select);
		ret.put("clazz", clazz);
		ret.put("where", where);
		ret.put("group_by", group_by);
		ret.put("having", having);
		ret.put("order_by", order_by);
		return ret;
	}
	/**
	 * sql6元素map转sql 文本
	 * 查询中用到的关键词主要包含六个，并且他们的顺序依次为 
	 * select-[select]-from-[clazz]-where-[where]-group by-[group_by]-having-[having]-order by-[order_by]
	 * 其中select和from是必须的，其他关键词是可选的，这六个关键词的执行顺序
	 * @param map
	 * @return sql String
	 */
	public static String sql6Map2Sql(Map<String,String> map){
		String select=null,clazz=null,where=null,group_by=null,having=null,order_by=null;
		select=map.get("select");
		clazz=map.get("clazz");
		if(StringUtils.isBlank(select) || StringUtils.isBlank(clazz))
			return null;
		String sql="select "+select+" from "+clazz;
		where=map.get("where");
		group_by=map.get("group_by");
		having=map.get("having");
		order_by=map.get("order_by");
		
		if(StringUtils.isNotBlank(where))
			sql+=" where "+where;
		if(StringUtils.isNotBlank(group_by))
			sql+=" group by "+group_by;
		if(StringUtils.isNotBlank(having))
			sql+=" having "+having;
		if(StringUtils.isNotBlank(order_by))
			sql+=" order by "+order_by;
		return sql;
	}
	/*
	//用完注释掉
	public static void main(String[] args) {
		Map<String,String> map=null;
		map=sql6MapFromSql("select * from aaa");
		System.out.println("mmm:" + StringUtilz.map2str(map));
		map=sql6MapFromSql("select  *  from   aaa  where 1=1   and 2=2 group by ggbbyy having sex order by id desc");
		System.out.println("mmm:" + StringUtilz.map2str(map));
		System.out.println("sql:" + sql6Map2Sql(map));
	}
	*/
}
