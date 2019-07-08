package com.ringo.ssh.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.ringo.ssh.dao.BaseTestCaseJunit44;
import com.ringo.ssh.entity.Order_Goods;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class OrderDetailsServiceTest extends BaseTestCaseJunit44{

	@Resource
	private IOrderDetailsService orderDetailsService;

	@Test
	public void test() {
		List<Order_Goods> ogs=orderDetailsService.getOrderDetailsByUserId(2);
		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
		jsonConfig.setExcludes(new String[] { "goods","shopCarList","orderDetails"}); // 此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray jsonArray2 = JSONArray.fromObject(ogs,jsonConfig);// 将集合转换为json格式
		System.out.println(jsonArray2);
	}

}
