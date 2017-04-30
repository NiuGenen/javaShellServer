package ng.servlet.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
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
import ng.jms.hbm.Join_user_topic;
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
