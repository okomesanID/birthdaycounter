//ユーザーに関する情報を持つJavaBeans
package model;
import java.io.Serializable;

public class UserBean implements Serializable {
	private int id;
	private String name; 
	private String pass;
	private String checkpass;
	private int year;
	private int month;
	private int day;
	private int age;
	
	//ユーザ登録確認処理に使用
	public UserBean(String name, String pass,String checkpass, String year, String month, String day, int age) { 
		this.name = name; 
		this.pass = pass;
		this.checkpass = checkpass; 
		this.year = Integer.parseInt(year);
		this.month=Integer.parseInt(month);
		this.day = Integer.parseInt(day);
		this.age = age;
	}
	
	//DB内のユーザーチェック用
	public UserBean(int id,String name) { 
		this.id = id; 
		this.name = name; 
	}
	
	//データベース登録用
	public UserBean(String name, String pass, int year, int month, int day, int age) { 
		this.name = name; 
		this.pass = pass; 
		this.age = age; 
		this.year = year;
		this.month=month;
		this.day = day;
	}
	
	//ログイン処理用
	public UserBean(int id,String name, String pass, int year,int month, int day, int age) { 
		this.id = id; 
		this.name = name; 
		this.pass = pass;  
		this.year = year;
		this.month= month;
		this.day = day;
		this.age = age;
	}
	
	public int getId() {return id;} 
	public String getName() {return name;} 
	public String getPass() {return pass;}
	public String getCheckpass() {return checkpass;}
	public int getYear() {return year;} 
	public int getMonth() {return month;}
	public int getDay() {return day;}
	public int getAge() {return age;} 

}