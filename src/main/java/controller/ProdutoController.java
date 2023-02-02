package controller;

/*
 * @author allfel
 *
 * Data: 02/02/2023
 */

import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

	public void deletar(Integer id) {
		System.out.println("Deletando produto");
	}

	public void salvar(Produto produto) {
		System.out.println("Salvando produto");
	}

	public List<Produto> listar() {
		List<Produto> produtos =
			new ArrayList<>();
		produtos.add(new Produto("Nome do Produto de teste"
				, "Descrição do produto de teste"));
		return produtos;
	}

	public void alterar(String nome, String descricao, Integer id) {
		System.out.println("Alterando produto");
	}
}
