package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ThreadBean;

public class ThreadDAO {
	
	// データベースに接続
	private final String JDBC_URL =
	"jdbc:mysql://localhost/birthdaycounter?useSSL=false";
	private final String DB_USER = "root";
	private final String DB_PASS = "@municom";
	
	//スレッドリスト読込用の処理
	List<ThreadBean> threadList = new ArrayList<ThreadBean>();
	public List<ThreadBean> findAll() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(
		          JDBC_URL, DB_USER, DB_PASS)) {
			
			//SELECT文を準備
			String sql = "SELECT *"
					+ "FROM USERLIST "
					+ "JOIN THREADLIST "
					+ "ON USERLIST.ID = THREADLIST.USER_ID";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			
			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("THREAD_ID");
				String name = rs.getString("NAME");
				int year = rs.getInt("BIRTHYEAR");
				int month = rs.getInt("BIRTHMONTH");
				int day = rs.getInt("BIRTHDAY");
				int age = rs.getInt("AGE");
				String text = rs.getString("TEXT");
				
				ThreadBean user = new ThreadBean(id, name, year,month,day,age,text);
				threadList.add(user);
				
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
		return threadList;
	 }
	
	//スレッド投稿用の処理
	public boolean create(ThreadBean thread, int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(
		          JDBC_URL, DB_USER, DB_PASS)) {
			
			//INSERT文の準備
			String sql = "INSERT INTO THREADLIST(THREAD_ID,USER_ID,TEXT)"
					+ "VALUES(?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setInt(2, id);
			pStmt.setString(3, thread.getText());
			
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
