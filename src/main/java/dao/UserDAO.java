package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserBean;

public class UserDAO {
	
	// データベースに接続
	private final String JDBC_URL =System.getenv("JDBC_URL");
	private final String DB_USER = System.getenv("DB_USER");
	private final String DB_PASS =System.getenv("DB_PASS");

	//新規登録用の処理
	public boolean UserList(UserBean user) {
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(
		          JDBC_URL, DB_USER, DB_PASS)) {
			
			//INSERT文の準備
			String sql = "INSERT INTO USERLIST(NAME, PASS, BIRTHYEAR, BIRTHMONTH, BIRTHDAY, AGE) "
					+ "VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPass());
			pStmt.setInt(3, user.getYear());
			pStmt.setInt(4, user.getMonth());
			pStmt.setInt(5, user.getDay());
			pStmt.setInt(6, user.getAge());
//			pStmt.setInt(7, user.getResidue());
			
			// INSERT文を実行(execute)
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
			}
			return true;
	}
	
	//ログイン用の処理
	public UserBean LoginLogic(String username){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(
		          JDBC_URL, DB_USER, DB_PASS)) {
			
			 //SELECT文を準備
			 String sql = "SELECT *"
					 + "FROM USERLIST WHERE NAME = ?";
			 PreparedStatement pStmt = conn.prepareStatement(sql);
			 pStmt.setString(1, username);
			 
			 //SELCT文を実行し結果表を取得
			 ResultSet rs = pStmt.executeQuery();
			 rs.next();
			 
			 int id =rs.getInt("ID");
			 String name =rs.getString("NAME");
			 String pass =rs.getString("PASS");
			 int year =rs.getInt("BIRTHYEAR");
			 int month =rs.getInt("BIRTHMONTH");
			 int day =rs.getInt("BIRTHDAY");
			 int age =rs.getInt("AGE");
			 
			 UserBean loginuser = new UserBean(id,name,pass,year,month,day,age);
			 
			 return loginuser;
			 
		}catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		}
	}
	
	//検索用の処理
	List<UserBean> userList = new ArrayList<UserBean>();
	public List<UserBean> findAll() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(
		          JDBC_URL, DB_USER, DB_PASS)) {
			
			//SELECT文を準備
			String sql = "SELECT *"
					+ "FROM USERLIST ORDER BY ID";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			
			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				UserBean user = new UserBean(id, userName);
				userList.add(user);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
		return userList;
	}
}