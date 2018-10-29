/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.AuctionTool.util;


public class HeapSort {
    
    public static void heapSort(Comparable[] ordena, int size){
        if(ordena == null){
            return;
        }
        buildMinHeap(ordena, size); //Contrói a heap.
        for(int i = size - 1; i > 0; i--){
            swap(ordena, 0, i); //Troca o primeiro elemento com o último.
            minHeapify(ordena, 0, i); //Constroe a Heap novamente.
        }
    }
    
    private static void buildMinHeap(Comparable[] ordenar, int size){
        for(int i = size; i >= 0; i--){
            minHeapify(ordenar, i, size);
        }
    }
    
    private static void minHeapify(Comparable[] ordenar, int atual, int size){
        /*Constrói uma minHeap para que seja possível ordenar um vetor decrescentemente.*/
        int pai = atual;
        int littleChild = min(ordenar, pai*2+1, pai*2+2, size);
        /*Joga o menor elemento cada vez mais pra cima.*/
        while(littleChild < size && ordenar[littleChild].compareTo(ordenar[pai]) < 0){
            swap(ordenar, pai, littleChild);
            pai = littleChild;
            littleChild = min(ordenar, pai*2+1, pai*2+2, size);
        }
    }
    
    private static void swap(Comparable[] troca, int first, int second){
        /*Troca 2 elementos de um vetor*/
        Comparable temp = troca[first];
        troca[first] = troca[second];
        troca[second] = temp;
    }
    
    private static int  min(Comparable[] ordena, int left, int right, int size){
        /*Verifica qual o menor filho de um determinado pai.*/

        if(left < size && right < size){
            return ordena[left].compareTo(ordena[right]) <= 0 ? left : right;
        }
        else if(left < size){
            return left;
        }
        else if(right < size){
            return right;
        }
        
        else{
            return size;
        }
    }
}


