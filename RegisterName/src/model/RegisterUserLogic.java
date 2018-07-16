package model;

import dao.RegisterUserDao;

public class RegisterUserLogic {
	public boolean execute(User user) {
		//登録処理
		RegisterUserDao registerUserDao = new RegisterUserDao();
		registerUserDao.create(user);
		return true;
	}
}
