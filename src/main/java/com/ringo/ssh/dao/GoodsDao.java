package com.ringo.ssh.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ringo.ssh.entity.Goods;

@Repository("GoodsDao")
public class GoodsDao implements IGoodsDao{

	@Resource
	private SessionFactory sessionFactory;
	
	/**
	 * 保存对象
	 * 
	 * @param Goods goods
	 * @return null
	 */
	@Override
	public void save(Goods goods) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(goods);
	}

	@Override
	public void update(Goods goods) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(goods);
		
	}

	@Override
	public void delete(int goodsId) {
		// TODO Auto-generated method stub
		Goods g_delete=(Goods)sessionFactory.getCurrentSession().get(Goods.class, goodsId);
		sessionFactory.getCurrentSession().delete(g_delete);
	}

	@Override
	public Goods getGoodsByGoodId(int goodsId) {
		// TODO Auto-generated method stub
		return (Goods) sessionFactory.getCurrentSession().createQuery("from Goods where goodsId =?")
				.setParameter(0, goodsId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Goods> getGoodsByGoodsName(String goodsName) {
		// TODO Auto-generated method stub
		String hql="from Goods where goodsName like :param order by goodsDate DESC";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setString("param", "%"+goodsName+"%").list();
	}
}
