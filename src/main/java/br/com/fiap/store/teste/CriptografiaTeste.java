package br.com.fiap.store.teste;

import br.com.fiap.store.util.CriptografiaUtils;

public class CriptografiaTeste {

	public static void main(String[] args) {
		try {
			System.out.println(CriptografiaUtils.criptografar("123456"));
			System.out.println(CriptografiaUtils.criptografar("leo203412"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}