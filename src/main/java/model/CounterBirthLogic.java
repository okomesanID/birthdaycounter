//残りの誕生日を計算する
package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.ThreadDAO;

public class CounterBirthLogic {
	public List<Long> counter(){
		
		//日数格納用のリスト
		List<Long> birthList = new ArrayList<Long>();
		
		//スレッドのリストを取得
		ThreadDAO dao = new ThreadDAO();
		List<ThreadBean> threadList =dao.findAll();
		
		//CalTodayは現在の日付、calTempは日付格納用
		Calendar calToday = Calendar.getInstance();
		Calendar calTemp = Calendar.getInstance();
		int intYear =calToday.get(Calendar.YEAR);
		
		//1スレッドずつ誕生日までの日数を計算
		for (ThreadBean emp : threadList) {
			int month = emp.getMonth();	
			int day =emp.getDay();
			
			 //現在の年度の誕生日を生成
			 calTemp.set(intYear,month-1,day);
			 
			 //誕生日までの残り日数を計算
			 Date dteToday=calToday.getTime();
			 Date dteTemp=calTemp.getTime();
			 if(!dteTemp.after(dteToday)){
			     calTemp.set(Calendar.YEAR,intYear+1);
			     dteTemp=calTemp.getTime();
			 }
			 long diff=(dteTemp.getTime() -dteToday.getTime())/(24*60*60*1000);
			 birthList.add(diff);
		 }
		
		return birthList;
	}
}
