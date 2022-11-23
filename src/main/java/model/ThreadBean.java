
//ユーザーに関する情報を持つJavaBeans
package model;
import java.io.Serializable;
import java.time.LocalDate;

public class ThreadBean implements Serializable {
	private int id;
	private int thread_id;
	private String name; 
	private int year;
	private int month;
	private int day;
	private int age; 
	private String text;
	private LocalDate PostDate; 
	private int residue;
	
	//スレッドリスト取得用
	public ThreadBean(String name,int age, String text,LocalDate PostDate) { 
		this.name = name; 
		this.age = age; 
		this.text=text;
		this. PostDate= PostDate;
	}
	
	//スレッド投稿処理(Home.java)
	public ThreadBean(String name,int age, String text, int residue) { 
		this.name = name; 
		this.age = age; 
		this.text=text;
		this.residue = residue;
	}
	
	//DAO用
	public ThreadBean(int id, int thread_id, String name,int year, int month, int day, int age, String text, LocalDate PostDate, int residue) { 
		this.id = id;
		this.thread_id = thread_id;
		this.name = name; 
		this.year = year;
		this.month=month;
		this.day = day;
		this.age = age; 
		this.text=text;
		this. PostDate= PostDate;
		this.residue = residue;
	}
	
	public int getId() {return id;} 
	public int getThread_Id() {return thread_id;} 
	public String getName() {return name;} 
	public int getYear() {return year;} 
	public int getMonth() {return month;}
	public int getDay() {return day;}
	public int getAge() {return age;} 
	public String getText() {return text;} 
	public  LocalDate getPostDate() {return PostDate;} 
	public int getResidue() {return residue;} 
}