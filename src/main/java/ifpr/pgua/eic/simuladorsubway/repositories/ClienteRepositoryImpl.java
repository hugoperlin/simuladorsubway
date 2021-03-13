package ifpr.pgua.eic.simuladorsubway.repositories;

import ifpr.pgua.eic.simuladorsubway.daos.interfaces.ClienteDAO;
import ifpr.pgua.eic.simuladorsubway.models.Cliente;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.ClienteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ClienteRepositoryImpl implements ClienteRepository {

    private ObservableList<Cliente> clientes;
    private ClienteDAO clienteDAO;

    public ClienteRepositoryImpl(ClienteDAO clienteDAO){
        clientes = FXCollections.observableArrayList();
        this.clienteDAO = clienteDAO;
    }

    @Override
    public boolean adicionar(Cliente cliente) throws SQLException {

        return clienteDAO.inserir(cliente);

    }

    @Override
    public boolean editar(int id, Cliente cliente) throws SQLException{

        return clienteDAO.atualizar(id,cliente);
    }

    @Override
    public ObservableList<Cliente> lista() throws SQLException {
        this.clientes.clear();
        this.clientes.addAll(clienteDAO.lista());
        return FXCollections.unmodifiableObservableList(clientes);
    }
}
