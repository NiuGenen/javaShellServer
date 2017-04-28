package ng.servlet.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ng.jms.hbm.HDB;
import ng.jms.hbm.Table_topic;

/**
 * Servlet implementation class GetTopicsServlet
 */
@WebServlet("/GetTopicsServlet")
public class GetTopicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GetTopicsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String posttime = request.getParameter("posttime");
		
		System.out.println(posttime);
		System.out.println(System.currentTimeMillis());
		
		DataOutputStream output = new DataOutputStream(response.getOutputStream());
		//output.writeBytes("asdasdasdasd");
		
		List<Object> topics = HDB.getInstance().get(Table_topic.class);
		StringBuffer ret = new StringBuffer();
		ret.append("{'topics':[");
		for(int i = 0; i < topics.size(); ++i){
			Table_topic t = (Table_topic)(topics.get(i));
			ret.append("{'id':'" + t.getId() + "','name':'" + t.getName() + "'}");
			if((i+1)!=topics.size()){
				ret.append(",");
			}
		}
		ret.append("]}");
		//json: {'topics':[{'id':'1','name':'topic'},{...}]}
		
		output.writeBytes(ret.toString());
		
		output.close();
		
		//doGet(request, response);
	}

}
