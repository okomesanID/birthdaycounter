//年齢を計算するモデル
package model;

import java.text.ParseException;
import java.util.Calendar;

public class CalcAge {
	 public int birthday(String Year,String Month, String Day) throws ParseException {	 
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
}