//ユーザー名被りを確認するモデル
package model;

import java.util.List;

import dao.CheckUserDAO;

public class CheckUser{
	
	public boolean check(String user) {
		  
		  //userlistテーブルの全レコードを取得
		  CheckUserDAO User = new CheckUserDAO();
		  List<UserBean> userList = User.findAll();
		  
		  //ユーザー名被りがないかチェック
		  for (UserBean emp : userList) {
		      System.out.println("名前:" + emp.getName());
			  if(emp.getName().trim().equals(user)) {
				  return true;
			  }
		  }
		  return false;
	  }
}