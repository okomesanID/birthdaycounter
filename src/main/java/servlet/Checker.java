package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CheckerLogic;

@WebServlet("/Checker")

public class Checker extends HttpServlet {
  private static final long serialVersionUID = 1L;
    
	 protected void doGet(HttpServletRequest request,
		      HttpServletResponse response)
		      throws ServletException, IOException {
		 
		 //チェッカー画面にフォワード
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/checker.jsp");
		 dispatcher.forward(request,response);
	 }
	 
	 protected void doPost(HttpServletRequest request,
		      HttpServletResponse response)
		      throws ServletException, IOException {
		 
		  //リクエストパラメーターの取得
		  request.setCharacterEncoding("UTF-8");
		  
		  String year = request.getParameter("year");
		  String month =request.getParameter("month");
		  String day = request.getParameter("day");
		  String foryear = request.getParameter("foryear");
		  String formonth =request.getParameter("formonth");
		  String forday = request.getParameter("forday");
		  
		  CheckerLogic Checker =  new CheckerLogic(year, month, day, foryear, formonth, forday);
		  
		  //日付の妥当性チェック
		  boolean date1= Checker.checkDate1();
		  boolean date2= Checker.checkDate2();
		  
		  if(date1==true&&date2==true) {
			  //計算ロジックの呼び出し
			  int age= Checker.calcage();
			  int age2= Checker.calcage2();
			  String eto  =Checker.Eto();
			  String seiza  =Checker.Seiza();
			  long progress =Checker.counter();
			  long days =Checker.counter2();
			  
			  //不正な年齢が入力されたときの処理
			  if(age < 0) {
				  request.setAttribute("usercheck2", "まだ産まれていません");
				  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/checker.jsp"); 
				  dispatcher.forward(request, response);
			  }  
			  else {
				  //ユーザー情報をセッションスコープに保存
				  HttpSession session =request.getSession();
				  session.setAttribute("Checkage",age);
				  session.setAttribute("Checkage2",age2);
				  session.setAttribute("eto",eto);
				  session.setAttribute("seiza",seiza);
				  session.setAttribute("progress",progress);
				  session.setAttribute("days",days);
				  
				  //入力フォーム用
				  session.setAttribute("year",year);
				  session.setAttribute("month",month);
				  session.setAttribute("day",day);
				  session.setAttribute("foryear",foryear);
				  session.setAttribute("formonth",formonth);
				  session.setAttribute("forday",forday);
				  
				  //チェッカー画面にフォワード
				  response.sendRedirect("/birthdaycounter/Checker");
			  }
		  }
		  else {
			  request.setAttribute("checkDate", "不正な日付が入力されています");
			  //チェッカー画面にフォワード
			  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/checker.jsp"); 
			  dispatcher.forward(request, response);
		  }
	 }
}