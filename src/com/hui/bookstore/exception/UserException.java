package com.hui.bookstore.exception;

//Exception是编译异常，Runtimeexception时运行异常
public class UserException extends Exception {

	public UserException(String message) {//重写父类异常
		super(message);
		// TODO Auto-generated constructor stub
	}

}
