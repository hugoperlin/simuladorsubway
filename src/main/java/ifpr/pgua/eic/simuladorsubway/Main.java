package ifpr.pgua.eic.simuladorsubway;

import ifpr.pgua.eic.simuladorsubway.daos.*;
import ifpr.pgua.eic.simuladorsubway.daos.interfaces.*;
import ifpr.pgua.eic.simuladorsubway.gui.controls.Principal;
import ifpr.pgua.eic.simuladorsubway.models.Bebida;
import ifpr.pgua.eic.simuladorsubway.models.Cliente;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.models.Pedido;
import ifpr.pgua.eic.simuladorsubway.repositories.BebidaRepositoryImpl;
import ifpr.pgua.eic.simuladorsubway.repositories.ClienteRepositoryImpl;
import ifpr.pgua.eic.simuladorsubway.repositories.IngredienteRepositoryImpl;
import ifpr.pgua.eic.simuladorsubway.repositories.PedidoRepositoryImpl;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.BebidaRepository;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.ClienteRepository;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.IngredienteRepository;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.PedidoRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.SQLException;


/**
 * Classe principal para a interface gráfica da vendinha.
 * Além de incializar o sistema também permite a mudança de cena
 */

public class Main extends Application {

    /**
     * Atributos estáticos com o caminho para cada fxml. Note que o caminho
     * é relativo para a pasta resources do projeto. Utilizado pelo método mudaCena.
     * Caso queira inserir uma nova janela, crie um novo atributo e indique o caminho
     * do fxml.
     */


    public static final String PRINCIPAL = "/fxml/principal.fxml";
    public static final String ADICIONARINGREDIENTE = "/fxml/adicionar_ingrediente.fxml";
    public static final String ADICIONARCLIENTE = "/fxml/adicionar_cliente.fxml";
    public static final String ADICIONARBEBIDA = "/fxml/adicionar_bebida.fxml";
    public static final String ADICIONARPEDIDO = "/fxml/adicionar_pedido.fxml";

    /**
     * Atributo que representa a mercearia. Será utilizado
     * por todas as janelas, através da injeção de dependência.
     */
    private static IngredienteRepository ingredienteRepository;
    private static ClienteRepository clienteRepository;
    private static BebidaRepository bebidaRepository;
    private static PedidoRepository pedidoRepository;


    private static IngredienteDAO ingredienteDAO;
    private static BebidaDAO bebidaDAO;
    private static ClienteDAO clienteDAO;
    private static PedidoDAO pedidoDAO;
    private static SanduicheDAO sanduicheDAO;

    /**
     * Atrituto que repreenta o gerenciador base que será inserido
     * no stage. Por ser um StackPane, podemos inserir outros gerenciadores
     * de layout que sempre serão mostrados por cima.
     */

    private static StackPane base;


    private static void criaFakes() {

        try {
            ingredienteRepository.adicionar(new Ingrediente("Tomate", "", 0.5));
            ingredienteRepository.adicionar(new Ingrediente("Pão", "", 1.0));
            ingredienteRepository.adicionar(new Ingrediente("Queijo", "", 0.8));
            ingredienteRepository.adicionar(new Ingrediente("Presunto", "", 0.75));
            ingredienteRepository.adicionar(new Ingrediente("Bacon", "", 1.1));

            bebidaRepository.adicionar(new Bebida("Suco de Limão", 1.0));
            bebidaRepository.adicionar(new Bebida("Suco de Laranja", 1.5));
            bebidaRepository.adicionar(new Bebida("Suco de Abacaxi", 2.0));
            bebidaRepository.adicionar(new Bebida("Suco de Graviola", 3.0));
            bebidaRepository.adicionar(new Bebida("Suco de Acerola", 3.5));

            clienteRepository.adicionar(new Cliente("Cliente 1", "0", "cliente1@teste.com"));
            clienteRepository.adicionar(new Cliente("Cliente 2", "0", "cliente2@teste.com"));
            clienteRepository.adicionar(new Cliente("Cliente 3", "0", "cliente3@teste.com"));
            clienteRepository.adicionar(new Cliente("Cliente 4", "0", "cliente4@teste.com"));
            clienteRepository.adicionar(new Cliente("Cliente 5", "0", "cliente5@teste.com"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {


        Application.launch(args);
    }

    /**
     * Método executado no iníco da aplicação JavaFX.
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        super.init();

        ingredienteDAO = new JDBCIngredienteDAO();
        bebidaDAO = new JdbcBebidaDAO();
        clienteDAO = new JdbcClienteDAO();
        pedidoDAO = new JDBCPedidoDAO();
        sanduicheDAO = new JDBCSanduicheDAO();

        ingredienteRepository = new IngredienteRepositoryImpl(ingredienteDAO);
        clienteRepository = new ClienteRepositoryImpl(clienteDAO);
        bebidaRepository = new BebidaRepositoryImpl(bebidaDAO);
        pedidoRepository = new PedidoRepositoryImpl(pedidoDAO, sanduicheDAO,clienteDAO, bebidaDAO, ingredienteDAO);

        //Criando objetos temporários para teste
        //criaFakes();
    }

    /**
     * Método que é executado antes de mostrar a janela.
     * Alocamos o StackPane e colocamos na cena base.
     * <p>
     * Invocamos o método mudaCena para carregar a cena principal.
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        base = new StackPane();


        stage.setScene(new Scene(base, Region.USE_PREF_SIZE, Region.USE_PREF_SIZE));
        stage.setTitle("Simulador de Subway...");


        mudaCena(Main.PRINCIPAL, principalCallback());

        stage.show();

    }

    /**
     * Método executado sempre quando a aplicação é fechada.
     * Utilizamos para salvar o objeto mercearia no disco.
     *
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();

    }

    /**
     * Método que permite mudar o conteúdo da janela, permitindo
     * assim a troca de cena, navegando o usuário entre as funcionalidades do sistema.
     * Pode ser invocado de qualquer uma das classes do projeto.
     *
     * @param fxml              o conteúdo deve ser o especificado por um dos atributos estáticos da classe MainGui
     * @param controllerFactory lamba expression com um objeto do controlador da janela que será criada
     */


    public static void mudaCena(String fxml, Callback controllerFactory) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(fxml));
            loader.setControllerFactory(controllerFactory);

            Parent novoRoot = loader.load();

            //ja existe alguma coisa sendo mostrada, entao remover
            if (base.getChildren().stream().count() > 0) {
                base.getChildren().remove(0);
            }
            base.getChildren().add(novoRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void voltaPrincipal() {
        mudaCena(Main.PRINCIPAL, principalCallback());
    }

    private static Callback principalCallback() {
        return (aClass) -> new Principal(ingredienteRepository, clienteRepository, bebidaRepository, pedidoRepository);
    }

}
