package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.CommentBean;
import model.CounterThreadLogic;

public class CommentDAO {
	
//	// データベースに接続
//	private final String JDBC_URL =
//	"jdbc:mysql://localhost/birthdaycounter?useSSL=false";
//	private final String DB_USER = "root";
//	private final String DB_PASS = "@municom";
	
	// データベースに接続
	private final String JDBC_URL =System.getenv("JDBC_URL");
	private final String DB_USER = System.getenv("DB_USER");
	private final String DB_PASS =System.getenv("DB_PASS");
	
	//スレッドリスト読込用の処理
	List<CommentBean> CommentList = new ArrayList<CommentBean>();
	public List<CommentBean> findAll(int thread_id) {
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(
		          JDBC_URL, DB_USER, DB_PASS)) {
			
			//SELECT文を準備
			String sql = "SELECT COMMENT_ID, NAME, AGE, COMMENT, COMMENTLIST.POSTDAY "
					+ "FROM COMMENTLIST "
					+ "LEFT JOIN THREADLIST ON COMMENTLIST.THREAD_ID = THREADLIST.THREAD_ID "
					+ "LEFT JOIN USERLIST ON COMMENTLIST.USER_ID = USERLIST.ID "
					+ "WHERE COMMENTLIST.THREAD_ID = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, thread_id);
			
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			
			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("COMMENT_ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				String comment = rs.getString("COMMENT");
				java.sql.Date date = rs.getDate("POSTDAY");
				
				String strDate = date.toString();
				LocalDate PostDate = LocalDate.parse(strDate);
				
				CommentBean Come = new CommentBean(id, name, age,comment, PostDate);
				CommentList.add(Come);
				
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
		return CommentList;
	 }
	
	//コメント投稿用の処理
	public boolean create(CommentBean comminfo, String comment) throws ParseException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(
		          JDBC_URL, DB_USER, DB_PASS)) {
			
			//現在の日付を取得
			 CounterThreadLogic calendar = new CounterThreadLogic();
			 LocalDate today = calendar.today();
			//型変換
			 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 Date count =simpleDateFormat.parse(today.toString());
			 String str = new SimpleDateFormat("yyyy-MM-dd").format(count);
			 java.sql.Date sqlDate=  java.sql.Date.valueOf(str);
			
			//INSERT文の準備
			String sql = "INSERT INTO COMMENTLIST(THREAD_ID,USER_ID,COMMENT,COMMENTLIST.POSTDAY)"
					+ "VALUES(?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, comminfo.getThread_id());
			pStmt.setInt(2, comminfo.getUser_id());
			pStmt.setString(3, comment);
			pStmt.setDate(4, sqlDate);
			
			//INSERT文の実行
			int result = pStmt.executeUpdate();
			if(result !=1) {
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
			}
		return true;
	}
	
}