//ユーザー名被りを確認するモデル
package model;

import java.util.List;

import dao.UserDAO;

public class CheckUser{
	
	public boolean check(String user) {
		
		 //userlistテーブルの全レコードを取得
		 UserDAO User = new UserDAO();
		 List<UserBean> userList = User.findAll();
		
		 //ユーザー名被りがないかチェック
		 for (UserBean emp : userList) {
			 if(emp.getName().trim().equals(user)) {
			  return true;
			  }
		  }
		  return false;
	  }
}