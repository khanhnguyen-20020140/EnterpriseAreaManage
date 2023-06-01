package com.sgis.khukt;

import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class DoanhnghiepReportServlet extends HttpServlet {
    public DoanhnghiepReportServlet() {
        super();
    }
    int accesses = 0;

    @SuppressWarnings("deprecation")
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String sessionID = "reports/" + session.getId() + new Date().getTime();
    }


//    protected void doGet(HttpServletRequest request,
//                         HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        try {
//            processRequest(request, response);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }


    @Override
    public void destroy(){
        System.out.println("destroy");
    }

    @Override
    public void init (ServletConfig arg0) throws ServletException{
        System.out.println("init");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        accesses++;
        out.print("Number of times this servlet has been accessed:" + accesses);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
            processRequest(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
