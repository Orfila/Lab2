package database;

import java.sql.*;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class showdetail extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public String bookname;
	public ArrayList<detail> info = new ArrayList<detail>(); 
	
	public String getbookname(){
		return bookname;
	}
	
	public void sebookname(String bookname){
		this.bookname = bookname;
	}
	
	public ArrayList<detail> getinfo(){     
		return info;
	}
	
	public void setinfo(ArrayList<detail> info){
		this.info = info;
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
			//System.out.println(bookname);
			//Statement sql = conn.createStatement();
			String query = "select * from Book where";
			query += " Title = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, bookname);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				//System.out.println("2");
				int authorid = rs.getInt("AuthorID");
				//System.out.println(authorid);
				String isbn = rs.getString("ISBN");
				//System.out.println(isbn);
				String publisher = rs.getString("Publisher");
				//System.out.println(publisher);
				String publishdate = rs.getString("PublishDate");
				//System.out.println(publishdate);
				int price = rs.getInt("Price");
				//System.out.println(price);
				String query1 = "select * from Author where";
				query1 += " AuthorID = ?";
				PreparedStatement qs = conn.prepareStatement(query1);
				qs.setInt(1, authorid);
				ResultSet authordetail = qs.executeQuery();
				if(authordetail.next()){
					String name = authordetail.getString("Name");
					//System.out.println(name);
					int age = authordetail.getInt("Age");
					//System.out.println(age);
					String country = authordetail.getString("Country");
					//System.out.println(country);
					detail tmp = new detail(isbn,bookname,authorid,publisher,publishdate,price,name,age,country);
					info.add(tmp);
					conn.close();
					return "success";
				}
				else{
					//System.out.println("3");
					return "error";
				}
			}
			else{
				//System.out.println("4");
				return "error";
			}
		} catch (Exception e) {
			return "error";
		}
	}
}

class detail{
	public String isbn;
	public String title;
	public int authorid;
	public String publisher;
	public String publishdate;
	public int price;
	public String name;
	public int age;
	public String country;
	
	public detail(String isbn,String title,int authorid,String publisher,String publishdate,int price,String name,int age,String country){
		this.isbn = isbn;
		this.title = title;
		this.authorid = authorid;
		this.publisher = publisher;
		this.publishdate = publishdate;
		this.price = price;
		this.name = name;
		this.age = age;
		this.country = country;
	};
	
	public String getcountry(){
		return country;
	}
	public void setcountry(String country){
		this.country = country;
	}
	public int getage(){
		return age;
	}
	public void setage(int age){
		this.age = age;
	}
	public int getprice(){
		return price;
	}
	public void setprice(int price){
		this.price = price;
	}
	public String getpublishdate(){
		return publishdate;
	}
	public void setpublishdate(String publishdate){
		this.publishdate = publishdate;
	}
	public String getpublisher(){
		return publisher;
	}
	public void setpublisher(String publisher){
		this.publisher = publisher;
	}
	public int getauthorid(){
		return authorid;
	}
	public void setauthorid(int authorid){
		this.authorid = authorid;
	}
	public String getname(){
		return name;
	}
	public void setname(String name){
		this.name = name;
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
