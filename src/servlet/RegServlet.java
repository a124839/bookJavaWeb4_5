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
 *	�Ƚ��г�ʼ��������дdopost����
 *��ʼ����Ҫ�������ݿ�jdbd
 *1.����Connection����
 *2.Class.forName()��������
 *3.���ݿ����ӵ�url
 *4.������DriverManager.getConnection()�����������д���url���û�����������������
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
			Class.forName("com.mysql.jdbc.Driver");//��������
			String url = "jdbc:mysql//localhost:3306/book_java_web";
			connection = DriverManager.getConnection(url,"root","root");//�������ݿ�����ݿ⣬�û���������
			
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
		//���ö������
		response.setCharacterEncoding("GBK");
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		//��ȡ���е�����ֵ
		String username =  request.getParameter("username");
		String password =  request.getParameter("password");
		String gender =  request.getParameter("gender");
		String question =  request.getParameter("question");
		String answer =  request.getParameter("answer");
		String email =  request.getParameter("email");
		//�ж����ݿ��Ƿ����ӳɹ�
		if (connection!=null) {
			try {
				//����sql��䣨ʹ�ã�ռλ����
				String sql = "insert servlet_tb_user(username,password,gender,question,answer,email)"
						+"value(?,?,?,?,?,?)";
				//����PreparedStatement����
				PreparedStatement ps = connection.prepareStatement(sql);
				//��sql����еĲ�����̬��ֵ
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, gender);
				ps.setString(4, question);
				ps.setString(5, answer);
				ps.setString(6, email);
				//ִ�и��²���
				ps.executeUpdate();
				//��ȡPrintWriter����
				PrintWriter out = response.getWriter();
				//���ע������Ϣ
				out.println("<h1 aling='center'>");
				out.print(username+"ע��ɹ�");
				out.println("</h1>");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else {
			//�������ݿ����Ӵ�����ʾ��Ϣ
			response.sendError(500,"���ݿ����Ӵ���");
		}
	}

}
