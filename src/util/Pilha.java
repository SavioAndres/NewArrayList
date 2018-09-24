package util;

/**
 *
 * @author Sávio Andres
 * @param <T>
 */
public interface Pilha<T> extends Iterable<T> {
    public void empilhar(T elemento);
    public T desempilhar();
    public T topo();
    public int tamanho();
    public boolean contem(T elemento);
    public void limpar();
    public boolean vazio();
}
