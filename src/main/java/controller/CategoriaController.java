package controller;

import model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

	public List<Categoria> listar() {
		List<Categoria> categorias =
			new ArrayList<>();
		categorias.add(new Categoria(1, "Categoria de teste")); 
		return categorias;
	}
}
