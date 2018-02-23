package dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Sample;
import hibernateConfig.HibernateConfiguration;


public class SampleDao {
	
	public HibernateConfiguration hiberconfig = null;
	
	public SampleDao(){
		hiberconfig = new HibernateConfiguration();
	}
	
	@Transactional
	public void persist(Sample entity){
		Session session = null;
		Transaction tx = null;
		try {
			
			session = hiberconfig.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.clear();
			
			session.persist(entity);			
			
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		}
		finally{
			if (session.isOpen()) {
				 session.flush();
				 session.close();
				}
		}
		
	}

}
