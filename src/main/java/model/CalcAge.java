//年齢を計算するモデル
package model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CalcAge {
	
	 //年齢を計算するメソッド
	 public int calcage(String Year,String Month, String Day) throws ParseException {	 
		 int year = Integer.parseInt(Year);
		 int month = Integer.parseInt(Month);
		 int day = Integer.parseInt(Day);
		 
		 //CalTodayは現在の日付、calTempは日付格納用
		 Calendar calToday = Calendar.getInstance();
		 Calendar calTemp = Calendar.getInstance();
		 int intYear =calToday.get(Calendar.YEAR);
		 
		 //現在の年度の誕生日を生成
		 calTemp.set(intYear,month-1,day);
		 
		 //基準年齢を計算
		 int age =intYear-year;	 
		 
		 //年齢の確認計算
		 if(calToday.get(Calendar.MONTH)+1 < month) {
			 age -=1;
		 }
		 else if(calToday.get(Calendar.MONTH)+1 == month) {
			 if(calToday.get(Calendar.DATE) < day) {
				 age -=1;
			 }
		 }
			return age;
	 }
	 
	//初期日数を計算するメソッド
	public long counter(String Month, String Day) throws ParseException {
		int month = Integer.parseInt(Month);
		int day = Integer.parseInt(Day);
		
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

		return diff;
		}
}