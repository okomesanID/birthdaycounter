//年齢を計算するモデル
package model;

import java.text.DateFormat;
import java.time.MonthDay;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class CheckerLogic {
	private int year;
	private int month;
	private int day;
	private int foryear;
	private int formonth;
	private int forday;
	 
	//日付を取得するメソッド
	 public CheckerLogic(String Year,String Month, String Day,String forYear,String forMonth, String forDay) {	 
		 this.year = Integer.parseInt(Year);
		 this.month = Integer.parseInt(Month);
		 this.day = Integer.parseInt(Day);
		 this.foryear = Integer.parseInt(forYear);
		 this.formonth = Integer.parseInt(forMonth);
		 this.forday = Integer.parseInt(forDay);
	 }
	 
	 //年齢を計算するメソッド
	 public int calcage(){	 
		
		 //基準年齢を計算
		 int age =foryear- year;	 
		 
		 //年齢の確認計算
		 if(formonth < month) {
			 age -=1;
		 }
		 else if(formonth == month) {
			 if(forday < day) {
				 age -=1;
			 }
		 }
			return age;
	 }
	 
	 //数え年を計算するメソッド
	 public int calcage2(){	 
			
		 //基準年齢を計算
		 int age =foryear- year+1;	 

			return age;
	 }
	 
	//干支を計算するメソッド
	public String Eto(){	 
	
		// 配列に干支を代入
		String[] eto = {"子（ね）", "丑（うし）", "寅（とら）", 
				"卯（う）", "辰（たつ）", "巳（み）", "午（うま）", "未（ひつじ）", "申（さる）", 
				"酉（とり）", "戌（いぬ）", "亥（い）"};
		
		int eto_num = year;
		int result = (eto_num + 8) % 12;
		
		return eto[result];
	}
	
	//星座を計算するメソッド
	 public String Seiza(){		 
		
		 MonthDay a= MonthDay.of(month,day);
		
		 TreeMap<MonthDay, String> lastDayOfSeiza = new TreeMap<>(
			 Map.ofEntries(Map.entry(MonthDay.of(1, 19), "やぎ座"), Map.entry(MonthDay.of(2, 18), "みずがめ座"),
			 Map.entry(MonthDay.of(3, 20), "うお座"), Map.entry(MonthDay.of(4, 19), "おうし座"),
			 Map.entry(MonthDay.of(5, 20), "おうし座"), Map.entry(MonthDay.of(6, 21), "ふたご座"),
			 Map.entry(MonthDay.of(7, 22), "かに座"), Map.entry(MonthDay.of(8, 22), "しし座"),
			 Map.entry(MonthDay.of(9, 22), "おとめ座"), Map.entry(MonthDay.of(10, 23), "てんびん座"),
			 Map.entry(MonthDay.of(11, 22), "さそり座"), Map.entry(MonthDay.of(12, 21), "いて座"),
			 Map.entry(MonthDay.of(12, 31), "やぎ座")
			 ));
		
		 return lastDayOfSeiza.ceilingEntry(a).getValue();
	 }
	 
	//生まれてからの経過日数をカウントするメソッド
	public long counter(){
		
		//日付格納用のインスタンスを生成
		Calendar calTemp1 = Calendar.getInstance();
		Calendar calTemp2 = Calendar.getInstance();
		 
		//日付生成
		calTemp1.set(year,month,day);
		calTemp2.set(foryear,formonth,forday);
		
		 //誕生日までの残り日数を計算
		 Date dteTemp1=calTemp1.getTime();//誕生日
		 Date dteTemp2=calTemp2.getTime();//計算日
		 
		 if(!dteTemp2.after(dteTemp1)){
		     calTemp2.set(Calendar.YEAR,foryear+1);
		     dteTemp2=calTemp2.getTime();
		 }
		 long days=(dteTemp2.getTime() -dteTemp1.getTime())/(24*60*60*1000);
		 
		 return days;
	}
	
	//誕生日までの日数を計算するメソッド
	public long counter2(){
		
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
	
	//日付の妥当性を検証するメソッド
	@SuppressWarnings("unused")
	public boolean checkDate1() {
		
		String year = String.valueOf(this.year);
		String month = String.valueOf(this.month);
		String day = String.valueOf(this.day);
		String strDate = year+"-"+month+"-"+day;
		
		if (strDate == null) {
			throw new IllegalArgumentException("引数の文字列["+ strDate +"]" + "は不正です。");
		}
		
		strDate = strDate.replace('-', '/');
		DateFormat format = DateFormat.getDateInstance();
		format.setLenient(false);
		try {
			format.parse(strDate);
			return true;
		} catch (Exception e) {
			return false;
		}
	
	}
	
	//日付の妥当性を検証するメソッド
	@SuppressWarnings("unused")
	public boolean checkDate2() {
		
		String year = String.valueOf(this.foryear);
		String month = String.valueOf(this.formonth);
		String day = String.valueOf(this.forday);
		String strDate = year+"-"+month+"-"+day;
		
		if (strDate == null) {
			throw new IllegalArgumentException("引数の文字列["+ strDate +"]" + "は不正です。");
		}
		
		strDate = strDate.replace('-', '/');
		DateFormat format = DateFormat.getDateInstance();
		format.setLenient(false);
		try {
			format.parse(strDate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}