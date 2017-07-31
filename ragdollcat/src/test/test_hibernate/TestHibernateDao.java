package test.test_hibernate;
import javax.annotation.Resource;


/** * @author zhang_chl 
    * @date 2017年7月14日下午4:23:34
    */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.com.zhang.blog.entity.Person;
public class TestHibernateDao {
	@Resource
	private SessionFactory sessionFactory;     /*待解决：无法注入*/
	
	private Session session;
	
	private Transaction transaction;
	
	@Before
	public void before(){
		if(sessionFactory==null)
			System.out.println("not exit");
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	@After
	public void after(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	@Test
	public void test1(){
		Person p = new Person();
		p.setName("小张");
		p.setPassward("123");
		p.setDatecreated("2017-07-14");
		session.saveOrUpdate(p);
	}
	
}
