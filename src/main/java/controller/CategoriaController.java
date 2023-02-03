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
import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

	private CategoriaDAO categoriaDAO;

	public CategoriaController() {
		Connection connection = new ConnectionFactory().recuperaConexao();
		this.categoriaDAO = new CategoriaDAO(connection);
	}


	public List<Categoria> listar() {
		List<Categoria> categorias = new ArrayList<>();
		categorias.add(new Categoria(1, "Categoria de teste")); 
		return categorias;
	}
}
