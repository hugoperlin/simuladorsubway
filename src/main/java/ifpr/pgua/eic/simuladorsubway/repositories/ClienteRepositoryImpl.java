package ifpr.pgua.eic.simuladorsubway.repositories;

import ifpr.pgua.eic.simuladorsubway.models.Cliente;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.ClienteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteRepositoryImpl implements ClienteRepository {

    private ObservableList<Cliente> clientes;


    public ClienteRepositoryImpl(){
        clientes = FXCollections.observableArrayList();
    }

    @Override
    public boolean adicionar(Cliente cliente) {

        clientes.add(new Cliente(clientes.size(),cliente.getNome(),cliente.getEmail(), cliente.getTelefone()));

        return true;
    }

    @Override
    public boolean editar(int id, Cliente cliente) {

        Cliente antigo = clientes.stream().filter((c -> c.getId() == id)).findFirst().get();

        if(antigo != null){

            antigo.setNome(cliente.getNome());
            antigo.setEmail(cliente.getEmail());
            antigo.setTelefone(cliente.getTelefone());

            return  true;
        }

        return false;
    }

    @Override
    public ObservableList<Cliente> lista() {
        return FXCollections.unmodifiableObservableList(clientes);
    }
}
