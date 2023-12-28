package br.com.fiap.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.store.bean.Usuario;
import br.com.fiap.store.bo.EmailBO;
import br.com.fiap.store.dao.UsuarioDAO;
import br.com.fiap.store.exception.EmailException;
import br.com.fiap.store.factory.DAOFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO dao;
    public LoginServlet() {
        dao = DAOFactory.getUsuarioDAO();
        new EmailBO();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = new Usuario(email, senha);

        if (dao.validarUsuario(usuario)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", email);
            String mensagem = "Um login foi realizado";
//            try {
//                bo.enviarEmail(email, "Login Realizado", mensagem);
//            } catch (EmailException e) {
//                e.printStackTrace();
//            }
        } else {
            request.setAttribute("erro", "Usuário e/ou senha inválidos");
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	session.invalidate();
    	request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
