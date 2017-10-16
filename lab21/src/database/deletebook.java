package database;

import java.sql.*;

import com.opensymphony.xwork2.ActionSupport;

public class deletebook extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public String isbn;
	
	public String getisbn(){
		return isbn;
	}
	
	public void setisbn(String isbn){
		this.isbn = isbn;
	}
	
	public String execute(){
		String driver = "com.mysql.jdbc.Driver";
		String username = System.getenv("ACCESSKEY");
		String password = System.getenv("SECRETKEY");
		//System.getenv("MYSQL_HOST_S"); 为从库，只读
		String dbUrl = String.format("jdbc:mysql://%s:%s/%s", System.getenv("MYSQL_HOST"), System.getenv("MYSQL_PORT"), System.getenv("MYSQL_DB"));
		try {
		    Class.forName(driver).newInstance();
		    Connection conn = DriverManager.getConnection(dbUrl, username, password);
		    System.out.println("数据库连接成功！");
			String de = "delete from Book where";
			de += " ISBN = ?";
			PreparedStatement ps = conn.prepareStatement(de);
			ps.setString(1,isbn);
			ps.executeUpdate();
			conn.close();
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}
}
