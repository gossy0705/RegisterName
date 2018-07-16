package model;

import dao.RegisterUserDao;

public class UserJudgeLogic {
	public boolean execute(User user) {
		RegisterUserDao registerUserDao = new RegisterUserDao();
		return registerUserDao.create_or_update(user);
	}


}
