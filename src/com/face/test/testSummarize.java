package com.face.test;

import com.face.entity.RecommandDAO;
import com.face.statistics.Summarize;

public class testSummarize {

	public static void main(String[] args) 
	{
		 Summarize summarize =new Summarize();
		 //summarize.getclassProductSummarize(1,0, 0.1, 0.1, 0.1,0.1);
		 summarize.updateclassProduct(1, 1, 12, 0.155, 3.11, 40.6, 60.4);
	}

}
