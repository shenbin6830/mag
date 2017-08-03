package com.zmax.common.utils.easyui;

import java.util.ArrayList;
import java.util.List;

public class EasyUiUtils {
	/**
	 * 对List[Tree]进行排序形成树
	 * 0,1-1,10
	 *        -10,111
	 *        -10,222
	 *    -1,11	
	 * 0,2
	 * 过程：每项找父亲，找到把自己加进去。
	 * 最后把一级项目加到新List即可
	 * @param list
	 * @return
	 */
	public static List<Tree> treeSort(List<Tree> list){
		List<Tree> ret=new ArrayList<Tree>();
		for (Tree tree : list) {
			Tree treef=findFather(list, tree);
			if(treef!=null)
				ret.add(treef);
		}
		return ret;
	}
	/**
	 * 找父亲，找到把自己加入，没找到返回自己。
	 * @param list
	 * @param tree
	 * @return
	 */
	private static Tree findFather(List<Tree> list,Tree tree){
		if(tree.getPid().equals("0"))
			return tree;
		for (Tree treef : list) {
			if(tree.getPid().equals(treef.getId())){
				treef.getChildren().add(tree);
				treef.setState("closed");
				return null;
			}
		}		
		return tree;
	}

}
