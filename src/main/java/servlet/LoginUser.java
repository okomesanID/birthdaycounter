package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.LoginConfirmLogic;
import model.UserBean;

@WebServlet("/Login")
public class LoginUser extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	 protected void doGet(HttpServletRequest request,
			 HttpServletResponse response)
			 throws ServletException, IOException {
		
			// フォワード先
			String forwardPath = null;
			String action = request.getParameter("action");
			
			if (action == null) {
			forwardPath = "/WEB-INF/jsp/loginForm.jsp";
			}
			
			//ログインしているかの確認
			HttpSession session = request.getSession();
			UserBean loginUser = (UserBean) session.getAttribute("loginUser");
			if(loginUser != null) {
				// 不要となったセッションスコープ内のインスタンスを削除
				session.removeAttribute("recname");
				session.removeAttribute("joinRecname");
				forwardPath = "/birthdaycounter/Home";
			}
			
			// 設定されたフォワード先にフォワード
			RequestDispatcher dispatcher =request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
    }
	
	 protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
			  //リクエストパラメーターの取得
			  request.setCharacterEncoding("UTF-8");
			  String name = request.getParameter("name"); 
			  String pass = request.getParameter("pass");
			  
			  //入力の確認
			  if( name == null ||  name.length()== 0) {	
				  request.setAttribute("NameerrorMsg", "名前が入力されていません");
			  }
			  if( pass == null ||  pass.length()== 0) {	
				  request.setAttribute("PasserrorMsg", "パスワードが入力されていません");
			  }
			  
			  //データベースに値を参照
			  UserDAO dao =new UserDAO();
			  UserBean user = dao.LoginLogic(name);
			  
			  //ログイン処理
			  LoginConfirmLogic ConfirmLogic = new LoginConfirmLogic(); 
			  boolean isLogin = ConfirmLogic.execute(user,pass);
			  
			  //ホーム画面にリダイレクト・ログイン画面にフォワード
			  if(isLogin) {
				 //ユーザー情報をセッションスコープに保存
				 HttpSession session =request.getSession();
				  session.setAttribute("loginUser",user);
				  response.sendRedirect("/birthdaycounter/Home");
			  }
			  else {
				  HttpSession session =request.getSession();
				  session.setAttribute("recname",name);
				  
				 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp");
					 dispatcher.forward(request, response);
			  }
	 }
 }