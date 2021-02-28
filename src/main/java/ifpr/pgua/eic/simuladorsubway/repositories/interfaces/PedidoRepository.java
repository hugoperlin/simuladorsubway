package ifpr.pgua.eic.simuladorsubway.repositories.interfaces;

import ifpr.pgua.eic.simuladorsubway.models.Pedido;
import ifpr.pgua.eic.simuladorsubway.models.Sanduiche;
import javafx.collections.ObservableList;

public interface PedidoRepository {

    boolean adicionar(Pedido pedido);
    boolean adicionar(Sanduiche sanduiche);

    ObservableList<Pedido> lista();



}
