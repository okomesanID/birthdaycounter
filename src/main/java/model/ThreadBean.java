
//ユーザーに関する情報を持つJavaBeans
package model;
import java.io.Serializable;

public class ThreadBean implements Serializable {
	private int id;
	private String name; 
	private int year;
	private int month;
	private int day;
	private int age; 
	private String text;
	
	//スレッド投稿処理用
	public ThreadBean(String name,int age, String text) { 
		this.name = name; 
		this.age = age; 
		this.text=text;
	}
	
	//DAO用
	public ThreadBean(int id, String name,int year, int month, int day, int age, String text) { 
		this.id = id;
		this.name = name; 
		this.year = year;
		this.month=month;
		this.day = day;
		this.age = age; 
		this.text=text;
		
	}
	public int getId() {return id;} 
	public String getName() {return name;} 
	public int getYear() {return year;} 
	public int getMonth() {return month;}
	public int getDay() {return day;}
	public int getAge() {return age;} 
	public String getText() {return text;} 
}