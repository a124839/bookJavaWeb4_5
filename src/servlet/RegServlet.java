package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
/**
 * @author 570
 *	先进行初始化，在重写dopost方法
 *初始化中要连接数据库jdbd
 *1.创建Connection对象
 *2.Class.forName()加载驱动
 *3.数据库连接的url
 *4.最后调用DriverManager.getConnection()方法，方法中传入url，用户名，密码三个对象
 *
 */

@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Connection connection;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init();
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载驱动
			String url = "jdbc:mysql//localhost:3306/book_java_web";
			connection = DriverManager.getConnection(url,"root","root");//连接数据库的数据库，用户名，密码
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置对象编码
		response.setCharacterEncoding("GBK");
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		//获取表单中的属性值
		String username =  request.getParameter("username");
		String password =  request.getParameter("password");
		String gender =  request.getParameter("gender");
		String question =  request.getParameter("question");
		String answer =  request.getParameter("answer");
		String email =  request.getParameter("email");
		//判断数据库是否连接成功
		if (connection!=null) {
			try {
				//插入sql语句（使用？占位符）
				String sql = "insert servlet_tb_user(username,password,gender,question,answer,email)"
						+"value(?,?,?,?,?,?)";
				//创建PreparedStatement对象
				PreparedStatement ps = connection.prepareStatement(sql);
				//对sql语句中的参数动态赋值
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, gender);
				ps.setString(4, question);
				ps.setString(5, answer);
				ps.setString(6, email);
				//执行更新操作
				ps.executeUpdate();
				//获取PrintWriter对象
				PrintWriter out = response.getWriter();
				//输出注册结果信息
				out.println("<h1 aling='center'>");
				out.print(username+"注册成功");
				out.println("</h1>");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else {
			//发送数据库连接错误提示信息
			response.sendError(500,"数据库连接错误");
		}
	}

}
