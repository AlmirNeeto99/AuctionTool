
package br.uefs.ecomp.AuctionTool.model;

import java.util.Date;
/*Herda de 'ITEM', pois não deixa de ser um item.*/
public class Imovel extends Item {
    
    private boolean imovel_ocupado; //Armazena o estado do imóvel
    
    public Imovel(String nome, Categoria categoria, float preco, Date data, Pessoa dono, boolean ocupacao){
        super(nome, preco, data, dono, categoria); //chama o construtor de ITEM
        imovel_ocupado = ocupacao;
    }

    public boolean isImovel_ocupado() {
        return imovel_ocupado; //Retorna a ocupação do imóvel.
    }

    public void setImovel_ocupado(boolean imovel_ocupado) {
        this.imovel_ocupado = imovel_ocupado; //Altera a ocupação do imóvel
    }
    
    
}
