package com.face.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.face.entity.Product;
import com.face.entity.ProductDAO;

/**
 * 对于商品的综合排序类
 * @author bobobo
 *
 */
public class SynthesisSort {
	//经过AHP分权值法，得到权值数据，依次为月成交量、评论量、非差评率、收藏量、价格倒数
	Double[] WEIGHTS = {1.808967033,1.070093554,0.727268326,0.341809632,1.051861455};
	/**
	 * 通过输入无序的map数据，与想要获得综合排名最好的前count个商品，通过排序，可以得到最终权值从大到小的一个商品队列，越靠前，权值越高
	 * @param map 原始数据map
	 * @param count 需要前几商品个数
	 * @return
	 */
	public ArrayList<Product> sort(Map<Integer, Double> map,int count)
	{
		if(map.isEmpty()){
			return null;
		}
		//map_new是Double值前count重新组合形成的map
		Map<Integer,Double> map_new = mapSort(map, count);
		//新map值的商品ID，就是map中的Integer
		Integer[] goodsIds = new Integer[map_new.size()];
		Iterator<Integer> iterator_new = map.keySet().iterator();
		for(int i =0;i<map_new.size();i++){
			goodsIds[i] = iterator_new.next();
		}
		//得到商品列表
		ProductDAO prodao = new ProductDAO();
		ArrayList<Product> ProList = prodao.findByIds(goodsIds);
		if(ProList.isEmpty()){
			return null;
		}
		//数据归一化
		
		
		
		//得到prolist的最终权值数据
		Map<Product,Double> mapPro = new HashMap<Product,Double>();
		for (Product product : ProList) {
			Double weight = product.getSalesForMoon()*WEIGHTS[0]
					+ product.getCommentCount()*WEIGHTS[1]
							+ product.getDisNegativeCommentRate()*WEIGHTS[2]
									+product.getCollection()*WEIGHTS[3]
											+(1/product.getPride())*WEIGHTS[4];
			mapPro.put(product,weight);
		}
		
		//新建排序后数据，按权重从大到小排列
		ArrayList<Product> proSortList = listSort(mapPro);
		return proSortList;
	}
	
	private ArrayList<Product> listSort(Map<Product,Double> mapPro) 
	{
		ArrayList<Product> proSortList = new ArrayList<Product>();
		Product[] scoreIndex = new Product[mapPro.size()];
		Double[] score = new Double[mapPro.size()];
		Iterator<Product> iterator = mapPro.keySet().iterator();
		for(int i =0;i<mapPro.size();i++){
			scoreIndex[i] = iterator.next();
			score[i] = mapPro.get(scoreIndex[i]);
		}
		for(int i = 0;i<mapPro.size();i++){
			Product bigestIndex = null;
			int jIndex = 0;
			Double bigest = -100.0;
			for(int j = 0;j<mapPro.size();j++){
				if(score[j]>bigest) {
					bigestIndex = scoreIndex[j];
					jIndex = j;
					bigest = score[j];
				}
			}
			proSortList.add(bigestIndex);
			score[jIndex] = -100.0;
		}		
		return proSortList;
	}
	
	
	private Map<Integer, Double> mapSort(Map<Integer, Double> map,int count) {
		Map<Integer,Double> map_new = new HashMap<Integer,Double>();
		Integer[] scoreIndex = new Integer[map.size()];
		Double[] score = new Double[map.size()];
		Iterator<Integer> iterator = map.keySet().iterator();
		for(int i =0;i<map.size();i++){
			scoreIndex[i] = iterator.next();
			score[i] = map.get(scoreIndex[i]);
		}
		for(int i = 0;i<count;i++){
			int bigestIndex = 0;
			int jIndex = 0;
			Double bigest = -100.0;
			for(int j = 0;j<map.size();j++){
				if(score[j]>bigest) {
					jIndex = j;
					bigestIndex = scoreIndex[j];
					bigest = score[j];
				}
			}
			map_new.put(bigestIndex, bigest);
			score[jIndex] = -100.0;
		}		
		return map_new;
	}
}
