package servlert;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modle.ListClass;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.GetRemoteAddr;

/**
 * Servlet implementation class ClassAPI
 */
@WebServlet("/ClassAPI")
public class ClassAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		String add=GetRemoteAddr.getIpAddress(request);
		System.out.println(add);
		ListClass.checkAdd(add);
		PrintWriter out=response.getWriter();
		String type = "";
		type=request.getParameter("type");
		JSONObject obj=new JSONObject();
		if(type.equals("515")) {
			JSONArray jsonArr=ListClass.findList();
			obj.accumulate("result", jsonArr);
			out.println(obj);
		}
		if(type.equals("720")) {
			String data=request.getParameter("data");
			if(data==null) {
				//System.out.println("Пе");
				obj.accumulate("result", "fail");
				out.println(obj);
			}else {
				//System.out.println("гажЕ");
				boolean flag=ListClass.addVotes(data);
				if(flag) {
					obj.accumulate("result", "success");
				}else {
					obj.accumulate("result", "fail");
				}
				out.println(obj);
			}
		}
	}

}
