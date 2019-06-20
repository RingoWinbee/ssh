package com.ringo.util;

import java.util.Random;

public class CreatRandCode {
	/*
	程序实现的是打印出6位验证码有字符有数字交替出现
	*/
	 static String  result = "";
	 static String  result2= "";
	public String creatCode()
	{
	  for(int i = 1;i <=6; i++)                  //6次执行输出6个不同字符
		    {
			//判断产生的随机数是0还是1，是0进入if语句用于输出数字，是1进入else用于输出字符
		       int rd = Math.random() >= 0.5 ? 1 : 0;   
			
		        if(rd == 0)
		            {
					   Random r = new Random();      
					   result = r.nextInt(9) +1 + "";      //产生1-9数字
			     
				    }
		        else{
		         char[] A_z = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
				 Random sc = new Random(); 
			     int sub = sc.nextInt(A_z.length);
				 result = A_z[sub] + "";      //产生A——z字符
				
					}
			     result2 +=result;
		    }
	  return result2;
	}
}
