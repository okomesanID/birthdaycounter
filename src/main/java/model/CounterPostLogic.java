//ユーザービューの残り日数を計算するメソッド
package model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CounterPostLogic {
	
	//残りの誕生日を計算するメソッド
	 public long counter(int month, int day) throws ParseException {	 
		 
		 //CalTodayは現在の日付、calTempは日付格納用
		 Calendar calToday = Calendar.getInstance();
		 Calendar calTemp = Calendar.getInstance();
		 int intYear =calToday.get(Calendar.YEAR);
		 
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
		 
		 if(diff == 365)
		 {
			 diff = 0;
		 }
		 
		 return diff+1;
	 }
}
