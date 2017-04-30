package ng.servlet.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;

import ng.jms.hbm.HDB;
import ng.jms.hbm.Join_user_topic;
import ng.jms.hbm.Table_subscribe;
import ng.jms.hbm.Table_topic;

/**
 * Servlet implementation class UserSubscribeServlet
 */
@WebServlet("/UserSubscribeServlet")
public class UserSubscribeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserSubscribeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataOutputStream output = new DataOutputStream(response.getOutputStream());
		try {
			int userid = Integer.parseInt( request.getParameter("userid") );
			boolean ifsub = Boolean.parseBoolean( request.getParameter("subscribe") );
			int topicid = Integer.parseInt( request.getParameter("topicid") );
			String topicname = request.getParameter("topicname");

			if(ifsub){
				Table_subscribe sub = new Table_subscribe();
				sub.setUserid( userid );
				sub.setTopicid( topicid );
				HDB.getInstance().save( sub );
				
				List<Object> res = HDB.getInstance().get(Join_user_topic.class, 
						Restrictions.eq("id", userid) );
				
				if(res != null && res.size() >= 1 ){
					Join_user_topic ut = (Join_user_topic)res.get(0);
					Table_topic topic = new Table_topic();
					topic.setId( topicid );
					topic.setName( topicname );
					ut.getTopic().add( topic );
					HDB.getInstance().update( ut );
				}
			}
			else{	//ubsub
				Table_subscribe sub = new Table_subscribe();
				sub.setUserid( userid );
				sub.setTopicid( topicid );
				HDB.getInstance().save( sub );
				
				List<Object> res = HDB.getInstance().get(Join_user_topic.class, 
						Restrictions.eq("id", userid) );
				
				if(res != null && res.size() >= 1 ){
					Join_user_topic ut = (Join_user_topic)res.get(0);
					for(Table_topic t:ut.getTopic()){
						if(t.getId() == topicid){
							ut.getTopic().remove(t);
						}
					}
					HDB.getInstance().update( ut );
				}
			}
			
			output.writeBytes("{'subscribe':'True'}");
		} catch (Exception e) {
			output.writeBytes("{'subscribe':'False'}");
			e.printStackTrace();
		} finally{
			if(output != null)
				output.close();
		}
		
		//doGet(request, response);
	}

}
