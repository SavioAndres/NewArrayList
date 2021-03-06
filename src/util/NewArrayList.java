package util;

import java.security.InvalidParameterException;
import java.util.Iterator;

/**
 *
 * @author Sávio Andres
 * @param <T>
 */
public class NewArrayList<T> implements Lista<T>, Pilha<T>, Fila<T> {

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
            System.arraycopy(this.elementos, 0, novoVetor, 0, this.tamanho);
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
    public void adicionarTodos(T... elementos) {
        for (T elemento : elementos) {
            this.adicionar(elemento);
        }
    }
    
    @Override
    public T obter(int indice) {
        vIndice(indice);
        return this.elementos[indice];
    }
    
    @Override
    public int obterIndice(T elemento) {
        for (int i = 0; i < this.tamanho; i++) {
            if (obter(i).equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int[] obterIndices(T elemento) {
        int cont = -1;
        int indices[] = new int[contemQuantos(elemento)];
        for (int i = 0; i < this.tamanho; i++) {
            if (obter(i).equals(elemento)) {
                cont = cont + 1;
                indices[cont] = i;
            }
        }
        return indices;
    }

    @Override
    public T remover(int indice) {
        vIndice(indice);
        T obj = obter(indice);
        for (int i = indice; i < this.tamanho; i++) {
            this.elementos[i] = this.elementos[i + 1];
        }
        this.tamanho--;
        return obj;
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
        return contemQuantos(elemento) != 0;
    }
    
    @Override
    public int contemQuantos(T elemento) {
        int cont = 0;
        for (int i = 0; i < this.tamanho; i++) {
            if (obter(i).equals(elemento)) {
                cont = cont + 1;
            }
        }
        return cont;
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

    //Pilha
    @Override
    public void empilhar(T elemento) {
        adicionar(elemento);
    }

    @Override
    public T desempilhar() {
        return remover(this.tamanho - 1);
    }

    @Override
    public T topo() {
        return obter(this.tamanho - 1);
    }

    //Fila
    @Override
    public void enfileirar(T elemento) {
        adicionar(elemento);
    }

    @Override
    public T desenfileirar() {
        return remover(0);
    }

    @Override
    public T primeiro() {
        return obter(0);
    }

    @Override
    public T ultimo() {
        return obter(this.tamanho - 1);
    }
    
    @Override
    public String toString() {
        if (this.tamanho != 0) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < this.tamanho - 1; i++) {
                str.append(obter(i)).append(", ");
            }
            return "[" + str.toString() + obter(tamanho - 1) + "]";
        } else {
            return "[]";
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorVetor();
    }
    
    private class IteradorVetor implements Iterator<T> {

        private int posicaoAtual = 0;
        
        @Override
        public boolean hasNext() {
            return posicaoAtual < tamanho;
        }

        @Override
        public T next() {
            return elementos[posicaoAtual++];
        }
        
    }
    
}
