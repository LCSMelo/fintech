package br.com.fiap.store.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.store.bean.Receita;
import br.com.fiap.store.dao.ReceitaDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

@WebServlet("/receita")
public class ReceitaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ReceitaDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = DAOFactory.getReceitaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        switch (acao) {
            case "listar":
                listar(request, response);
                break;
            case "abrir-form-edicao":
                abrirFormEdicao(request, response);
                break;
            case "abrir-form-cadastro":
                abrirFormCadastro(request, response);
                break;
        }
    }

    private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("cadastro-receita.jsp").forward(request, response);
    }

    private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("codigo"));
        Receita receita = dao.buscar(id);
        request.setAttribute("receita", receita);
        request.getRequestDispatcher("edicao-receita.jsp").forward(request, response);
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Receita> lista = dao.listar();
        request.setAttribute("receita", lista);
        request.getRequestDispatcher("lista-receita.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
            case "excluir":
                excluir(request, response);
                break;
        }
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        try {
            dao.remover(codigo);
            request.setAttribute("msg", "Receita removida!");
        } catch (DBException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao remover a receita.");
        }
        listar(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            String descricao = request.getParameter("descricao");
            double valor = Double.parseDouble(request.getParameter("valor"));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar dataReceita = Calendar.getInstance();
            dataReceita.setTime(format.parse(request.getParameter("data")));

            Receita receita = new Receita(codigo, descricao, valor, dataReceita, null);
            dao.atualizar(receita);

            request.setAttribute("msg", "Receita atualizada!");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao atualizar.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados");
        }
        listar(request, response);
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String descricao = request.getParameter("nome");
            double valor = Double.parseDouble(request.getParameter("valor"));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar dataReceita = Calendar.getInstance();
            dataReceita.setTime(format.parse(request.getParameter("data")));
            String categoria = request.getParameter("categoria");

            Receita receita = new Receita(0, descricao, valor, dataReceita, categoria);

            dao.cadastrar(receita);

            request.setAttribute("msg", "Receita cadastrada!");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados");
        }
        abrirFormCadastro(request, response);
    }
}
