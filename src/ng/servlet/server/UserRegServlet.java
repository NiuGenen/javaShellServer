package ng.servlet.server;

import java.io.DataOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ng.jms.hbm.HDB;
import ng.jms.hbm.Table_user;

/**
 * Servlet implementation class UserRegServlet
 */
@WebServlet("/UserRegServlet")
public class UserRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserRegServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String passwd = request.getParameter("password");
		
		Table_user user = new Table_user();
		user.setName( username );
		user.setPasswd( passwd );
		boolean success = HDB.getInstance().save( user );
		
		JSONObject ret = new JSONObject();
		if(success){
			ret.put("register", true);
		}
		else{
			ret.put("register", false);
		}
		
		DataOutputStream output = new DataOutputStream( response.getOutputStream() );
		output.writeBytes( ret.toString() );
		output.close();
		
		//doGet(request, response);
	}

}
