package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CounterBirth;
import model.PostThreadLogic;
import model.ThreadBean;
import model.UserBean;

@SuppressWarnings("unchecked")
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
		 }
		 
		 //スレッドリストの作成
		 ServletContext application =this.getServletContext();
		 List<ThreadBean> ThreadList =(List<ThreadBean>)application.getAttribute("ThreadList");
		 if(ThreadList == null) {
			 ThreadList = new ArrayList<>();
			 application.setAttribute("ThreadList",ThreadList);
		 }
		 
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
				//スレッドリストをアプリケーションスコープより取得
				ServletContext application =this.getServletContext();
				List<ThreadBean> ThreadList =(List<ThreadBean>)application.getAttribute("ThreadList");
				
				//セッションスコープからユーザー情報を取得
				HttpSession session =request.getSession();
				UserBean loginUser = (UserBean) session.getAttribute("loginUser");
				
				//スレッドをリストに追加
				ThreadBean Thread = new ThreadBean(loginUser.getName(),loginUser.getAge(),text); 
				PostThreadLogic postMutterLogic = new PostThreadLogic(); 
				postMutterLogic.execute(Thread,ThreadList); 
				application.setAttribute("ThreadList",ThreadList);
				
				//誕生日の残り日数を取得
				CounterBirth birth = new CounterBirth();
				try {
					long count = birth.counter(loginUser.getMonth(),loginUser.getDay());
					application.setAttribute("counterbirth",count);
				} catch (ParseException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			else {
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "入力されていません");
			}
			
			 //ホーム画面にフォワード
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
			 dispatcher.forward(request,response);
	 }
}