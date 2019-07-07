package com.ringo.ssh.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.ringo.ssh.entity.Reviews;

@Repository("ReviewsDao")
public class ReviewsDao implements IReviewsDao {

	@Resource
	private SessionFactory sessionFactory;

	@Override
	public void save(Reviews reviews) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(reviews);
	}

	@Override
	public void update(Reviews reviews) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(reviews);
	}

	@Override
	public void delete(int reviewsId) {
		// TODO Auto-generated method stub
		Reviews r_delete = (Reviews) sessionFactory.getCurrentSession().get(Reviews.class, reviewsId);
		sessionFactory.getCurrentSession().delete(r_delete);
	}

	@Override
	public Reviews getReviewsByReviewsId(int reviewsId) {
		// TODO Auto-generated method stub
		return (Reviews) sessionFactory.getCurrentSession().createQuery("from Reviews where reviewsId =?")
				.setParameter(0, reviewsId).uniqueResult();
	}

	@Override
	public Reviews getReviewsByOrderDetailsId(int orderDetailsId) {
		// TODO Auto-generated method stub
		return (Reviews) sessionFactory.getCurrentSession().createQuery("from Reviews where orderDetailsId =?")
				.setParameter(0, orderDetailsId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reviews> getAllReviews() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Reviews")
				.list();
		//return sessionFactory.getCurrentSession().createSQLQuery("select * from reviews").list();
	}
}
