package ng.jms.hbm;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;

public class TestHDB {

	private HDB hdb = null;
	
	@Before
	public void setup(){
		hdb = HDB.getInstance();
	}
	
	@Test
	public void test_table_news(){
		System.out.println("[get news]");
		
		List<Object> res = hdb.get(Table_news.class);
		
		for(Object r:res){
			Table_news n = (Table_news)r;

			System.out.print(n.getId()+" ");
			System.out.print(n.getTitle()+" ");
			System.out.print(n.getCntnt()+" ");
			System.out.print(n.getTime()+" ");
			System.out.print(n.getUserid()+" ");
			System.out.println(n.getTopicid()+" ");
		}
	}
	
	@Test
	public void test_table_topic(){
		System.out.println("[get topic]");
		
		List<Object> res = hdb.get(Table_topic.class);
		
		for(Object r: res){
			Table_topic u = (Table_topic)r;

			System.out.print(u.getId()+" ");
			System.out.println(u.getName()+" ");
		}
	}
	
	@Test
	public void test_table_user(){
		
		System.out.println("[get user]");
		
		List<Object> res = hdb.get(Table_user.class);
		
		for(Object r: res){
			Table_user u = (Table_user)r;

			System.out.print(u.getId()+" ");
			System.out.print(u.getName()+" ");
			System.out.println(u.getPasswd());
		}
	}
	
	@Test
	public void test_join_topic_user(){

		System.out.println("[get topic_user]");
		
		List<Object> res = hdb.get(Join_topic_user.class);
		
		assertTrue( res != null );

		for(Object r: res){
			Join_topic_user u = (Join_topic_user)r;
			
			assertTrue( u != null );
			
			System.out.println(u.getName());
			
			Set<Table_user> users = u.getUser();
			for(Table_user user:users){
				System.out.println(user.getName());
				System.out.println(user.getPasswd());
			}
		}
	}
	
	@Test
	public void test_join_user_topic(){

		System.out.println("[get user_topic]");
		
		List<Object> res = hdb.get(Join_user_topic.class);
		
		assertTrue( res != null );

		for(Object r: res){
			Join_user_topic u = (Join_user_topic)r;
			
			assertTrue( u != null );
			
			System.out.println(u.getName());

			System.out.println(u.getPasswd());
			
			Set<Table_topic> topics = u.getTopic();
			for(Table_topic topic:topics){
				System.out.println(topic.getName());
			}
		}
	}
	
	@Test
	public void test_join_user_news(){
		
		System.out.println("[get user_news]");
		
		List<Object> res = hdb.get(Join_user_news.class);
		
		for(Object r: res){
			Join_user_news u = (Join_user_news)r;

			System.out.print(u.getUserid() + " ");
			System.out.println(u.getUsername());
			
			Set<Table_news> news = u.getNews();
			for(Table_news n: news){
				System.out.print(n.getId()+" ");
				System.out.print(n.getTitle()+" ");
				System.out.print(n.getCntnt()+" ");
				System.out.print(n.getTime()+" ");
				System.out.print(n.getUserid()+" ");
				System.out.println(n.getTopicid()+" ");
			}
		}
	}
	
	@Test
	public void test_join_new_user_topic(){
		System.out.println("[get news_user_topic]");
		
		List<Object> res = hdb.get(Join_news_user_topic.class);
		
		for(Object r: res){
			Join_news_user_topic nut = (Join_news_user_topic)r;

			System.out.print(nut.getNewsid() +" ");
			System.out.print(nut.getTitle() +" ");
			System.out.print(nut.getCntnt() +" ");
			System.out.print(nut.getTime() +" ");
			
			Table_user u = nut.getUser();

			System.out.print(u.getId() +" ");
			System.out.print(u.getName() +" ");
			System.out.print(u.getPasswd() +" ");
			
			Table_topic t = nut.getTopic();
			
			System.out.print( t.getId() + " ");
			System.out.println(t.getName());
		}
	}
	
}
