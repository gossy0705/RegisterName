package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetUserListLogic;
import model.RegisterUserLogic;
import model.UpdateUserLogic;
import model.User;
import model.UserJudgeLogic;


/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//フォワード先
		String forwardPath = null;

		//サーブレットクラスの動作を決定する「ation」の値を
		//リクエストパラメーターから取得
		String action = request.getParameter("action");

		//「登録の開始」をリクエストされたときの処理
		if(action == null) {


			//フォワード先を設定
			forwardPath = "/WEB-INF/jsp/registerForm.jsp";
		}

		//登録画面から「登録実行」をリクエストされたときの処理
		else if(action.equals("done")) {
			//セッションスコープに保存された登録ユーザーを取得
			HttpSession session = request.getSession();
			User registerUser = (User)session.getAttribute("registerUser");


			//登録処理の呼び出し
			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			registerUserLogic.execute(registerUser);

			//name_listを取得して、リクエストスコープに保存
			GetUserListLogic getUserListLogic = new GetUserListLogic();
			List<User> userList = getUserListLogic.execute();
			request.setAttribute("userList", userList);


			//不要となったセッションスコープ内のインスタンスを削除
			session.removeAttribute("registerUser");

			//登録後のフォワード先を設定
			forwardPath = "/WEB-INF/jsp/registerDone.jsp";
		}else {
			//セッションスコープに保存された登録ユーザーを取得
			HttpSession session = request.getSession();
			User registerUser = (User)session.getAttribute("registerUser");


			//更新処理の呼び出し
			UpdateUserLogic updateUserLogic = new UpdateUserLogic();
			updateUserLogic.execute(registerUser);
			//name_listを取得して、リクエストスコープに保存
			GetUserListLogic getUserListLogic = new GetUserListLogic();
			List<User> userList = getUserListLogic.execute();
			request.setAttribute("userList", userList);


			//不要となったセッションスコープ内のインスタンスを削除
			session.removeAttribute("registerUser");

			//登録後のフォワード先を設定
			forwardPath = "/WEB-INF/jsp/registerDone.jsp";
		}

		//設定されたフォワード先のフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		//登録するユーザーの情報を設定
		User registerUser = new User(id, name, pass);


		//エラーメッセージをリクエストスコープに保存
		//エラーの状態の入力内容を保持
		if(name == null || name.length() == 0) {
			request.setAttribute("error_registerUser", registerUser);
			request.setAttribute("errorMessage", "名前が入力されていません");
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/registerForm.jsp");
			dispatcher.forward(request, response);
		}

		//セッションスコープに登録ユーザーを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);


		//ユーザーを登録するのか、更新するのかの判断
		UserJudgeLogic userJudgeLogic = new UserJudgeLogic();


		if(userJudgeLogic.execute(registerUser)) {
			//登録処理確認画面へフォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/registerConfirm.jsp");
			dispatcher.forward(request, response);
		}else {
			//更新処理確認画面へフォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/updateConfirm.jsp");
			dispatcher.forward(request, response);
		}
	}


}
