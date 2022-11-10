//つぶやきの投稿に関する処理を行うモデル
package model;

import java.util.List;

public class PostThreadLogic{
	public void execute(ThreadBean Thread, List<ThreadBean> ThreadList) {
		ThreadList.add(0,Thread);
	}
}