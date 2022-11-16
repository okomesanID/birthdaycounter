package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CommentBean;
import model.GetCommentLogic;
import model.GetThreadListLogic;
import model.PostCommentLogic;
import model.ThreadBean;
import model.UserBean;

@WebServlet("/Comment")

public class Comment extends HttpServlet {
  private static final long serialVersionUID = 1L;
    
	 //スレッドタイルとコメントの取得
	 protected void doGet(HttpServletRequest request,
		      HttpServletResponse response)
		      throws ServletException, IOException {	 
		 
		 //スレッドのIDを格納
		 String threadNo = request.getParameter("ThreadNo");
		 int ThreadNo = Integer.parseInt(threadNo);
		 HttpSession session = request.getSession();
		 session.setAttribute("ThreadID",ThreadNo);
		 
		 //スレッドリストを取得してリクエストスコープに保存
		 GetThreadListLogic getListLogic = new  GetThreadListLogic();
		 List<ThreadBean> ThreadList = getListLogic.execute();
		 request.setAttribute("ThreadList", ThreadList);
		 
		 //ログインのチェック処理
		 UserBean loginUser = (UserBean) session.getAttribute("loginUser");
		 if(loginUser == null) {    
			 session.setAttribute("logincheck",0);
		 }
		 else if(loginUser != null) {
		   session.setAttribute("logincheck",1);
		 }
		 
		 //コメントリストを取得してリクエストスコープに保存
		 GetCommentLogic getCommentLogic = new  GetCommentLogic();
		 List<CommentBean> CommentList = getCommentLogic.execute(ThreadNo);
		 request.setAttribute("CommentList", CommentList);
		 
		 //コメント画面にフォワード
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/comment.jsp");
		 dispatcher.forward(request,response);
	 }
	 
	//スレッドタイルとコメントの取得
	 protected void doPost(HttpServletRequest request,
		      HttpServletResponse response)
		      throws ServletException, IOException {	 
		 
		 //リクエストパラメーターの取得
		 request.setCharacterEncoding("UTF-8"); 
		 String comment = request.getParameter("comment");
		//スレッドの情報,ユーザー情報を取得
		HttpSession session =request.getSession();
		int ThreadNo =Integer.parseInt( request.getParameter("ThreadNo"));
		UserBean loginUser = (UserBean) session.getAttribute("loginUser");
		
		//入力値チェック
		if(comment != null && comment.length()!=0) {
			
			//スレッドリストを取得してリクエストスコープに保存
			GetThreadListLogic getListLogic = new  GetThreadListLogic();
			List<ThreadBean> ThreadList = getListLogic.execute();
			request.setAttribute("ThreadList", ThreadList);
			session.setAttribute("ThreadID",ThreadNo);
			 
			//スレッドリストをリストに追加
			CommentBean Comeinfo = new CommentBean(ThreadNo,loginUser.getId(),comment);
			PostCommentLogic postCommentLogic = new PostCommentLogic(); 
			postCommentLogic.execute(Comeinfo,comment);
		}
		else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "入力されていません");
		}
		
		 //コメントリストを取得してリクエストスコープに保存
		 GetCommentLogic getCommentLogic = new  GetCommentLogic();
		 List<CommentBean> CommentList = getCommentLogic.execute(ThreadNo);
		 request.setAttribute("CommentList", CommentList);
		 
		 //コメント画面にフォワード
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/comment.jsp");
		 dispatcher.forward(request,response);
	 }
}