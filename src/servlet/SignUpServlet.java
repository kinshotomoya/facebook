package servlet;

import DAO.AccountDAO;
import bean.AccountBean;
import model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/sign_up")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Account account = new Account();
        String hashedPassword = account.doHashPassword(password);

        // passwordをハッシュ化する
        AccountBean bean = new AccountBean(userName, hashedPassword);

        // session管理する
        addSession(bean, request);

        // AccountDBにデータを格納する
        AccountDAO dao = new AccountDAO();
        dao.createAccount(bean);

        // viewに処理を飛ばす
        Common com = new Common();
        com.goToJsp(request, response, "top.jsp");
    }

    private void addSession(AccountBean bean, HttpServletRequest request) {
        HttpSession session = request.getSession();
        // すでにあるセッション（accountInfo）を取得
        AccountBean sessionAccount = (AccountBean)session.getAttribute("accountInfo");
        if(sessionAccount == null) {
            // セッションがない場合
            session.setAttribute("accountInfo", bean);
        } else {
            // セッションがある場合
            // すでにあるセッションを削除する
            session.removeAttribute("accountInfo");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
