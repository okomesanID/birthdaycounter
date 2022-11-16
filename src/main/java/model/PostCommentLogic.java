//スレッドの投稿に関する処理を行うモデル
package model;

import java.text.ParseException;

import dao.CommentDAO;

public class PostCommentLogic{
	public void execute(CommentBean comminfo, String comment) {
		
		CommentDAO dao = new CommentDAO();
		try {
			dao.create(comminfo, comment);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}