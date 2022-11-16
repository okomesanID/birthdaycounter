//ユーザーに関する情報を持つJavaBeans
package model;
import java.io.Serializable;
import java.time.LocalDate;

public class CommentBean implements Serializable {
	private int comment_id;
	private int thread_id;
	private int user_id;
	private String name;
	private int age;
	private String comment;
	private LocalDate PostDate; 
	
	//スレッド投稿処理用
	public CommentBean(int thread_id,int user_id, String comment) { 
		this.thread_id = thread_id;
		this.user_id = user_id; 
		this.comment=comment;
	}
	
	//スレッド読込用
	public CommentBean(int comment_id, String name, int age, String comment,LocalDate PostDate) {
		this.comment_id=comment_id;
		this.name=name;
		this.age=age;
		this.comment=comment;
		this. PostDate= PostDate;
	}
	public int getComment_id() {return comment_id;}
	public int getThread_id() {return thread_id;}
	public int getUser_id() {return user_id;} 
	public String getName() {return name;} 
	public int getAge() {return age;} 
	public String getComment() {return comment;} 
	public  LocalDate getPostDate() {return PostDate;} 
}