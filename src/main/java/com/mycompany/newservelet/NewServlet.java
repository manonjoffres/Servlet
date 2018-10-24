/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.newservelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.*;

/**
 *
 * @author pedago
 */
@WebServlet(name ="ShowClient", urlPatterns = {"/ShowClient"})
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DAOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            //out.println("le parametre name vaut:" + request.getParameter("name"));

            DAO myDAO = new DAO(DataSourceFactory.getDataSource());
            Sous_Classe myDAO2 = new Sous_Classe(DataSourceFactory.getDataSource());

            //String state=request.getParameter("state");
            List<String> Etat = myDAO2.ListOfState();

            if (Etat.size() != 0) {
                out.println("<form method='post'>");
                out.println("<select name='etat'>");

                for (int i = 0; i < Etat.size(); i++) {
                    out.println("<option value=" + Etat.get(i) + ">" + Etat.get(i) + "</option>");
                }

                out.println("</select>");
                out.println("<input type='submit' value='Envoyer'/>");
                out.println("</form>");
            }

            //out.println(request.getParameter("etat"));
            List<CustomerEntity> CE = myDAO.customersInState(request.getParameter("etat"));

            if (CE.size() != 0) {
                out.println("<table border=1>");

                out.println("<tr>");
                out.println("<th>" + "ID" + "</th>");
                out.println("<th>" + "Name" + "</th>");
                out.println("<th>" + "Adress" + "</th>");
                out.println("</tr>");

                for (int i = 0; i < CE.size(); i++) {
                    out.println("<tr>");

                    out.println("<td>" + CE.get(i).getCustomerId() + "</td>");

                    out.println("<td>" + CE.get(i).getName() + "</td>");

                    out.println("<td>" + CE.get(i).getAddressLine1() + "</td>");

                    out.println("</tr>");
                }
            }

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("Dans le DOPOST");
            System.out.println(request.getParameter("etat"));
            processRequest(request, response);

        } catch (DAOException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
