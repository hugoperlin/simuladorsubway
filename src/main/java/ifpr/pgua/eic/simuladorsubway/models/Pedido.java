package ifpr.pgua.eic.simuladorsubway.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pedido {

    private int id;
    private Sanduiche sanduiche;
    private Bebida bebida;
    private Cliente cliente;
    private double valorTotal;
    private LocalDateTime data;

    public Pedido(int id, Sanduiche sanduiche, Bebida bebida, Cliente cliente, LocalDateTime data){
        this.id = id;
        this.sanduiche = sanduiche;
        this.bebida = bebida;
        this.cliente = cliente;
        this.data = data;
    }

    public Pedido(Sanduiche sanduiche, Bebida bebida, Cliente cliente){
        this(-1,sanduiche,bebida,cliente, LocalDateTime.now());
    }
    public Pedido(int id, Sanduiche sanduiche, Bebida bebida, Cliente cliente,LocalDateTime data, Double valor){
        this(id,sanduiche,bebida,cliente, data);
        this.valorTotal = valor;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setSanduiche(Sanduiche sanduiche) {
        this.sanduiche = sanduiche;
    }

    public double getValorTotal() {
        return sanduiche.getValorTotal() + bebida.getValor();
    }

    public Sanduiche getSanduiche() {
        return sanduiche;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", sanduiche=" + sanduiche +
                ", bebida=" + bebida +
                ", valorTotal=" + valorTotal +
                ", data=" + data.toString() +
                ", cliente="+cliente +
                '}';
    }
}
