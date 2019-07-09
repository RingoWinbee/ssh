package com.ringo.ssh.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ringo.ssh.entity.Category;
import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.Reviews_User;
import com.ringo.ssh.service.IGoodsService;
import com.ringo.util.FileUpload;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/goods")
public class GoodController {
	
	@Resource(name = "GoodsService")
	private IGoodsService goodsService;
	
	/**
	 * 通过PrintWriter将响应数据写入response，ajax可以接受到这个数据
	 * 
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	private void renderData(HttpServletResponse response, String data) throws IOException {
		PrintWriter printWriter = null;
		printWriter = response.getWriter();
		printWriter.print(data);
		if (null != printWriter) {
			printWriter.flush();
			printWriter.close();
		}
	}
	
	/**
	 * 获得所有商品从新到旧开始排序
	 * 
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/getAllGoods", method = RequestMethod.GET)
	public void getAllGoods(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<Goods> goods=goodsService.getGoodsByName("");
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[] { "goods","shopCarList","orderDetails"}); // 此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray jsonArray2 = JSONArray.fromObject(goods,jsonConfig);//将集合转换为json格式
		renderData(response, jsonArray2.toString());
	}
	
	/**
	 * 通过商品名字搜索商品从新到旧开始排序
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchGoods", method = RequestMethod.GET)
	public void searchGoods(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String serachName=(String)request.getParameter("serachName");
		List<Goods> goods=goodsService.getGoodsByName(serachName);
		if(goods.size()==0)
			renderData(response,"搜索不到结果");
		else {
			JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
			jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
			jsonConfig.setExcludes(new String[] { "goods","shopCarList","orderDetails"}); // 此处是亮点，只要将所需忽略字段加到数组中即可
			JSONArray jsonArray2 = JSONArray.fromObject(goods,jsonConfig);//将集合转换为json格式
			renderData(response, jsonArray2.toString());
		}
	}
	
	/**
	 * 通过类别名字搜索商品从新到旧开始排序
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchGoodsByCategoryName", method = RequestMethod.GET)
	public void searchGoodsByCategoryName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String serachName=(String)request.getParameter("serachName");
		List<Category> goods=goodsService.getGoodsByCategoryName(serachName);
		if(goods.size()==0)
			renderData(response,"搜索不到结果");
		else {
			JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
			jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
			jsonConfig.setExcludes(new String[]{"category","shopCarList","orderDetails"});  //此处是亮点，只要将所需忽略字段加到数组中即可
			JSONArray jsonArray2 = JSONArray.fromObject(goods,jsonConfig);//将集合转换为json格式
			renderData(response, jsonArray2.toString());
		}
	}
	
	/**
	 * 通过商品ID查看商品详情页
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/showGoodsDetial", method = RequestMethod.GET)
	public void showGoodsDetial(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int goodsId=Integer.parseInt(request.getParameter("goodsId"));
		Goods g=goodsService.getGoodsByGoodId(goodsId);
		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
		jsonConfig.setExcludes(new String[] { "goods","shopCarList","orderDetails"}); // 此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray goods = JSONArray.fromObject(g, jsonConfig);// 将集合转换为json格式
		
		renderData(response, goods.toString());
		
	}
	
	/**
	 * 通过商品ID获得商品所有评论
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/getGoodsAllReviews", method = RequestMethod.GET)
	public void getGoodsAllReviews(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int goodsId=Integer.parseInt(request.getParameter("goodsId"));
		List<Reviews_User> rus=goodsService.getReviewsByGoodsId(goodsId);
		JSONArray reviews = JSONArray.fromObject(rus);//将集合转换为json格式
		renderData(response, reviews.toString());
	}
	
	/**
	 * 
	 * 用于给商品上传图片
	 * 
	 * @param HttpServletRequest  request
	 * @param HttpServletResponse response
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public void imageUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
		MultipartFile file = mreq.getFile("file");
		String frontName = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/upload/";
		System.out.println(frontName);
		FileUpload f = new FileUpload();
		String fileName = f.imageUpload(file, frontName);
		renderData(response, fileName);
	}

	/**
	 * 
	 * 用于删除商品之前的图片
	 * 
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse response
	 */
	@RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
	public void deleteImage(HttpServletRequest request, HttpServletResponse response) {
		boolean flag = false;
		String lastFileName=(String)request.getParameter("lastFileName");
		System.out.println("要删除的文件名:"+lastFileName);
		System.out.println("realpath:"+request.getSession().getServletContext().getRealPath("/"));
		String filePath=request.getSession().getServletContext().getRealPath("/")+"WEB-INF/upload/"+lastFileName;
		System.out.println(filePath);
		File file=new File(filePath);
		
		//判断文件是否存在
		if (file.exists()) {
			System.out.println("存在该文件");
            flag = file.delete();
        }
		if (flag) {
            System.out.println("删除文件成功");
        } else {
            System.out.println("删除文件出错");
        }
	}
	
}
