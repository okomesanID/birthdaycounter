//スレッドを投稿したか判定するモデル
package model;

import java.util.List;

import dao.ThreadDAO;

public class CheckThread{
	
	public boolean check(int id) {
		  
		 //threadテーブルの全レコードを取得
		 ThreadDAO thread= new ThreadDAO();
		 List<ThreadBean> threadList = thread.findAll();
		
		 //ユーザー名被りがないかチェック
		 for (ThreadBean emp : threadList) {
			 if(emp.getThread_Id() == id ) {
			  return true;
			  }
		  }
		
		  return false;
	  }
}