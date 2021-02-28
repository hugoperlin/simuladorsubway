package ifpr.pgua.eic.simuladorsubway.models;

public class Bebida {

    private int id;
    private String nome;
    private double valor;

    public Bebida(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Bebida(String nome, double valor) {
        this(-1,nome,valor);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                '}';
    }
}
