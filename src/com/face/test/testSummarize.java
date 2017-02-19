package com.face.test;

import java.util.ArrayList;
import java.util.Map;

import com.face.entity.Product;
import com.face.statistics.Summarize;
import com.face.statistics.SynthesisSort;

public class testSummarize {

	public static void main(String[] args) 
	{
		 Summarize summarize =new Summarize();
		 //summarize.initProduct(7);
		 Map map=summarize.getclassProductSummarize(1, 12, 0.3, 0.3, 0.4, 20);
		 SynthesisSort sort = new SynthesisSort();
		 ArrayList<Product> list= sort.sort(map, 3);
		 for (Product product : list) {
			
			 System.out.println(product.toString());
		}
	}

}
