package model;

import java.util.List;

import dao.RegisterUserDao;

public class GetUserListLogic {
	public List<User> execute(){
		RegisterUserDao registerUserDao = new RegisterUserDao();
		List<User> userList = registerUserDao.findAll();
		return userList;
	}

}
