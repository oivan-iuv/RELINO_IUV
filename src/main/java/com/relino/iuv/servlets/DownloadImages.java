/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.servlets;

/**
 *
 * @author IvanPC
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/imagen.jpg")
public class DownloadImages extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        byte[] content = (byte[]) req.getSession().getAttribute("imagenBytes");
        if (content == null) {
            content = new byte[0];
        }
//        req.getSession().removeAttribute("imagenBytes");
//        resp.reset();
        resp.setContentType("aplication/jpeg");
        resp.setContentLength(content.length);
        resp.getOutputStream().write(content);
        resp.getOutputStream().flush();
        resp.getOutputStream().close();

        //  req.getSession().removeAttribute("imagenBytes");


    }
    
}
