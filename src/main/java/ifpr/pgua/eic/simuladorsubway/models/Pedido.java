package ifpr.pgua.eic.simuladorsubway.models;

import java.time.LocalDate;

public class Pedido {

    private int id;
    private Sanduiche sanduiche;
    private Bebida bebida;
    private double valorTotal;
    private LocalDate data;

    public Pedido(int id, Sanduiche sanduiche, Bebida bebida, LocalDate data){
        this.id = id;
        this.sanduiche = sanduiche;
        this.bebida = bebida;
        this.data = data;
    }

    public Pedido(Sanduiche sanduiche, Bebida bebida){
        this(-1,sanduiche,bebida, LocalDate.now());
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
