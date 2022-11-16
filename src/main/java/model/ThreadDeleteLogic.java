//スレッドのカウンターに関する処理
package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import dao.ThreadDAO;
import dao.UserDAO;

public class ThreadDeleteLogic {
	
	//スレッドの経過日数を計算するメソッド
	public List<Long>  diff() {
		
		List<Long> dayList = new ArrayList<Long>();
		LocalDate now = LocalDate.now();
		
		ThreadDAO dao = new ThreadDAO();
		List<ThreadBean> threadList =dao.findAll();
		
		//1スレッドずつ経過日数を計算
		for (ThreadBean emp : threadList) {
			LocalDate postday =emp.getPostDate();
			long diff = ChronoUnit.DAYS.between(postday, now);
			dayList.add(diff);
		 }
		return dayList;
	}
	
	//ユーザーの初期日数を取得するメソッド
	public List<Long>  residueDay() {
		
		List<Long> residueList = new ArrayList<Long>();
		UserDAO dao = new UserDAO();
		List<UserBean> UserList =dao.findAll();
		//1スレッドずつ経過日数を計算
		for (UserBean emp : UserList) {
			int residueday =emp.getResidue();
			residueList.add((long)residueday);
		  }
		return residueList;
	}
	
	//誕生日が経過したか調べるメソッド
	public List<Integer> delete() {
			
		List<Long> diff = diff();
		List<Long> residueDay = residueDay();
		
		List<Integer> List = new ArrayList<Integer>();	
		List<Integer> Id = new ArrayList<Integer>();	
		
		ThreadDAO dao = new ThreadDAO();
		List<ThreadBean> threadList =dao.findAll();
		
		//スレッドIDの取得
		for (ThreadBean emp : threadList) {
			Id.add(emp.getId());
		 }
		for (int i = 0; i < diff.size()-1; i++) {
			if(residueDay.get(i) < diff.get(i)) {
				//経過していたら値を格納
				List.add(Id.get(i)); //iをスレッドIDにする
			}
		 }
			return List;
	  }
}