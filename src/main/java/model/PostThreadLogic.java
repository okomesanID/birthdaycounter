//スレッドの投稿に関する処理を行うモデル
package model;

import java.text.ParseException;

import dao.ThreadDAO;

public class PostThreadLogic{
	public void execute(ThreadBean thread, int id) {
		ThreadDAO dao = new ThreadDAO();
		try {
			dao.create(thread, id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}