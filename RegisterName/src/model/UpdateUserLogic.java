package model;

import dao.RegisterUserDao;

public class UpdateUserLogic {
	public void execute(User user) {
		//登録処理
		RegisterUserDao registerUserDao = new RegisterUserDao();
		registerUserDao.update(user);
	}

}
