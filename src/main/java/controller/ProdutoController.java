package controller;

/*
 * @author allfel
 *
 * Data: 02/02/2023
 */

import dao.ProdutoDAO;
import factory.ConnectionFactory;
import model.Produto;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

	private ProdutoDAO produtoDAO;

	public ProdutoController() {
		Connection connection = new ConnectionFactory().recuperaConexao();
		this.produtoDAO = new ProdutoDAO(connection);
	}

	public void deletar(Integer id) {
		System.out.println("Deletando produto");
	}

	public void salvar(Produto produto) {
		System.out.println("Salvando produto...");
		Produto produtoSalvo = produtoDAO.insertProduto(produto);
		System.out.println("Produto salvo com sucesso:");
		System.out.println(String.format("%d - %s - %s", produtoSalvo.getId(), produtoSalvo.getNome(), produtoSalvo.getDescricao()));
	}

	public List<Produto> listar() {
		List<Produto> produtos = new ArrayList<>();
		produtos.add(new Produto("Nome do Produto de teste", "Descrição do produto de teste"));
		return produtos;
	}

	public void alterar(String nome, String descricao, Integer id) {
		System.out.println("Alterando produto");
	}
}
