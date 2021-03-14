package ifpr.pgua.eic.simuladorsubway.repositories;

import ifpr.pgua.eic.simuladorsubway.daos.interfaces.*;
import ifpr.pgua.eic.simuladorsubway.models.Pedido;
import ifpr.pgua.eic.simuladorsubway.models.Sanduiche;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.PedidoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class PedidoRepositoryImpl implements PedidoRepository {

    private ObservableList<Sanduiche> sanduiches;
    private ObservableList<Pedido> pedidos;

    private PedidoDAO pedidoDAO;
    private SanduicheDAO sanduicheDAO;
    private ClienteDAO clienteDAO;
    private BebidaDAO bebidaDAO;
    private IngredienteDAO ingredienteDAO;


    public PedidoRepositoryImpl(PedidoDAO pedidoDAO,
                                SanduicheDAO sanduicheDAO,
                                ClienteDAO clienteDAO,
                                BebidaDAO bebidaDAO,
                                IngredienteDAO ingredienteDAO){

        this.pedidoDAO = pedidoDAO;
        this.sanduicheDAO = sanduicheDAO;
        this.clienteDAO = clienteDAO;
        this.bebidaDAO = bebidaDAO;
        this.ingredienteDAO = ingredienteDAO;

        sanduiches = FXCollections.observableArrayList();
        pedidos = FXCollections.observableArrayList();
    }


    @Override
    public boolean adicionar(Pedido pedido) throws SQLException {

        return pedidoDAO.inserir(pedido);

    }

    public boolean adicionar(Sanduiche sanduiche) throws SQLException{
        return sanduicheDAO.inserir(sanduiche);
    }

    @Override
    public ObservableList<Pedido> lista() throws SQLException{

        this.pedidos.clear();

        List<Pedido> lista = pedidoDAO.lista();

        for(Pedido p:lista){
            p.setCliente(clienteDAO.buscaClienteDoPedido(p.getId()));

            p.setBebida(bebidaDAO.buscaBebidaDoPedido(p.getId()));
            Sanduiche sanduiche = sanduicheDAO.buscaSanduicheDoPedido(p.getId());
            sanduiche.adicionaTodos(ingredienteDAO.ingredientesDoSanduiche(sanduiche.getId()));
            p.setSanduiche(sanduiche);
        }


        this.pedidos.addAll(lista);


        return FXCollections.unmodifiableObservableList(pedidos);
    }
}
