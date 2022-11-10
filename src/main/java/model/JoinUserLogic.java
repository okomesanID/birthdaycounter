//ユーザ登録に関する処理を行うモデル
package model;

import dao.UserDAO;

public class JoinUserLogic{
	public void execute(UserBean user) {//executeメソッド追加(プロトタイプ)
		UserDAO dao = new UserDAO(); 
		dao.UserList(user);
	}
}