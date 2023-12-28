package br.com.fiap.store.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.store.bean.Receita;
import br.com.fiap.store.dao.ReceitaDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

public class TesteReceitaDAO {

    public static void main(String[] args) {
        ReceitaDAO dao = DAOFactory.getReceitaDAO();

        // Cadastrar uma receita
        Receita receita = new Receita(0, "Salário", 2000, Calendar.getInstance(), "Empresa XPTO");
        try {
            dao.cadastrar(receita);
            System.out.println("Receita cadastrada. Código: " + receita.getCodigo());
        } catch (DBException e) {
            e.printStackTrace();
        }

        // Buscar uma receita pelo código e atualizar
        int codigoAtualizar = receita.getCodigo(); // Use o código retornado após o cadastro
        receita = dao.buscar(19);
        if (receita != null) {
            receita.setDescricao("13º Salário");
            receita.setValor(3000);
            try {
                dao.atualizar(receita);
                System.out.println("Receita atualizada.");
            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Receita não encontrada para atualização.");
        }

        // Listar as receitas
        List<Receita> lista = dao.listar();
        System.out.println("Lista de Receitas:");
        for (Receita item : lista) {
            System.out.println(item.getCodigo() + " - " + item.getDescricao() + " - " + item.getValor());
        }

        // Remover uma receita
        try {
            dao.remover(codigoAtualizar); // Use o código da receita cadastrada
            System.out.println("Receita removida.");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
