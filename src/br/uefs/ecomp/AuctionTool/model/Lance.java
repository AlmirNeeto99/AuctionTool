
package br.uefs.ecomp.AuctionTool.model;

import java.util.Date;


public class Lance implements Comparable{
    
    private Date dt_hr_lance; //Armazena a data e hora do lance.
    private Pessoa pessoa; //Armazena o dono do lance.
    private float valor; //Armazena o valor do lance.
    
    /*Construtor*/
    public Lance(Date data, Pessoa person, float value){
        this.dt_hr_lance = data;
        this.pessoa = person;
        this.valor = value;
    }

    public Date getDt_hr_lance() {
        return dt_hr_lance; //Retorna a data e hora do lance.
    }

    public Pessoa getPessoa() {
        return pessoa; //Retorna o dono do Lance.
    }

    public float getValor() {
        return valor;
    }

    /*Método necessário para inserir os itens ordenadamente na HEAP*/
    @Override
    public int compareTo(Object obj) {
            Lance compara = (Lance) obj;
            if(valor == compara.getValor()){
                return 0;
            }
            else if(valor < compara.getValor()){
                return -1;
            }
            else{
                return 1;
            }
        }  
}
