package util;

/**
 *
 * @author Sávio Andres
 * @param <T>
 */
public interface Fila<T> extends Iterable<T> {
    public void enfileirar(T elemento);
    public T desenfileirar();
    public T primeiro();
    public T ultimo();
    public int tamanho();
    public boolean contem(T elemento);
    public void limpar();
    public boolean vazio();
}
