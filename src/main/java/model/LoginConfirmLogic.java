//ログインチェックに関するモデル
package model;

public class LoginConfirmLogic {
  public boolean execute(UserBean user,String pass) {
	  if(user !=null && user.getPass().equals(pass)) {
		  return true;
	  }
	  return false;
  }
}