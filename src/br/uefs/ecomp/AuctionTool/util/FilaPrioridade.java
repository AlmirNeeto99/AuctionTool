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
public class FilaPrioridade implements IPriorityQueue{
    
    private Heap fila; //Armazena os dados da heap.
    private Heap iterator; //Heap utilizada para percorre a HEAP.
    
    public FilaPrioridade(){
        fila = new Heap(10); //Cria uma heap com 10 posições.
    }

    @Override
    public boolean isEmpty() {
        return fila.isEmpty(); //Retorna TRUE se a fila estiver vazia.
    }

    @Override
    public int size() {
        return fila.size(); //Retorna o tamanho da FILA.
    }

    @Override
    public void add(Comparable obj){
        fila.add(obj); //Adiciona um novo objeto a fila.
        iterator = fila; //Atualiza o iterator.
    }

    @Override
    public Comparable remove() {
        
        Comparable removido = fila.remove(); //Remove o primeiro objeto da fila.
        iterator = fila; //Atualiza o iterator
        if(removido == null){
            return null;
        }
        else{
            return removido; //Retorna o primeiro elemento da fila.
        }
    }

    @Override
    public Comparable peek() {
        return fila.peek(); //Retorna o primeiro elemento da fila.
    }
    
    
    public Iterator iterador(){
        return new myIterator();
    }
    
    private class myIterator implements Iterator{

        @Override
        public boolean hasNext(){
            /*Verifica se o primeiro elemento da fila existe.*/
            return iterator.peek() != null;
        }
        
        @Override
        public Comparable next(){
            /*Retorna o primeiro elemento da fila e o remove da fila secundária.*/
            return iterator.remove();
        }    
    }
}
