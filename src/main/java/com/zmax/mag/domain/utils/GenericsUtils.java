package com.zmax.mag.domain.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛形工具
 * @author Administrator
 *
 */
public class GenericsUtils {
	public static Class getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricTypef(clazz, 0);
	}

	/**
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
     * 
     *@param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     *         determined
     */
    @SuppressWarnings("unchecked")
    public static Class<Object> getSuperClassGenricTypef(final Class clazz, final int index) {
    	
    	//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
           return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
                     return Object.class;
        }
        if (!(params[index] instanceof Class)) {
              return Object.class;
        }

        return (Class) params[index];
    }
    public static Class getSuperClassGenricType1(Class clazz, int index) throws IndexOutOfBoundsException {
    	return (Class)((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
    }
	public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {

		Type genType = clazz.getGenericSuperclass();

//		if (!(genType instanceof ParameterizedType)) {
//			return Object.class;
//		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

}
