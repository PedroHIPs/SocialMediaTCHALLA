/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import controller.DAOJPA;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;

/**
 *
 * @author aluno
 */
public class SCadastrar extends HttpServlet {

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
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        DAOJPA dao;
        Usuario objU;
        String Data = "";
        Date dataNasc;
        PrintWriter out = response.getWriter();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-dd-MM");
            Data = request.getParameter("txtData");
            dataNasc = df.parse(Data);
            objU = new Usuario();
            objU.setNome(request.getParameter("txtName"));
            objU.setSenha(request.getParameter("txtPass"));
            objU.setDatanasc(dataNasc);
            dao = new DAOJPA();
            dao.gravar(objU);

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SGravar</title>");
            out.println("<link rel='stylesheet' type='text/css' href='CSS/myCss.css'>");
            out.println("</head>");
            out.println("<body class='bhi'>");
            out.println("<h1 class='hi' > " + request.getParameter("txtName") + " Gravado com sucesso </h1>");
            out.println("</br></br></br><div align='center'><img src='imgs/hqdefault.jpg' width='480' height='360' alt='hqdefault' /></div>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SGravar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Erro Servlet SCadastrar at " + ex.getMessage() + "</h1>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
