package ng.servlet.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;

import ng.jms.hbm.HDB;
import ng.jms.hbm.Join_user_news;
import ng.jms.hbm.Join_user_topic;
import ng.jms.hbm.Table_news;
import ng.jms.hbm.Table_topic;
import ng.jms.hbm.Table_user;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		
		System.out.println("user " + username + "login. passwd: " + passwd);
		
		DataOutputStream output = new DataOutputStream(response.getOutputStream());
		
		List<Object> res = HDB.getInstance().get(Table_user.class, 
				Restrictions.eq("name", username),
				Restrictions.eq("passwd", passwd));
		
		JSONObject json_ret = new JSONObject();
		//login: 1 or 0
		//topics: ["topicname1","topicname2"]
		//userid: id
		//news:	[{""},{}]
		if(res != null && res.size() >= 1){
			//return login succeed
			json_ret.put("login", 1 );
			
			res = HDB.getInstance().get(Join_user_topic.class, 
					Restrictions.eq("name", username),
					Restrictions.eq("passwd", passwd));
			
			if(res != null && res.size() >= 1 ){
				Join_user_topic ut = (Join_user_topic)res.get(0);
				//return user's topic
				Set<Table_topic> set_topics = ut.getTopic();
				if( set_topics != null){
					JSONArray json_topics_array = new JSONArray();
					for(Table_topic t: ut.getTopic()){
						json_topics_array.put( t.getName() );
					}
					json_ret.put("topics", json_topics_array);
				}
				//return user id
				json_ret.put("userid", ut.getUserid() );
			}
			else{
				json_ret.put("topics", new JSONArray());
				json_ret.put("userid", -1 );
			}
			
			res = HDB.getInstance().get(Join_user_news.class, 
					Restrictions.eq("name", username),
					Restrictions.eq("passwd", passwd));
			
			if(res != null && res.size() >= 1 ){
				Join_user_news un = (Join_user_news)res.get(0);
				//return user's news
				Set<Table_news> set_news = un.getNews();
				if( set_news != null){
					JSONArray json_news_array = new JSONArray();
					
					List<Object> topics_all = HDB.getInstance().get(Table_topic.class);
					Map<Integer, String> id_to_topic = new HashMap<>();
					if(topics_all != null && topics_all.size() >= 1){
						for(Object tt: topics_all){
							Table_topic t = (Table_topic)tt;
							id_to_topic.put(t.getId(), t.getName());
						}
					}
					
					for(Table_news n: un.getNews()){
						JSONObject news = new JSONObject();
						news.put("title", n.getTitle() );
						news.put("cntnt", n.getCntnt() );
						news.put("time", n.getTime() );
						news.put("userid", n.getUserid() );
						news.put("username", username);
						news.put("topicid", n.getTopicid() );
						news.put("topicname", id_to_topic.get(n.getTopicid()) );
						json_news_array.put( news );
					}
					json_ret.put("news", json_news_array);
				}
			}
			else{
				json_ret.put("news", new JSONArray());
			}
		}
		else{
			//return login failed
			json_ret.put("login", 0 );
		}
		
		System.out.println( json_ret.toString() );
		output.writeBytes( json_ret.toString() );
		output.close();
		
		//doGet(request, response);
	}

}
