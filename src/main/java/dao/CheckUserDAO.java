package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserBean;

public class CheckUserDAO {
	
	// データベースに接続
	private final String JDBC_URL =
	"jdbc:mysql://localhost/birthdaycounter?useSSL=false";
	private final String DB_USER = "root";
	private final String DB_PASS = "@municom";
	
	List<UserBean> userList = new ArrayList<UserBean>();
	
	//アカウント検索用の処理
	public List<UserBean> findAll() {
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
					+ "FROM USERLIST ORDER BY ID DESC";
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
