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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String params = request.getParameter("action");
        Common com = new Common();
        if(params != "button") {
            // ログインボタンを押さずにこのservletに来た時
            // sessionにaccount情報があるのか確認
            HttpSession session = request.getSession();
            AccountBean sessionAccountBean = (AccountBean)session.getAttribute("accountInfo");
            if (sessionAccountBean != null) {
                // sessionにアカウント情報がある場合
                // sessionに格納していたbeanをrequestに格納してtop.jspに処理を飛ばす
                request.setAttribute("accountInfo", sessionAccountBean);
                // top.jspに飛ばす
                com.goToJsp(request, response, "top.jsp");
            } else {
                // sessionにない時（セッション切れ or そもそもsign_upしていない）
                // ログインページに飛ばす。
                request.setAttribute("message", "ログイン or サインアップしてください");
                com.goToJsp(request, response, "login.jsp");
            }
        } else {
            // input hiddenでaction=buttonが送られてくる時
            // つまり、ログインボタンを押した時
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            Account account = new Account();
            String hashedPassword = account.doHashPassword(password);
            AccountDAO dao = new AccountDAO();
            AccountBean beanWithId = dao.findAccountByNameAndPassword(userName, hashedPassword);
            if(beanWithId != null) {
                // DBに存在するなら
                request.setAttribute("message", "ログイン成功");
                com.goToJsp(request, response, "top.jsp");
            } else {
                // DBに存在しないとき
                // sign_upさせる
                request.setAttribute("message", "ログイン成功");
                com.goToJsp(request, response, "sign_up.jsp");
            }
        }
    }
}
