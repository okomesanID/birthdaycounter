//スレッドの取得に関するモデル
package model;

import java.util.List;

import dao.ThreadDAO;

public class GetThreadListLogic {
	public List<ThreadBean> execute(){
		ThreadDAO dao = new ThreadDAO();
		List<ThreadBean> threadList =dao.findAll();
		return threadList;
	}
}