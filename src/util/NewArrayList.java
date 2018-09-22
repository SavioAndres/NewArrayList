package util;

import java.security.InvalidParameterException;
import java.util.Iterator;

/**
 *
 * @author Sávio Andres
 * @param <T>
 */
public class NewArrayList<T> implements Lista<T> {

    private T[] elementos;
    private int tamanho, capacidade;

    public NewArrayList() {
        this.capacidade = 10;
        this.tamanho = 0;
        this.elementos = (T[]) new Object[capacidade];
    }

    private void capacidade() {
        if (this.tamanho == this.capacidade) {
            int novaCapacidade = capacidade + (capacidade / 2);
            T[] novoVetor = (T[]) new Object[novaCapacidade];
            for (int i = 0; i < this.tamanho; i++) {
                novoVetor[i] = this.elementos[i];
            }
            this.elementos = novoVetor;
            this.capacidade = novoVetor.length;
        }
    }

    private void vIndice(int indice) {
        if (indice < 0 || indice > this.capacidade) {
            throw new InvalidParameterException("Posição inválida");
        }
    }

    @Override
    public void adicionar(T elemento) {
        capacidade();
        this.elementos[tamanho] = elemento;
        this.tamanho++;
    }

    @Override
    public void adicionar(int indice, T elemento) {
        vIndice(indice);
        capacidade();
        for (int i = this.tamanho; i >= indice; i--) {
            if (i != 0) {
                this.elementos[i] = this.elementos[i - 1];
            }
        }
        this.elementos[indice] = elemento;
        this.tamanho++;
    }

    @Override
    public T obter(int indice) {
        vIndice(indice);
        return this.elementos[indice];
    }

    @Override
    public void remover(int indice) {
        vIndice(indice);
        for (int i = indice; i < this.tamanho; i++) {
            this.elementos[i] = this.elementos[i + 1];
        }
        this.tamanho = this.tamanho - 1;
    }

    @Override
    public boolean remover(T elemento) {
        for (int i = 0; i < this.tamanho; i++) {
            if (this.elementos[i] == elemento) {
                remover(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contem(T elemento) {
        for (int i = 0; i < this.tamanho; i++) {
            if (this.elementos[i] == elemento) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public void limpar() {
        this.capacidade = 10;
        this.tamanho = 0;
        for (int i = 0; i < this.capacidade; i++) {
            this.elementos[i] = null;
        }
    }

    @Override
    public boolean vazio() {
        for (int i = 0; i < this.tamanho; i++) {
            if (this.elementos[i] != null) {
                return false;
            } 
        }
        return true;
    }

    @Override
    public String toString() {
        return "NewArrayList{" + "elementos=" + elementos + ", tamanho=" + tamanho + ", capacidade=" + capacidade + '}';
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorVetor();
    }

    private class IteradorVetor implements Iterator<T> {

        private int posicaoAtual = 0;
        
        @Override
        public boolean hasNext() {
            if(posicaoAtual < tamanho){
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            return elementos[posicaoAtual++];
        }
        
    }
    
}
