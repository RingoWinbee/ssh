package com.ringo.ssh.dao;

import java.util.List;
import com.ringo.ssh.entity.Reviews;

/**
 * Reviews抽象持久层层接口:
 * 		
 * @author 李卓岚
 *
 */
public interface IReviewsDao {
	public void save(Reviews reviews);
	public void update(Reviews reviews);
	public void delete(int reviewsId);
	//查询
	public Reviews getReviewsByReviewsId(int reviewsId);
	public Reviews getReviewsByUserId(int userId);
	public List<Reviews> getReviewsByGoodsId(int goodsId);
}
