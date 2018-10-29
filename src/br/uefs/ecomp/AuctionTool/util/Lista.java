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
public class Lista implements IList{

    private Node head; //Referência para a cabeça da lista.
    private Node tail; //Referência para a cauda da lista.
    private int size; //Armazena o tamanho da lista.
    
    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public int size() {
        return size; //Retorna o tamanho da lista.
    }

    @Override
    public void addBegin(Comparable obj) {
        if(isEmpty()){
            tail = head = new Node(obj); /*Se a lista estiver vazia atualiza a head e a tail.*/
            size++;
        }
        else{
            /*Adiciona um novo elemento na lista e o faz apontar para a cabeça
            e então atualiza a cabeça.*/
            Node temp = new Node(obj);
            temp.setNext(head);
            head = temp;
            size++;
        }
    }

    @Override
    public void addEnd(Comparable obj) {
        if(isEmpty()){
            /*Se a lista estiver vazia atualiza a head e a tail.*/
            head = tail = new Node(obj);
            size++;
        }
        else{
            /*Insere um elemento no fim da lista, tail passa a apontar para o novo elemento.
            e então tail é atualizada.*/
            Node temp = new Node(obj);
            tail.setNext(temp);
            tail = temp;
            size++;
        }
    }

    @Override
    public void removeBegin() {
        if(isEmpty()){
            return;
        }
        else{
            /*Remove o primeiro elemento da lista.*/
            head = head.getNext();
            size--;
        }
    }

    @Override
    public Comparable removeEnd() {
        if(isEmpty()){
            return null;
        }
        else{
            /*Remove o último elemento da lista e o retorna.*/
            Node temp = tail;
            Node anterior = getNode(size - 2);
            anterior.setNext(tail.getNext());
            tail = anterior;
            size--;
            return temp.getData();
        }
    }

    @Override
    public Comparable get(int index) {
        /*Pega e retorna um certo elemento da lista.*/
        if(index >= 0 && index < size()){
            Node temp = head;
            for(int i = 0; i < index; i++){
                temp = temp.getNext();
            }
            return temp.getData();
        }
        return null;  
    }
    
    private Node getNode(int index){
        /*Pega e retorna um certo NÓ da lista.*/
        if(index >= 0 && index < size()){
            Node temp = head;
            for(int i = 0; i < index; i++){
                temp = temp.getNext();
            }
            return temp;
        }
        return null;
    }

    public boolean contains(Comparable obj) {
        /*Verifica se o objeto passado por parâmetro existe na lista.*/
        Node temp = head;
        while(temp != null){
            if(temp.getData().equals(obj)){
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public void remove(int index) {
        /*Remove um determinado objeto presente na lista
        a partir de seu índice.*/
        if(index == 0){
            removeBegin();
        }
        else{
            Node anterior = getNode(index - 1);
            Node removido = getNode(index);
            
            anterior.setNext(removido.getNext());
            removido = null;
            size--;
        }
    }

    @Override
    public Iterator iterador() {
        return new MyIterador();
    }
    
    private class MyIterador implements Iterator{
        private int atual = 0;
        
        @Override
        public boolean hasNext(){
            /*Verifica se o próximo elemento da lista existe.*/
            return get(atual) != null;
        }
        
        @Override
        public Comparable next(){
            /*Retorna o próximo elemento da lista.*/
            return get(atual++);
        }
    }
    
    
    private class Node{

        private Comparable data; //Dados do nó.
        private Node next; //Próximo elemento.

    Node(Comparable novo){
        this.data = novo;
        this.next = null;
    }
        public Comparable getData() {
            return data; //Retorna os dados de um certo nó
        }

        public void setData(Comparable data) {
            this.data = data; //Altera os dados de um nó.
        }

        public Node getNext() {
            return next; //Retorna o elemento o qual o atual aponta.
        }

        public void setNext(Node next) {
            this.next = next; //Altera o próximo elemento o qual o atual aponta.
        }
    
    }
    
}
                                                                                