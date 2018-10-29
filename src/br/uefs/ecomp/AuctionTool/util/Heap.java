/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.AuctionTool.util;

/**
 *
 * @author AlmirNeto
 */
public class Heap {
    
    private int maxSize; //Armazena o tamanho máximo da HEAP
    private int size; //Armazena a atual posição onde o próximo elemento será inserido.
    private Comparable[] dados; //Array para armazenar a HEAP.
    
    public Heap(int max){
        dados = new Comparable[max]; //Cria um novo vetor, para a HEAP
        maxSize = max;
        size = 0;
    }
    
    public boolean isEmpty(){
        return (size == 0); //Retorna true caso a HEAP esteja vazia.
    }
    
    public int size(){
        return size; //Retorna o tamanho da HEAP.
    }
    
    public Comparable peek(){
        if(dados[0] == null){
            return null;
        }
        return dados[0]; //Retorna o primeiro elemendo da heap.
    }
    
    public void add(Comparable obj){
        /*Dobra o tamanho da heap caso esteja cheia.*/
        if(maxSize == size){
            Comparable[] bigger = new Comparable[size * 2];
            //Método mostrado em sala pelo Professor João, para realizar a cópia do array.
            System.arraycopy(dados, 0, bigger, 0, size); 
            dados = bigger;
            maxSize *= 2;
        }
        /*Insere um novo elemento na HEAP*/
        dados[size] = obj;
        trickleUp(size++);
    }
    
    public Comparable remove(){
        if(dados[0] == null){
            return null;
        }
        /*Remove o primeiro elemento da HEAP.*/
        Comparable removed = dados[0];
        dados[0] = dados[--size];
        trickleDown(0);
        return removed;
        
    }
    
    private void trickleUp(int index){
        /*Puxa o elemento que foi inserido no fim, para sua posição correta.*/
        int pai = (index - 1) / 2;
        Comparable bottom = dados[index];
    
        while(index > 0 && dados[pai].compareTo(bottom) < 0){
          dados[index] = dados[pai];
          index = pai;
          pai = (pai - 1) / 2;
        }
        dados[index] = bottom;
    }
    
    private void trickleDown(int index){
        /*Joga o elemento para sua posição correta.*/
        int filhoMaior;
        Comparable top = dados[index];
        
        while(index < (size / 2)){
            int filho_esquerda = 2*index+1;
            int filho_direita = 2*index+2;
            
            if(filho_direita < size && dados[filho_esquerda].compareTo(dados[filho_direita]) < 0){
                filhoMaior = filho_direita;
            }
            else{
                filhoMaior = filho_esquerda;
            }
            if(top.compareTo(dados[filhoMaior]) >= 0){
                break;
            }
        dados[index] = dados[filhoMaior];
        index = filhoMaior;
        }
        dados[index] = top;
    }
        
}
