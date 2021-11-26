package SellerCenter;

import Data.orderDetailIO;
import Model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "bill", value = "/bill")
public class BillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "bill"; // default action
        }
        Account acc = (Account) request.getSession().getAttribute("account");
        long id1 = acc.getId();
        HttpSession session = request.getSession();
        List<?> listbill = orderDetailIO.selectOrderList(id1);
        session.setAttribute("listBill", listbill);
        String url = "/bill.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
