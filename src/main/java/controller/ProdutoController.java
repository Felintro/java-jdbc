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
import java.util.List;

public class ProdutoController {

	private ProdutoDAO produtoDAO;

	public ProdutoController() {
		Connection connection = new ConnectionFactory().recuperaConexao();
		this.produtoDAO = new ProdutoDAO(connection);
	}

	public void deletar(Integer idProduto) {
		System.out.println("Deletando produto...");
		produtoDAO.deleteProdutoById(idProduto);
		System.out.println("Produto deletado com sucesso!");
	}

	public void salvar(Produto produto) {
		System.out.println("Salvando produto...");
		produtoDAO.insertProduto(produto);
		System.out.println("Produto salvo com sucesso:");
		System.out.println(String.format("%d - %s - %s", produto.getId(), produto.getNome(), produto.getDescricao()));
	}

	public List<Produto> listar() {
		return produtoDAO.findAllProdutos();
	}

	public void alterar(String novoNome, String novaDescricao, Integer id) {
		System.out.println("Alterando produto...");
		produtoDAO.updateProdutoById(id, novoNome, novaDescricao);
		System.out.println("Produto alterado com sucesso!");
	}

}
