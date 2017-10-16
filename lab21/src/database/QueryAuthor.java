package database;

import java.sql.*;
import com.opensymphony.xwork2.ActionSupport;
import java.util.*;

public class QueryAuthor extends ActionSupport{   
	private static final long serialVersionUID = 1L;
	public String Authorname;
	public ArrayList<Booklist> titles = new ArrayList<Booklist>(); 
	
	public String getAuthorname(){
		return Authorname;
	}
	
	public void setAuthorname(String Authorname){
		this.Authorname = Authorname;
	}
	
	public ArrayList<Booklist> gettitles(){     
		return titles;
	}
	
	public void settitles(ArrayList<Booklist> titles){
		this.titles = titles;
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
			//Statement sql = conn.createStatement();
			String query = "select AuthorID from Author where";
			query += " Name = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, Authorname);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int id = rs.getInt("AuthorID");
				String bg = "select * from Book where";
				bg += " AuthorID = ?";
				PreparedStatement qs = conn.prepareStatement(bg);
				qs.setInt(1, id);
				ResultSet title = qs.executeQuery();
				while(title.next()){
					String temp = title.getString("Title");
					String is = title.getString("ISBN");
					Booklist tmp = new Booklist(Authorname,temp,is);
					titles.add(tmp);
				}
				conn.close();
				return "success";
			}
			else{
				return "error";
			}
		} catch (Exception e) {
			return "error";
		}
	}
}

class Booklist{
	public String authorname;
	public String title;
	public String isbn;
	public Booklist(String authorname, String title, String isbn){
		this.authorname = authorname;
		this.title = title;
		this.isbn = isbn;
	};
	public String getauthorname(){
		return authorname;
	}
	public void setauthorname(String authorname){
		this.authorname = authorname;
	}
	public String gettitle(){
		return title;
	}
	public void settitle(String title){
		this.title = title;
	}
	public String getisbn(){
		return isbn;
	}
	public void setisbn(String isbn){
		this.isbn = isbn;
	}
}