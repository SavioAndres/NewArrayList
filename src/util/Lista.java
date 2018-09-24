package util;

/**
 *
 * @author Sávio Andres
 * @param <T>
 */
public interface Lista<T> extends Iterable<T> {
    public void adicionar(T elemento);
    public void adicionar(int indice, T elemento);
    public T obter(int indice);
    public T remover(int indice);
    public boolean remover(T elemento);
    public boolean contem(T elemento);
    public int tamanho();
    public void limpar();
    public boolean vazio();
}
