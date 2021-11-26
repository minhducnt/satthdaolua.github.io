package User;

import Data.accountIO;
import Model.Account;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name = "customer", value = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        String url="/usercenter.jsp";
        String action = request.getParameter("action");

        if (action == null) {
            action = "join";
        }
        if (action.equals("join")) {
            action = "customer";
        }

        else if (action.equals("add")) {

            String username;
            boolean Gender;
            String phone;
            String message;
            Timestamp lastUpdate;
            username =  request.getParameter("fullName");
            String password =  request.getParameter("password");
            phone =request.getParameter("phoneNumber");
            String email =  request.getParameter("email");
            String sex = request.getParameter("gender");

            Gender = sex.equals("male");
            String   day=request.getParameter("day");
            String   month=request.getParameter("month");
            String   year=request.getParameter("year");
            //birhday
            Date birthday1 = null;
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = year+"-"+month+"-"+day;

            try {
                birthday1 = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert birthday1 != null;
            java.sql.Date birthday = new java.sql.Date(birthday1.getTime());
            boolean role = true;
            //last update
            lastUpdate = new Timestamp(System.currentTimeMillis());
            Account  account = (Account) request.getSession().getAttribute("account");
            if ( username == null || username.isEmpty() || phone == null || phone.isEmpty()) {
                message = "Xin hãy điền tất cả các giá trị";
                account.setBirthday(birthday);
                account.setGender(Gender);
                account.setLastUpdate(lastUpdate);
                account.setPhone(phone);
                account.setRole(role);
                account.setShopName("");
                account.setUsername(username);
                accountIO.update(account);
            }
            else {
                message = "Cập nhật tài khoản thành công";
                account.setBirthday(birthday);
                account.setGender(Gender);
                account.setLastUpdate(lastUpdate);
                account.setPhone(phone);
                account.setRole(role);
                account.setShopName("");
                account.setUsername(username);
                accountIO.update(account);
            }
            url = "/usercenter.jsp";
            request.getSession().setAttribute("account", account);
            request.setAttribute("message", message);
            request.setAttribute("account", account);
            request.setAttribute("username", username);
            request.getSession().setAttribute("username", account.getUsername());
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}
