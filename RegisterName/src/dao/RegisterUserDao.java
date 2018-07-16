package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class RegisterUserDao {
	private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/user?characterEncoding=UTF-8&serverTimezone=JST&autoReconnect=true&useSSL=false";
	private final String DB_USER = "root";
	private final String DB_PASS = "KeiMiyag3854";

	public List<User> findAll(){
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//SELECT文の準備
			String sql =
				"SELECT login_id, password, name FROM name_list";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			//SELECT文の結果をArrayListに格納
			while(rs.next()) {
				String id = rs.getString("login_id");
				String pass = rs.getString("password");
				String name = rs.getString("name");
				User user = new User(id,name,pass);
				userList.add(user);
			}

		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}finally {
			//データベース切断
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}

		return userList;

	}

	public boolean create(User user) {
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//INSERT文の準備
			String sql = "INSERT INTO name_list(login_id,password,name) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, user.getId());
			pStmt.setString(2, user.getPass());
			pStmt.setString(3, user.getName());

			//INSERT文を実行
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		finally {
			//データベース切断
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;

	}

	public boolean update(User user) {
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//UPDATE文の準備
			String sql = "UPDATE name_list SET name = ? where login_id = ? AND password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//UPDATE文の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getId());
			pStmt.setString(3, user.getPass());


			//UPDATE文を実行
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		finally {
			//データベース切断
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;

	}


	public boolean create_or_update(User user) {
		int count = 0;
		boolean judge = true;
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//SELECT文の準備
			String sql = "SELECT COUNT(*) FROM  name_list WHERE login_id = ? AND password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT文の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, user.getId());
			pStmt.setString(2, user.getPass());

			//SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			//SELECT文の結果をcountに格納
			while(rs.next()) {
				count = Integer.parseInt(rs.getString("COUNT(*)"));
			}

			//count == 0ならばINSERT文、count != 0ならばUPDATE文を実行
			if(count != 0) {
				judge = false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			//データベース切断
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return judge;
	}

}
