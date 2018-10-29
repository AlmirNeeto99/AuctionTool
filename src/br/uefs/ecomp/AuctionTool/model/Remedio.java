
package br.uefs.ecomp.AuctionTool.model;

import java.util.Date;

/*Herda de 'ITEM', pois não deixa de ser um item.*/
public class Remedio extends Item{
    
    private Date dt_validade; //Armazena a data de validade do remédio.
    private boolean precisa_receita; //Diz se o remédio precisa de receita.
    
    /*Construtor*/
    public Remedio(String nome, Categoria categoria, float preco, Date data, Pessoa dono, Date dt_validade, boolean precisa){
        super(nome, preco, data, dono, categoria);
        this.dt_validade = dt_validade;
        this.precisa_receita = precisa;
    }

    public Date getDt_validade() {
        return dt_validade; //Retorna a data de validade do Remedio.
    }

    public void setDt_validade(Date dt_validade) {
        this.dt_validade = dt_validade; //Altera a data de validade do Remedio.
    }

    public boolean isPrecisa_receita() {
        return precisa_receita; //Retorna se o remedio precisa de receita.
    }

    public void setPrecisa_receita(boolean precisa_receita) {
        this.precisa_receita = precisa_receita;
    }
    
    
}
