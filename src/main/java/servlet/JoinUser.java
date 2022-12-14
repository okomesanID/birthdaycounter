package servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CalcAge;
import model.CheckUser;
import model.CounterPostLogic;
import model.JoinConfirmLogic;
import model.JoinUserLogic;
import model.UserBean;

@WebServlet("/Join")
public class JoinUser extends HttpServlet {
  private static final long serialVersionUID = 1L;

	 protected void doGet(HttpServletRequest request,
		      HttpServletResponse response)
		      throws ServletException, IOException {
		 
		  String forwardPath = null;
		  String action = request.getParameter("action");

		  // フォワード先の設定
		  if (action == null) {
		  	forwardPath = "/WEB-INF/jsp/joinForm.jsp";
			 // 不要となったセッションスコープ内のインスタンスを削除
		  	HttpSession session = request.getSession();
			 session.removeAttribute("recname");
		  }
		  else if (action.equals("done")) {
			  // セッションスコープに保存された登録ユーザを呼び出し
			  HttpSession session = request.getSession();
			  UserBean registerUser = (UserBean) session.getAttribute("joinUser");
			  
			  //登録完了画面でロードされた場合の処理
			  if(registerUser == null){
				  forwardPath = "/WEB-INF/jsp/joinForm.jsp";
			  }
			  else {
				  // 登録処理の呼び出し
				  UserBean user = new UserBean(registerUser.getName(),registerUser.getPass(),
						  registerUser.getYear(),registerUser.getMonth(),registerUser.getDay(),registerUser.getAge());
				  JoinUserLogic logic = new JoinUserLogic();
				  logic.execute(user);
			
				  // 不要となったセッションスコープ内のインスタンスを削除
				  session.removeAttribute("joinUser");
				  session.removeAttribute("joinRecname");
			
				  // 登録後のフォワード先を設定
				  forwardPath = "/WEB-INF/jsp/joinDone.jsp";
			  }
		  }	  
		  
		  // 設定されたフォワード先にフォワード
		  RequestDispatcher dispatcher =
		      request.getRequestDispatcher(forwardPath);
		  dispatcher.forward(request, response);
	}
	
	 protected void doPost(HttpServletRequest request,
		      HttpServletResponse response)
		      throws ServletException, IOException {
		 
		  //リクエストパラメーターの取得
		  request.setCharacterEncoding("UTF-8");
		  String name = request.getParameter("name"); 
		  String pass = request.getParameter("pass");
		  String checkpass = request.getParameter("checkpass");
		  String year = request.getParameter("year");
		  String month =request.getParameter("month");
		  String day = request.getParameter("day");
		  
		  //入力の確認
		  if( name == null ||  name.length()== 0) {	
			  request.setAttribute("NameerrorMsg", "名前が入力されていません");
		  }
		  if( pass == null ||  pass.length()== 0) {	
			  request.setAttribute("PasserrorMsg", "パスワードが入力されていません");
		  }
		  if(checkpass == null ||  checkpass.length()== 0) {	
			  request.setAttribute("CasserrorMsg", "同じパスワードを入力してください");
		  }
		  if( !(pass.equals(checkpass))) {	
			  request.setAttribute("SasserrorMsg", "パスワードが違います");
		  }
		  
		  //年齢の計算
		  int age = 0;
		  CalcAge Age =  new CalcAge();
		  try {
			age= Age.calcage(year,month,day);
		  } catch (ParseException e) {
			  e.printStackTrace();
		  }	
		  
		  //ユーザー名被りがないかチェック
		  CheckUser checkuser =new CheckUser();
		  boolean check = checkuser.check(name);
		  if(check) {	
			  request.setAttribute("usercheck", "そのユーザー名は使われています。");
			  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/joinForm.jsp"); 
			  dispatcher.forward(request, response);
		  }
		  else {
			  
			  //不正な年齢が入力されたときの処理
			  if(age < 0) {
				  request.setAttribute("usercheck2", "まだ産まれていません");
				  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/joinForm.jsp"); 
				  dispatcher.forward(request, response);
			  }
			  else {
				  // 登録するユーザーの情報を設定
				  UserBean user =new UserBean(name,pass,checkpass,year,month,day,age);
				  
				  //ユーザー登録確認処理
				  JoinConfirmLogic ConfirmLogic = new JoinConfirmLogic(); 
				  boolean isLogin = ConfirmLogic.execute(user);
				
				  //入力成功時の処理
				  if(isLogin) {
					  
					//ユーザー情報をセッションスコープに保存
					HttpSession session =request.getSession();
					session.setAttribute("joinUser",user);
					
					//再投稿可能までの日数をチェック
					CounterPostLogic thread = new CounterPostLogic();
					 try {
						 long count = thread.counter(user.getMonth(),user.getDay());
						 session.setAttribute("CounterThread",count);
					 } catch (ParseException e) {
						e.printStackTrace();
					 }
					 
					//ユーザー登録確認画面にフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/joinConfirm.jsp"); 
					dispatcher.forward(request, response); 
			  }
			  else {
				  HttpSession session =request.getSession();
				  session.setAttribute("joinRecname",name);
				  session.setAttribute("RecYear",year);
				  session.setAttribute("RecMonth",month);
				  session.setAttribute("RecDay",day);
				  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/joinForm.jsp"); 
				  dispatcher.forward(request, response);
				  
			  }
			  }
		 }
	 }
}