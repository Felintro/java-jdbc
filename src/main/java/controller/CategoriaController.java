package controller;

/*
 * @author allfel
 *
 * Data: 02/02/2023
 */

import dao.CategoriaDAO;
import factory.ConnectionFactory;
import model.Categoria;

import java.sql.Connection;
import java.util.List;

public class CategoriaController {

	private CategoriaDAO categoriaDAO;

	public CategoriaController() {
		Connection connection = new ConnectionFactory().recuperaConexao();
		this.categoriaDAO = new CategoriaDAO(connection);
	}

	public List<Categoria> listar() {
		return this.categoriaDAO.findAllCategorias();
	}

	public void salvar(Categoria categoria) {
		System.out.println("Salvando categoria...");
		categoriaDAO.insertCategoria(categoria);
		System.out.println("Categoria salva com sucesso:");
		System.out.println(String.format("%d - %s", categoria.getId(), categoria.getNome()));
	}


}
