package org.zhl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-07-24 15:41
 **/
@WebServlet(name = "t", urlPatterns = "/*")
public class TestS extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("hhh");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello world");
    }
}
