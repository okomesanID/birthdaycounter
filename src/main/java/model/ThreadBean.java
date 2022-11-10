
//ユーザーに関する情報を持つJavaBeans
package model;
import java.io.Serializable;

public class ThreadBean implements Serializable {
	private int id;
	private String name; 
	private int age; 
	private String text;
	
	//スレッド投稿処理用
	public ThreadBean(String name,int age, String text) { 
		this.name = name; 
		this.age = age; 
		this.text=text;
	}

	
	public int getId() {return id;} 
	public String getName() {return name;} 
	public int getAge() {return age;} 
	public String getText() {return text;} 
}