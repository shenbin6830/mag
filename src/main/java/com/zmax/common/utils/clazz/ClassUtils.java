package com.zmax.common.utils.clazz;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import  java.lang.reflect.Method;
import  java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import  java.util.ArrayList;
import java.util.HashSet;
import  java.util.List;
import java.util.Set;
/**
 * 对象工具，深度复制，注入值等
 * @author zmax
 *
 */
public class ClassUtils {

    /**
     * bean的深拷贝，复制所有get,set方法，源的get值对目标进行set
     * @param beansrc 源
     * @param beandest 目标
     * @return
     */
    public static Object beanDeepCopy(Object beansrc,Object beandest){
        if(beansrc==null)return null;
        //先把dest的所有set找到。
        Class cladest=beandest.getClass();
        //所有方法
        ArrayList<String> allMethod=showClassMethordsFull(cladest);
        String setMethod="";
        String getMethod="";
        int atget=-1;
        Object value=null;
        for (String string : allMethod) {
            atget=string.indexOf(";get");
            if(atget==-1)continue;
            getMethod="get"+string.substring(atget+4,string.length()-1);
            setMethod="set"+string.substring(atget+4,string.length()-1);
            ////System.out.println("setMethod=" + setMethod);
            value=invokeMethod(beansrc, getMethod,null);
            if(value!=null)invokeMethod(beandest, setMethod, new Object[]{value});
        }
        return beandest;
    }
    /**
     * 空不复制版_bean的深拷贝，复制所有get,set方法，源的get值对目标进行set
     * 跳过null,String empty 和 Integer 0
     * @param beansrc 源
     * @param beandest 目标
     * @return
     */
    public static Object beanDeepCopySkipEmpty(Object beansrc,Object beandest){
        if(beansrc==null)return null;
        //先把dest的所有set找到。
        Class cladest=beandest.getClass();
        //所有方法
        ArrayList<String> allMethod=showClassMethordsFull(cladest);
        String setMethod="";
        String getMethod="";
        int atget=-1;
        Object value=null;
        for (String string : allMethod) {
            atget=string.indexOf(";get");
            if(atget==-1)continue;
            getMethod="get"+string.substring(atget+4,string.length()-1);
            setMethod="set"+string.substring(atget+4,string.length()-1);
            ////System.out.println("setMethod=" + setMethod);
            value=invokeMethod(beansrc, getMethod,null);
            if(value!=null){
            	if(value instanceof String && "".equals(value))
            		continue;
            	if(value instanceof Integer && Integer.valueOf(0).equals(value))
            		continue;
            	invokeMethod(beandest, setMethod, new Object[]{value});
            }
        }
        return beandest;
    }
    /**
     * 直接读取对象字段属性值, 无视public/protected修饰符, 不经过getter函数.
     * @param object 对象
     * @param fieldName 字段名
     * @return
     */
    public static Object getFieldValue(Object object, String fieldName) {
    	if(object==null)
    		return null;
        Field field = getDeclaredField(object, fieldName);

        if (field == null)return null;

        Object result = null;
        try {
            makeAccessible(field);
            result = field.get(object);
        } catch (IllegalAccessException e) {
            return null;
        }
        return result;
    }
    
