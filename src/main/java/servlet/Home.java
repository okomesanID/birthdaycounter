package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CheckThread;
import model.CounterBirthLogic;
import model.CounterThreadLogic;
import model.GetThreadListLogic;
import model.PostThreadLogic;
import model.ThreadBean;
import model.UserBean;

@WebServlet("/Home")

public class Home extends HttpServlet {
  private static final long serialVersionUID = 1L;
    
	 protected void doGet(HttpServletRequest request,
		      HttpServletResponse response)
		      throws ServletException, IOException {	 
		 
		 HttpSession session = request.getSession();
		 UserBean loginUser = (UserBean) session.getAttribute("loginUser");
		
		 //ログインのチェック処理
		 if(loginUser == null) {    
			 session.setAttribute("logincheck",0);
		 }
		 else if(loginUser != null) {
		   session.setAttribute("logincheck",1);
		
			//スレッドが投稿されているかのチェック
			CheckThread checthread =new  CheckThread();
			boolean check = checthread.check(loginUser.getId());
			if(check) {
				session.setAttribute("logincheck",3);
				//再投稿可能までの日数をチェック
				CounterThreadLogic thread = new CounterThreadLogic();
				 try {
					long count = thread.counter(loginUser.getMonth(),loginUser.getDay());
					session.setAttribute("CounterThread",count);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		 }
		 
		  //スレッドリストを取得してリクエストスコープに保存
		 GetThreadListLogic getListLogic = new  GetThreadListLogic();
		 List<ThreadBean> ThreadList = getListLogic.execute();
		 request.setAttribute("ThreadList", ThreadList);
		 
		 //誕生日の残り日数を取得
		 CounterBirthLogic birth = new CounterBirthLogic();
		 List<Long> count = birth.counter();
		 
		 session.setAttribute("counterbirth",count);
		 
		 //ホーム画面にフォワード
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		 dispatcher.forward(request,response);
	 }
	 
	 protected void doPost(HttpServletRequest request,
		      HttpServletResponse response)
		      throws ServletException, IOException {
		
			//リクエストパラメーターの取得
			request.setCharacterEncoding("UTF-8"); 
			String text = request.getParameter("text");
			
			//入力値チェック
			if(text != null && text.length()!=0) {
				
				//セッションスコープからユーザー情報を取得
				HttpSession session =request.getSession();
				UserBean loginUser = (UserBean) session.getAttribute("loginUser");
				
				//スレッドリストをリストに追加
				ThreadBean mutter = new ThreadBean(loginUser.getName(),loginUser.getAge(),text);
				PostThreadLogic postThreadLogic = new PostThreadLogic(); 
				postThreadLogic.execute(mutter,loginUser.getId());
				
			}
			else {
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "入力されていません");
			}
			
			//スレッドリストを取得して、リクエストスコープに保存
			 GetThreadListLogic getListLogic = new  GetThreadListLogic();
			 List<ThreadBean> ThreadList = getListLogic.execute();
			 request.setAttribute("ThreadList", ThreadList);			
			
			 HttpSession session = request.getSession();
			 UserBean loginUser = (UserBean) session.getAttribute("loginUser");
			
				//スレッドが投稿されているかのチェック
				CheckThread checthread =new  CheckThread();
				boolean check = checthread.check(loginUser.getId());
				if(check) {
					session.setAttribute("logincheck",3);
					//再投稿可能までの日数をチェック
					CounterThreadLogic thread = new CounterThreadLogic();
					 try {
						long count = thread.counter(loginUser.getMonth(),loginUser.getDay());
						session.setAttribute("CounterThread",count);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
			 //誕生日の残り日数を取得
			 CounterBirthLogic birth = new CounterBirthLogic();
			 List<Long> count = birth.counter();
			 session.setAttribute("counterbirth",count);

			 //ホーム画面にフォワード
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
			 dispatcher.forward(request,response);
	 }
}