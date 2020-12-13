/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.ads.paw.controller;

import br.upf.ads.paw.controladores.GenericDao;
import br.upf.ads.paw.entidades.Permissao;
import br.upf.ads.paw.entidades.Programa;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pavan
 */
@WebServlet(name = "ProgramaServletController", urlPatterns = {"/programa"})
public class ProgramaServletController extends HttpServlet {

    GenericDao<Programa> dao = new GenericDao(Programa.class);

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
    protected void doGet(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        Permissao p = Valida.acesso(req, resp, "Programa");
        if (p == null) {
            req.setAttribute("message", "Acesso negado. Tente fazer login.");
            RequestDispatcher dispatcher
                    = getServletContext().
                            getRequestDispatcher("/login?url=/programa");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("permissao", p);
            String action = req.getParameter("searchAction");
            if (action != null) {
                switch (action) {
                    case "searchById":
                        searchById(req, resp);
                        break;
                    case "searchByName":
                        if (p.getConsultar()) {
                            searchByName(req, resp);
                        } else {
                            req.setAttribute("message", "Você não tem permissão para consultar.");
                        }
                        forwardList(req, resp, null);
                        break;
                }
            } else {
                List<Programa> result = null;
                if (p.getConsultar()) {
                    result = dao.findEntities();
                } else {
                    req.setAttribute("message", "Você não tem permissão para consultar.");
                }
                forwardList(req, resp, result);
            }
        }
    }

    private void searchById(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        Programa obj = null;
        try {
            obj = dao.findEntity(id);
        } catch (Exception ex) {
            Logger.getLogger(ProgramaServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("obj", obj);
        req.setAttribute("action", "edit");
        String nextJSP = "/jsp/form-programa.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String search = req.getParameter("search");
        List<Programa> result = dao.findEntitiesByField("nome", search);  // buscar por nome
        forwardList(req, resp, result);
    }

    private void forwardList(HttpServletRequest req, HttpServletResponse resp, List entityList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list-programa.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("entities", entityList);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addAction(req, resp);
                break;
            case "edit":
                editAction(req, resp);
                break;
            case "remove":
                removeById(req, resp);
                break;
        }
    }

    private void addAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String nome = req.getParameter("nome");

            Programa obj = new Programa(null, nome);
            dao.create(obj);
            long id = obj.getId();
            req.setAttribute("id", id);
            String message = "Um novo registro foi criado com sucesso.";
            req.setAttribute("message", message);
            doGet(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(ProgramaServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        long id = Integer.valueOf(req.getParameter("id"));
        Programa obj = new Programa(id, nome);
        boolean success = false;
        try {
            dao.edit(obj);
            success = true;
        } catch (Exception ex) {
            Logger.getLogger(ProgramaServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String message = null;
        if (success) {
            message = "O registro foi atualizado com sucesso";
        }
        req.setAttribute("id", obj.getId());
        req.setAttribute("message", message);
        doGet(req, resp);
    }

    private void removeById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        boolean confirm = false;
        try {
            dao.destroy(id);
            confirm = true;
        } catch (Exception ex) {
            Logger.getLogger(ProgramaServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (confirm) {
            String message = "O registro foi removido com sucesso.";
            req.setAttribute("message", message);
        }
        doGet(req, resp);
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
