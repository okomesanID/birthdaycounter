//スレッドを削除するためのモデル
package model;

import java.text.ParseException;
import java.util.List;

import dao.ThreadDAO;


public class ThreadDeleteOrder{
	
	//日数が経過したときの動作
	public void execute() {
		ThreadDeleteLogic Delete = new ThreadDeleteLogic();
		ThreadDAO dao = new ThreadDAO();
		List<Integer> List = Delete.delete();
		
		for (int i = 0; i < List.size()-1; i++) {
			try {
				dao.delete(List.get(i));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		  }
	}
	
	//削除ボタンを押された時の動作
	public void delete(int No) {
		ThreadDAO dao = new ThreadDAO();
		System.out.println("No="+No);
		try {
			dao.delete2(No);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  
	}

}