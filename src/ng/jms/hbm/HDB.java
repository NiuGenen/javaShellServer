package ng.jms.hbm;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

@SuppressWarnings("unused")
public class HDB {

	private static HDB hdb = new HDB();
	private HDB(){
		setup();
	}
	public static HDB getInstance(){
		return hdb;
	}
	
	private Configuration cfg = null;
	private SessionFactory factory = null;
	private Session session = null;
	private void setup(){
		cfg = new Configuration().configure();
		factory = cfg.buildSessionFactory();
	}
	public Session getSession(){
		if(session == null)
			session = factory.openSession();
		return session;
	}
	public void close(){
		if(session != null){
			session.close();
			session = null;
		}
	}
	
	public List<Object> get(Class table_class, SimpleExpression... se){
		if(session == null)
			session = factory.openSession();
		
		if(session != null){
			List<Object> res_list = null;
			try{
				session.beginTransaction();

				Criteria cr = session.createCriteria( table_class );
				for(SimpleExpression s: se) cr.add(s);
				res_list = cr.list();
				
				session.getTransaction().commit();
				return res_list;
			}catch(Exception e){
				session.getTransaction().rollback();
				return null;
			}
		}
		return null;
	}
	
	public boolean update(Object obj){
		if(session == null)
			session = factory.openSession();
		
		if(session != null){
			try{
				session.beginTransaction();
				
				session.update(obj);
				
				session.getTransaction().commit();
				return true;
			}catch(Exception e){
				session.getTransaction().rollback();
				return false;
			}
		}
		return false;
	}
	

	public boolean delete(Object obj){
		if(session == null)
			session = factory.openSession();
		
		if(session != null){
			try{
				session.beginTransaction();
				
				session.delete(obj);
				
				session.getTransaction().commit();
				return true;
			}catch(Exception e){
				session.getTransaction().rollback();
				return false;
			}
		}
		return false;
	}
	
	public boolean save(Object obj){
		if(session == null)
			session = factory.openSession();

		if(session != null){
			try{
				session.beginTransaction();
				
				session.save(obj);
				
				session.getTransaction().commit();
				return true;
			}catch(Exception e){
				session.getTransaction().rollback();
				return false;
			}
		}
		return false;
	}
}
