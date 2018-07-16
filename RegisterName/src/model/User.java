package model;
import java.io.Serializable;

public class User implements Serializable{
	private String id;    //id
	private String name; //名前
	private String pass; //パスワード

	public User() {}
	public User( String id, String name, String pass) {
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

	public String getId() {return id;}
	public String getName() {return name;}
	public String getPass() {return pass;}
}


