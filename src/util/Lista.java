package util;

/**
 *
 * @author Sávio Andres
 * @param <T>
 */
public interface Lista<T> extends Iterable<T> {
    public void adicionar(T elemento);
    public void adicionar(int indice, T elemento);
    public void adicionarTodos(T... elementos);
    public T obter(int indice);
    public int obterIndice(T elemento);
    public int[] obterIndices(T elemento);
    public T remover(int indice);
    public boolean remover(T elemento);
    public boolean contem(T elemento);
    public int contemQuantos(T elemento);
    public int tamanho();
    public void limpar();
    public boolean vazio();
}
