//ユーザー登録に関する処理を行うモデル
package model;

public class JoinConfirmLogic {
	
	//ユーザー登録が正しく行われているか確認
	public boolean execute(UserBean user) {
		if(user.getName()!=("") && user.getPass()!=("") && user.getCheckpass()!=("") && (user.getCheckpass().equals(user.getPass())))
		 { 
		   return true;
		 }
		   return false;
	}

}