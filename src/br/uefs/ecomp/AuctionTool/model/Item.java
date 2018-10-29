
package br.uefs.ecomp.AuctionTool.model;

import br.uefs.ecomp.AuctionTool.util.*;
import java.util.Date;

public class Item implements Comparable{
    
    private int codigo; //Armazena o código de Item.
    private String nome; //Armazena o nome do item.
    private float preco_inicial; //Armazena o preço inicial do item.
    private Date data_criacao; //Armazena a data de criação do item.
    private Status status;//Armazena o status do item.
    private Pessoa dono; //Armazena o dono do Item.
    private Categoria categoria; //Armazena a categoria do item.
    private FilaPrioridade lances; //Armazena os lances do itens.
    private static  int codigo_item = 1;
    

    public Item(String nome, float preco_inicial, Date data_criacao, Pessoa dono, Categoria categoria) {
        this.codigo = codigo_item;
        codigo_item += 1;
        this.nome = nome;
        this.preco_inicial = preco_inicial;
        this.data_criacao = data_criacao;
        this.dono = dono;
        this.categoria = categoria;
        lances = new FilaPrioridade();
        status = Status.CADASTRADO;
    }

    public Categoria getCategoria() {
        return categoria; //Retorna a categoria do item.
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria; //Altera a categoria do item.
    }

    public int getCodigo() {
        return codigo; //Retorna o código do Item.
    }

    public String getNome() {
        return nome; //Retorna o nome do item.
    }

    public void setNome(String nome) {
        this.nome = nome; //Altera o nome do Item.
    }

    public float getPreco_inicial() {
        return preco_inicial; //Retorna o preço inicial do item.
    }

    public void setPreco_inicial(float preco_inicial) {
        this.preco_inicial = preco_inicial; //Altera o preço inicial do item.
    }

    public Date getData_criacao() {
        return data_criacao; //Retorna a data de criação do item.
    }

    public Status isStatus() {
        return status; //Retorna o status do item.
    }

    public void setStatus(Status status) {
        this.status = status; //Altera o status do item.
    }

    public Pessoa getDono() {
        return dono; //Retorna o dono do item.
    }

    public void setDono(Pessoa dono) {
        this.dono = dono; //Altera o dono do item.
    }

    public FilaPrioridade getLances() {
        return lances; //Retorna a fila de lances do item.
    }

    public void setLances(FilaPrioridade lances) {
        this.lances = lances; //Altera a fila de lances do item.
    }

    @Override
    public int compareTo(Object o) {
        Item compara = (Item) o;
        if(compara.getCodigo() == codigo){
            return 0;
        }
        else if(codigo < compara.getCodigo()){
            return -1;
        }
        else{
            return 1;
        }
    }
    
}
