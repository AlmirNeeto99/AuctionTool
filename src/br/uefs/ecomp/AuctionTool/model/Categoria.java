
package br.uefs.ecomp.AuctionTool.model;

import java.util.Date;


public class Categoria implements Comparable{
    
    private int codigo; //Armazena o código da categoria
    private String nome; //Armazena o nome da Categoria.
    private Date data_criacao; //Armazena a data de criação da categoria.
    public static int codigo_Cat = 0;

    
    /*Construtor de Categoria*/
    public Categoria(String nome, Date data_criacao){
        this.codigo = codigo_Cat;
        codigo_Cat +=10;
        this.nome = nome;
        this.data_criacao = data_criacao;
    }

    public int getCodigo() {
        return codigo; //Retorna o código da categoria.
    }

    public String getNome() {
        return nome; //Retorna o nome da Categoria.
    }

    public void setNome(String nome) {
        this.nome = nome; //Altera o nome da categoria.
    }

    public Date getData_criacao() {
        return data_criacao; //Retorna a Data de criação da categoria.
    }   

    @Override
    public int compareTo(Object o) {
        if(o instanceof Categoria){
            Categoria compara = (Categoria) o;
            if(nome.equalsIgnoreCase(compara.getNome())){
                return 0;
            }
            else{
                return 1;
            }
        }
        return -1;
    }
}
