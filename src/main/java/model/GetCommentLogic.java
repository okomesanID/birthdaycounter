//コメントの取得に関するモデル
package model;

import java.util.List;

import dao.CommentDAO;

public class GetCommentLogic {
	public List<CommentBean> execute(int id){
		CommentDAO dao = new CommentDAO();
		List<CommentBean> commentList =dao.findAll(id);
		
		return commentList;
	}
}