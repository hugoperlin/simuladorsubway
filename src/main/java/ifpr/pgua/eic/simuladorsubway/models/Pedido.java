package ifpr.pgua.eic.simuladorsubway.models;

import java.time.LocalDate;

public class Pedido {

    private int id;
    private Sanduiche sanduiche;
    private Bebida bebida;
    private Cliente cliente;
    private double valorTotal;
    private LocalDate data;

    public Pedido(int id, Sanduiche sanduiche, Bebida bebida, Cliente cliente, LocalDate data){
        this.id = id;
        this.sanduiche = sanduiche;
        this.bebida = bebida;
        this.cliente = cliente;
        this.data = data;
    }

    public Pedido(Sanduiche sanduiche, Bebida bebida, Cliente cliente){
        this(-1,sanduiche,bebida,cliente, LocalDate.now());
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

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", sanduiche=" + sanduiche +
                ", bebida=" + bebida +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