    /**
     * 直接设置对象字段属性值, 无视public/protected修饰符, 不经过setter函数
     * @param object 对象
     * @param fieldName 字段
     * @param value 值 
     * @return
     */
    public static boolean setFieldValue(Object object, String fieldName, Object value) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null)return false;
        try {
            makeAccessible(field);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            return false;
        }
        return true;
    }
    /**
     * 强行设置Field可访问.
     */
    protected static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }
    /**
     * 找到对象的字段
     * @param object
     * @param fieldName
     * @return
     */
    public static Field getDeclaredField(Object object, String fieldName){
        Field[] fields=object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.getName().equals(fieldName.trim()))return field;
        }
        return null;
    }
    /**
     * 返回所有字段名称
     * @param cla
     * @return String 
     * int,i;class java.lang.Integer,id;class java.util.Date,indate
     */
    public static String showClassFieldsAll(Class cla){
        String sField="";
        Field[] fields=cla.getDeclaredFields();
        for (Field field : fields) {
            
            sField+=""+field.getType()+","+field.getName()+";";
        }
        //把最后的逗号去掉
        if(sField.length()>1)sField=sField.substring(0,sField.length()-1);
        return sField;
        
    }
    /**
     * 返回所有字段名称
     * @param cla
     * @return String
     * i,id,indate
     */
    public static String showClassFields(Class cla){
        String sField="";
        Field[] fields=cla.getDeclaredFields();
        for (Field field : fields) {
            sField+=field.getName()+",";
            //System.out.println("field.getType().toString()=" + field.getType().toString());
        }
        //把最后的逗号去掉
        if(sField.length()>1)sField=sField.substring(0,sField.length()-1);
        return sField;
        
    }
    /**
     * 返回类的ID类型
     * @param cla
     * @return String
     * int long
     */
    public static String showClassFieldsIdType(Class cla){
        Field[] fields=cla.getDeclaredFields();
        for (Field field : fields) {
            if(field.getName().equals("id")){
                String fieldtype=field.getType().toString();
                if(fieldtype.indexOf(".")>-1)fieldtype=fieldtype.substring(fieldtype.lastIndexOf(".")+1);
                return fieldtype;
            }
        }
        return "int";
    }
    /**
     * 返回类的某字段类型
     * @param cla
     * @return String
     * int long
     */
    public static String showClassFieldsTypeByFieldname(Class cla,String fieldname){
        Field[] fields=cla.getDeclaredFields();
        for (Field field : fields) {
            if(field.getName().equals(fieldname)){
                String fieldtype=field.getType().toString();
                if(fieldtype.indexOf(".")>-1)fieldtype=fieldtype.substring(fieldtype.lastIndexOf(".")+1);
                return fieldtype;
            }
        }
        return "int";
    }
    /**
     * 返回所有字段名称
     * @param cla
     * @return String[]
     * 
     */
    public static String[] getAllClassFields(Class cla){
        Field[] fields=cla.getDeclaredFields();
        String[] ret=new String[fields.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i]=fields[i].getName();
        }
        return ret;
        
    }  
    /**
     * 返回所有字段名称Set
     * @param cla
     * @return Set<String>
     * 
     */
    public static Set<String> getAllClassFieldsSet(Class cla){
        Field[] fields=cla.getDeclaredFields();
        Set<String> ret=new HashSet<String>();
        for (int i = 0; i < fields.length; i++) {
        	ret.add(fields[i].getName());
        }
        return ret;
        
    } 
    /**
     * 返回getAllClassFields相对应的字段类型
     * @param cla
     * @return String[]
     * 
     */
    public static String[] getAllClassFieldsType(Class cla){
        Field[] fields=cla.getDeclaredFields();
        String[] ret=new String[fields.length];
        for (int i = 0; i < ret.length; i++) {
            String fieldtype=fields[i].getType().toString();
            if(fieldtype.indexOf(".")>-1)fieldtype=fieldtype.substring(fieldtype.lastIndexOf(".")+1);
            ret[i]=fieldtype;
        }
        return ret;
        
    }   
    /**
     * 直接调用对象方法, 无视public/protected修饰符.
     */
    public static Object invokeMethod(Object object, String methodName, final Object[] parameters) {
        Method method = getDeclaredMethod1(object, methodName);
        if (method == null)return null;

        try {
            method.setAccessible(true);
            return method.invoke(object, parameters);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 获取方法，如果方法名重复，取第一个方法，没考虑参数类型
     * @param object
     * @param methodName
     * @return
     */
    public static Method getDeclaredMethod1(Object object,String methodName){
        Method[] methods  =  object.getClass().getDeclaredMethods();
        //方法
        for (Method method : methods)  {
            if( method.getName().equals(methodName))return method;
        }
        return null;
        
    }
    /**
     * 获取所有类方法，只有名称
     * @param cla Class
     * @param getset String 方法的前缀get或set,null则返回全部
     * @return ArrayList 
     * class java.lang.Integer getId()
     * void setId(class java.lang.Integer)
     */
    public static String showClassMethords(Class cla,String getset){
        Method[] methods  =  cla.getDeclaredMethods();
        String sMethod="";
        getset = (getset == null) ? "" : getset;
        //方法
        for (Method method : methods)  {
            if(getset.equals("")){
                sMethod+=method.getName()+",";
            }else{
                if(method.getName().indexOf(getset)==0)
                    sMethod+=method.getName()+",";
            }
        }
        //把最后的逗号去掉
        if(sMethod.length()>1)sMethod=sMethod.substring(0,sMethod.length()-1);
        return sMethod;
    }
    /**
     * 获取所有类方法，包括返回类型和参数
     * @param cla Class
     * @return ArrayList 
     * class java.lang.Integer getId()
     * void setId(class java.lang.Integer)
     */
    public static ArrayList<String> showClassMethordsFull(Class cla){
        ArrayList<String> ret=new ArrayList<String>();
        Method[] methods  =  cla.getDeclaredMethods();
        String sMethod="";
        String sParam="";
        //方法
        for (Method method : methods)  {
            //返回类型
            Class returnType  =  method.getReturnType();
            sMethod=""+returnType+";"+ method.getName()+";";
            //void setId(
            //System.out.print("method=" +returnType+" "+ method.getName()+"(");
            //参数
            sParam="";
            Class[] paramTypeList  =  method.getParameterTypes();
            for (Class clazz:paramTypeList)  {
                sParam+=clazz+",";
                //System.out.print(clazz+",");
            } 
            //把最后的逗号去掉
            if(sParam.length()>1)sParam=sParam.substring(0,sParam.length()-1);
            sMethod+=sParam+"";
            ret.add(sMethod);
        }
        return ret;
    }
    
    /**
     * 获取所有类方法，包括返回类型和参数  格式 ：int test(String param1)
     * @param cla Class
     * @return ArrayList  int test(String param1)
     * class java.lang.Integer getId()
     * void setId(class java.lang.Integer)
     */
    public static ArrayList<String> getClassMethordsFull(Class cla,ArrayList<String> rettype,ArrayList<String> methodName,ArrayList<String> param){
        ArrayList<String> ret=new ArrayList<String>();
        Method[] methods  =  cla.getDeclaredMethods();
        String sMethod="";
        String sParam="";
        //方法
        for (Method method : methods)  {
            //返回类型
            Class returnType  =  method.getReturnType();
            //sMethod=""+classNameRemovePackage2(returnType.getName())+"  "+ classNameRemovePackage2(method.getName())+"(";
            rettype.add(classNameRemovePackage2(returnType.getName()));
            methodName.add(classNameRemovePackage2(method.getName()));
            //void setId(
            //System.out.print("method=" +returnType+" "+ method.getName()+"(");
            //参数

            String paramClass="";
            String paramObject="";
            sParam="";
            int iParam=1;
            Class[] paramTypeList  =  method.getParameterTypes();
            for (Class clazz:paramTypeList)  {
                paramClass = classNameRemovePackage2(clazz.getName());
                paramObject=firstLower(paramClass);
                if(paramClass.equals("Integer")){
                    paramClass="int";
                    paramObject=paramClass;
                }
                if(paramClass.equals("Long")){
                    paramClass="long";
                    paramObject=paramClass;
                }
                if(Character.isLowerCase(paramClass.charAt(0))){
                    paramObject=paramObject+iParam;
                    iParam++;
                }

                sParam+=paramClass+" "+paramObject+",";
                //System.out.print(clazz+",");
                
            } 
            //把最后的逗号去掉
            if(sParam.length()>1)sParam=sParam.substring(0,sParam.length()-1);
            param.add(sParam);
            sMethod+=sParam+")";
            ret.add(sMethod);
        }
        return ret;
    }
    
    /**
     * 数组深度复制
     * @param src
     * @return dest List
     */
    public static List listDeepCopy(List src){   
        try {
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();   
            ObjectOutputStream out = new ObjectOutputStream(byteout);   
            out.writeObject(src);  
            ByteArrayInputStream bytein = new ByteArrayInputStream(byteout.toByteArray());   
            ObjectInputStream in =new ObjectInputStream(bytein);   
            List dest = (List)in.readObject();   
            return dest;
        } catch (SecurityException e) {
            return new ArrayList();
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (IOException e) {
            return new ArrayList();
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            return new ArrayList();
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }   
        //return new ArrayList();
    
    }       
    /**
     * 大写的带点的CLASS变成小写的TABLE名
     * com.aaa.bbb.CccDddDo->ccc_ddd
     * @return
     */
    public static String classNameToTableName(String ClaName){
        String ret="";
        try {
            ret=ClaName.substring(1+ClaName.lastIndexOf("."));
            //最后两个的DO
            if(ret.charAt(ret.length()-2)=='D'&&ret.charAt(ret.length()-1)=='O'){
                ret=ret.substring(0, ret.length()-2);
            }
            //首位变小写
            char ch=ret.charAt(0);
            if(Character.isUpperCase(ch)){
                ret=Character.toLowerCase(ch)+ret.substring(1);
            }
            //aaBb变成aa_bb
            ret=upcaseTo_(ret);
            
        } catch (Exception e) {
            return ret;
            //e.printStackTrace();
        }
        return ret;
    }
       /**
     * 大写的带点的CLASS变成最后的类名，把包删除掉,DO,DAO也删掉
     * com.aaa.RdsAppDO->RdsApp
     * 
     * @return
     */
    public static String classNameRemovePackage(String ClaName){
        String ret="";
        try {
            ret=ClaName.substring(1+ClaName.lastIndexOf("."));
            //最后两个的DO
            if(ret.charAt(ret.length()-2)=='D'&&ret.charAt(ret.length()-1)=='O'){
                ret=ret.substring(0, ret.length()-2);
            }
            //最后3个的DAO
            if(ret.charAt(ret.length()-3)=='D'&&ret.charAt(ret.length()-2)=='A'&&ret.charAt(ret.length()-1)=='O'){
                ret=ret.substring(0, ret.length()-3);
            }
         } catch (Exception e) {
            //e.printStackTrace();
             return ret;
        }
        return ret;
    }
    /**
     * 大写的带点的CLASS变成最后的类名
     * com.aaa.RdsAppDO->RdsAppDO
     * 
     * @return
     */
    public static String classNameRemovePackage2(String ClaName){
        String ret="";
        if(ClaName.lastIndexOf(".")==-1)return ClaName;
        try {
            ret=ClaName.substring(1+ClaName.lastIndexOf("."));
         } catch (Exception e) {
            //e.printStackTrace();
             return ret;
        }
        return ret;
    }
    /**
     * 首位变小写
     * 
     * @return
     */
    /*
    public static String Xxx1Toxxx2(String Xxx1){
        //首位变小写
        char ch=Xxx1.charAt(0);
        if(Character.isUpperCase(ch)){
            return Character.toLowerCase(ch)+Xxx1.substring(1);
        }
        return Xxx1;
    }*/
    /**
     * 把大写变成_小写
     * 
     * @param upc
     * @return
     */
    public static String upcaseTo_(String upc){
        String ret="";
        char ch;
        for (int i = 0; i < upc.length(); i++) {
            ch=upc.charAt(i);
            //第一位不管
            if(i==0){
                ret+=ch;
                continue;
            }
            
            if(Character.isUpperCase(ch)){
                ret+="_"+Character.toLowerCase(ch);
                continue;
            }
            ret+=ch;
        }
        return ret;
    }

    /**
     * 把第一位变成小写
     * 
     * @param ss
     */
    public static String firstLower(String ss){
        return ""+Character.toLowerCase(ss.charAt(0))+ss.substring(1);
    }
       /**
     * 把第一位变成大写
     * 
     * @param ss
     */
    public static String firstUpper(String ss){
        return ""+Character.toUpperCase(ss.charAt(0))+ss.substring(1);
    }
 }
