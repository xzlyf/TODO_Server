package com.xz.app.todolist.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        JSONObject json = new JSONObject();
        json.put("date", System.currentTimeMillis());
        if (key != null) {
            json.put("key", key);
        }
        try {
            resp.getWriter().println(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resp.getWriter().close(); // 关闭这个流，不然会发生错误的
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
