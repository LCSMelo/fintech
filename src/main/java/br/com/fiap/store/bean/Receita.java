package br.com.fiap.store.bean;

import java.util.Calendar;

public class Receita {
	
	private int codigo;
	
	private String descricao;
	
	private double valor;
	
	private Calendar dataReceita;
	
	private String categoria;
	
	public Receita() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Receita(int codigo, String descricao, double valor, Calendar dataReceita, String categoria) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.dataReceita = dataReceita;
		this.categoria = categoria;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getDataReceita() {
		return dataReceita;
	}

	public void setDataReceita(Calendar dataReceita) {
		this.dataReceita = dataReceita;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	
	
}


