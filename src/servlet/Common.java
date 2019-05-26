package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Common {
    public void goToJsp(HttpServletRequest request, HttpServletResponse response, String page) {
        try {
            RequestDispatcher rs = request.getRequestDispatcher(page);
            rs.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
