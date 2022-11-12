//スレッドの投稿に関する処理を行うモデル
package model;

import dao.ThreadDAO;

public class PostThreadLogic{
	public void execute(ThreadBean thread, int id) {
		ThreadDAO dao = new ThreadDAO();
		dao.create(thread, id);
	}
}