
package br.uefs.ecomp.AuctionTool.model;

import java.util.Date;

/*Herda de 'ITEM' pois não deixa de ser um item*/
public class Alimento extends Item{
    
    private Date validade; //Armazena a validade do alimento.
    
    public Alimento(String nome, Categoria categoria, float preco, Date data, Pessoa dono, Date validade){
        super(nome, preco, data, dono, categoria); //Chama o construtor de Item.
        this.validade = validade;
    }

    public Date getValidade() {
        return validade; //Retorna a validade.
    }

    public void setValidade(Date validade) {
        this.validade = validade; //Altera a Validade.
    }

}
