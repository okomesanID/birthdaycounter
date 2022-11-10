//ログインに関するリクエストを処理するコントローラ
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//アノテーション（URLパターンの設定）
@WebServlet("/Logout")

public class LogoutUser extends HttpServlet { //HttpServletを継承してクラスを宣言
private static final long serialVersionUID = 1L;//シリアライズする際のバージョン管理

protected void doGet(HttpServletRequest request, //サーブレットクラスのメインメソッド（リクエストはGet）
  HttpServletResponse response) //（HttpServletをオーバーライド）
  throws ServletException, IOException { //ここまでメソッド宣言（例外発生時にはthrows）
	
	//セッションスコープを破棄
	HttpSession session = request.getSession();
	session.invalidate();
	
	  response.sendRedirect("/birthdaycounter/Home");
  }
}