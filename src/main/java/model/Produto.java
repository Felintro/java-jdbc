package model;
/*
 * @author allfel
 *
 * Data: 31/01/2023
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private Integer id;
    private String nome;
    private String descricao;


    public Produto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("Produto: %d - %s - %s", this.id, this.nome, this.descricao);
    }
}
