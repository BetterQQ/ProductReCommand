package com.face.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.face.entity.EntityManagerHelper;
import com.face.entity.Product;
import com.face.entity.ProductDAO;
import com.face.entity.Recommand;
import com.face.entity.RecommandDAO;
import com.face.util.ConsoleLog;

public class Summarize 
{
   private double learning_rate=0.05;//学习比率
   //通过这一类的权值获取
   
   
   public Map<Integer, Double> getclassProductSummarize(int gmale,int gage,double gyaw_angle,double gpitch_angle,
                                       double groll_angle,double gsmile)
   {
	   
	   
	   //获取实体管理者
       EntityManager  entityManager=EntityManagerHelper.getEntityManager();
	   final String queryString="select model from Recommand model ";
			           
	   
	   //创建查询
	   Query query = entityManager.createQuery(queryString);
	  
	  
	   @SuppressWarnings("unchecked")
	   List<Recommand> Recommands=query.getResultList();

	   Map<Integer, Double> map=new HashMap<>();
	   
	   //归一化处理
       gsmile=gsmile/100.0;
       double gamale=gmale;
       double gaage=gage/100.0;
       gyaw_angle=gyaw_angle/20.0;
       gpitch_angle=gpitch_angle/20.0;
       groll_angle=groll_angle/20.0;
	   
       
       //求喜爱度
	   for(int i=0;i<Recommands.size();i++)
	   {
		  Recommand recommand=Recommands.get(i);
		  double ans=gsmile*recommand.getW1()+
				     gamale*recommand.getW2()+
				     gaage*recommand.getW3()+
				     gyaw_angle*recommand.getW4()+
				     gpitch_angle*recommand.getW5()+
				     groll_angle*recommand.getW6()+
				     recommand.getW7();
		  
		  map.put(recommand.getProductid(),ans); 
		  ConsoleLog.PrintInfo(getClass(),"对于第"+recommand.getProductid()+"商品->评分:"+ans);
				     
	   }
	   
	   entityManager.close();
	   return map;
   }
   
   public  void updateclassProduct(int productid,int male,int age,
           double yaw_angle,double pitch_angle,double roll_angle,double smile)
   {
	   
	   
	   //获取实体管理者
       EntityManager  entityManager=EntityManagerHelper.getEntityManager();
       if(!entityManager.getTransaction().isActive())
   	    entityManager.getTransaction().begin();
       
	   final String queryString="select model from Recommand model where model.productid=?1";
	   
	   Query query = entityManager.createQuery(queryString);
	   query.setParameter(1, productid);
	   
	   try
 	   {
		   
		   
		 
		   @SuppressWarnings("unchecked")
		   List<Recommand> Recommands=query.getResultList();
		   Recommand recommand=Recommands.get(0);
		   int y=sigmod(smile,roll_angle);
		  
		   ConsoleLog.PrintInfo(getClass(),"sigmod->评分:"+y);
		   
		   
		   double w1=recommand.getW1();
		   double w2=recommand.getW2();
		   double w3=recommand.getW3();
		   double w4=recommand.getW4();
		   double w5=recommand.getW5();
		   double w6=recommand.getW6();
		   double w7=recommand.getW7();
		   
           smile=smile/100.0;
           double amale=male;
           double aage=age/100.0;
           yaw_angle=yaw_angle/20.0;
           pitch_angle=pitch_angle/20.0;
           roll_angle=roll_angle/20.0;
		   
		  
		   double ans=smile*w1+amale*w2+aage*w3+ yaw_angle*w4+pitch_angle*w5+roll_angle*w6+w7;
           
		   double uprate=learning_rate*(ans-y);

		   System.out.println("uprate->"+uprate+"  "+"ans->"+" "+ans);

		   recommand.setW1(w1-uprate*smile);
		   recommand.setW2(w2-uprate*male);
		   recommand.setW3(w3-uprate*age);
		   recommand.setW4(w4-uprate*yaw_angle);
		   recommand.setW5(w5-uprate*pitch_angle);
		   recommand.setW6(w6-uprate*roll_angle);
		   recommand.setW7(w7-uprate);
		   

		   entityManager.persist(recommand);
		   
	   }
	   catch(Exception e)
	   {
		   entityManager.close();
		   return;
	   }
	   
	   entityManager.getTransaction().commit();
	   entityManager.close();

        
   }
   
   
   public void initProduct(int productid)
   {
	   
	   
	   ProductDAO productDAO=new ProductDAO();
	   Product product=productDAO.findById(productid);
	   
	   if( product==null)
	   {
		   ConsoleLog.PrintInfo(getClass(),"初始化商品权值失败未找到相应商品！");
		   return;
	   }
	   
	   RecommandDAO recommandDAO=new RecommandDAO();
	   Recommand recommand=recommandDAO.findById(productid);
	   
	   if( recommand!=null )return;
	  
	   EntityManager  entityManager=EntityManagerHelper.getEntityManager();
       if(!entityManager.getTransaction().isActive())
    	    entityManager.getTransaction().begin();
       
       Query query;
       String query1="select avg(model.w1) from Recommand model";
       query=entityManager.createQuery(query1);
       Double w1=(Double)query.getSingleResult();
       if(w1==null||w1==0.0){w1=Math.random();}
       
       String query2="select avg(model.w2) from Recommand model";
       query=entityManager.createQuery(query2);
       Double w2=(Double)query.getSingleResult();
       if(w2==null||w2==0.0){w2=Math.random();}
       
       String query3="select avg(model.w3) from Recommand model";
       query=entityManager.createQuery(query3);
       Double w3=(Double)query.getSingleResult();
       if(w3==null||w3==0.0){w3=Math.random();}
       
       String query4="select avg(model.w4) from Recommand model";
       query=entityManager.createQuery(query4);
       Double w4=(Double)query.getSingleResult();
       if(w4==null||w4==0.0){w4=Math.random();}
       
       String query5="select avg(model.w5) from Recommand model";
       query=entityManager.createQuery(query5);
       Double w5=(Double)query.getSingleResult();
       if(w5==null||w5==0.0){w5=Math.random();}
       
       String query6="select avg(model.w6) from Recommand model";
       query=entityManager.createQuery(query6);
       Double w6=(Double)query.getSingleResult();
       if(w6==null||w6==0.0){w6=Math.random();}
       
       String query7="select avg(model.w7) from Recommand model";
       query=entityManager.createQuery(query7);
       Double w7=(Double)query.getSingleResult();
       if(w7==null||w7==0.0){w7=Math.random();}
       
       
       recommand=new Recommand();
       recommand.setProductid(productid);
       recommand.setW1(w1);
       recommand.setW2(w2);
       recommand.setW3(w3);
       recommand.setW4(w4);
       recommand.setW5(w5);
       recommand.setW6(w6);
       recommand.setW7(w7);
     
       entityManager.persist(recommand);
       entityManager.getTransaction().commit();
	   entityManager.close(); 
	   
   }
   
   
   //该函数用于对人脸倾向进行评分
   private int sigmod(double smile,double roll_angle)
   {
	   
	   System.out.println(smile+" "+roll_angle);
	   roll_angle=reg(roll_angle);
	   double x=(0.25*smile-1.2*roll_angle)/10.0;
	   double ans=1.0/(1+Math.exp(-x));
	   
	   if(ans<=0.2)return 1;//不满意
	   else if(ans<=0.4) return 2;//较不满意
	   else if(ans<=0.6) return 3;//中等
	   else if(ans<=0.8) return 4;//较满意
	   return 5;//满意
   }
   
   private double reg(double x)
   {
	    if(x<0)return -x;
	    return x;
   }
	
	
}
