package ifpr.pgua.eic.simuladorsubway.repositories;

import ifpr.pgua.eic.simuladorsubway.models.Pedido;
import ifpr.pgua.eic.simuladorsubway.models.Sanduiche;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.PedidoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PedidoRepositoryImpl implements PedidoRepository {

    private ObservableList<Sanduiche> sanduiches;
    private ObservableList<Pedido> pedidos;


    public PedidoRepositoryImpl(){
        sanduiches = FXCollections.observableArrayList();
        pedidos = FXCollections.observableArrayList();
    }


    @Override
    public boolean adicionar(Pedido pedido) {

        pedidos.add(pedido);

        return true;
    }

    public boolean adicionar(Sanduiche sanduiche){
        sanduiches.add(sanduiche);

        return true;
    }

    @Override
    public ObservableList<Pedido> lista() {
        return FXCollections.unmodifiableObservableList(pedidos);
    }
}
