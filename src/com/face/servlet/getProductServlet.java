package com.face.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.face.entity.Product;
import com.face.statistics.Summarize;
import com.face.statistics.SynthesisSort;

public class getProductServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getProductServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		    doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	
	/***
	 * Method="getclassProduct":
	 *       requestparam:
	 *       class,male,age,smile,yaw_angle,pitch_angle,roll_angle
	 * Method=
	 */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		
	      	response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    
	    
	    	int male=Integer.parseInt(request.getParameter("male"));
	        int age=Integer.parseInt(request.getParameter("age"));
	        double smile=Double.parseDouble(request.getParameter("smile"));
	        double yaw_angle=Double.parseDouble(request.getParameter("yaw_angle"));
	        double pitch_angle=Double.parseDouble(request.getParameter("pitch_angle"));
	        double roll_angle=Double.parseDouble(request.getParameter("roll_angle"));
	        int productid=Integer.parseInt(request.getParameter("productid"));
		   
	        //��ȡҪ����ķ���
		    String method=request.getParameter("method");
		    String jsonData=new String();
		    switch(method)
		    {
		    //��ȡ��Ӧ������Ʒ
		    case "getclassProduct":
		         Map map=getclassProductSummarize(male,age,yaw_angle,pitch_angle,roll_angle,smile);
		         SynthesisSort synthesisSort=new SynthesisSort();
		         jsonData=JSON.toJSONString(synthesisSort.sort(map, 5));
		         response.getWriter().write(jsonData);
		         break;
		    //������ƷȨֵ,�޷���
		    case "updateclassProduct":
		    	 updateclassProduct(productid,male,age,yaw_angle,pitch_angle,roll_angle,smile);
		    	 break;
		    	 //��ʼ��Ȩֵ,�޷��ء�
		    case "initProduct":
		    	initProduct(productid);
		    	break;
		    }
			
		 
		    
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
    
	
	private Map getclassProductSummarize(int gmale,int gage,double gyaw_angle,double gpitch_angle,
			                             double groll_angle,double gsmile)
	{
	   Summarize summarize=new Summarize();
	   return summarize.getclassProductSummarize(gmale,gage,gyaw_angle,gpitch_angle,groll_angle,gsmile);
	}
	
	private void updateclassProduct(int productid,int male,int age,
			                        double yaw_angle,double pitch_angle,double roll_angle,double smile)
	{
		Summarize summarize=new Summarize();
	    summarize.updateclassProduct(productid,male,age,yaw_angle,pitch_angle,roll_angle,smile);
	}
	private void initProduct(int productid)
	{
		Summarize summarize=new Summarize();
		summarize.initProduct(productid);
	}
}
