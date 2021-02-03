/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dymama;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MOHAMED
 */
public class EtudiantsServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            /*RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/formulaire.html");
view.forward(request, response);
            */
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
        
         String valider=request.getParameter("valider");
         
        
         this.getServletContext().getRequestDispatcher("/WEB-INF/formulaire.html").forward(request, response);
         
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try {
                InputStream my_file_csv = new FileInputStream("etudiants.csv");
                InputStreamReader read_my_file_csv = new InputStreamReader(my_file_csv);
                BufferedReader my_bufferReader = new BufferedReader(read_my_file_csv);
                String l;
                out.println("<Table borde='10'>");
                out.println("<tr>");
                out.println("<td>Noms Etudiants</td>");
                out.println("<td>Prenom Etudiants</td>");
                out.println("<td>Email Etudiants</td>");
                out.println("</tr>");
                while ((l = my_bufferReader.readLine()) != null) {
                    //out.println(ligne);
                    out.println("<br>");
                    String resultat[] = l.split(",");
                    out.println("<tr>");
                    for (int i = 0; i < resultat.length; i++) {
                        out.println("<td>");
                        out.println("\t" + resultat[i] + "\t");
                        out.println("</td>");
                    }
                }
                out.println("</tr>");
                out.println("</table>");

                my_bufferReader.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
     
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
        String nom=request.getParameter("nom");
        String prenom=request.getParameter("prenom");
        String email=request.getParameter("email");
        
         try (PrintWriter my_PrintWriter = new PrintWriter(new File("etudiants.csv")))
         {
        StringBuilder my_sStringBuilder = new StringBuilder();
        my_sStringBuilder.append("nom,");
        my_sStringBuilder.append("prenom,");
        my_sStringBuilder.append("email");
        my_sStringBuilder.append('\n');
        my_PrintWriter.write(my_sStringBuilder.toString());
        System.out.println("OK!");
         }
         catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
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
