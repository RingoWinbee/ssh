package com.ringo.ssh.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import com.ringo.ssh.dao.BaseTestCaseJunit44;
import com.ringo.ssh.entity.Reviews_User;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class GoodsServiceTest extends BaseTestCaseJunit44{

	@Resource
	private IGoodsService goodsService;
	
	@Test
	public void test() {
		List<Reviews_User> rus=goodsService.getReviewsByGoodsId(2);
//		for(Reviews_User ru:rus) {
//			System.out.println(ru.getUserName()+"发布了评论："+ru.getReviewsInfo()+"在"+ru.getReviewsDate());
//		}
		//JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		//jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		//jsonConfig.setExcludes(new String[]{"reviews","users","goods"});  //此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray jsonArray2 = JSONArray.fromObject(rus);//将集合转换为json格式
		System.out.println(jsonArray2);
	}

}
