//スレッドを削除するためのモデル
package model;

import java.text.ParseException;
import java.util.List;

import dao.ThreadDAO;

public class ThreadDeleteOrder{
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

}