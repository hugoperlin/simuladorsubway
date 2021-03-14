package ifpr.pgua.eic.simuladorsubway.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Sanduiche {

    private int id;
    private ObservableList<Ingrediente> ingredientes;
    private double valorTotal;

    public Sanduiche(int id, ArrayList<Ingrediente> ingredientes){
        this.id = id;
        this.ingredientes = FXCollections.observableArrayList();
        this.ingredientes.addAll(ingredientes);
    }

    public Sanduiche(ArrayList<Ingrediente> ingredientes){
        this(-1,ingredientes);
    }

    public Sanduiche(){
        this(-1,new ArrayList<>());
    }

    public Sanduiche(int id, double valor){
        this(id,new ArrayList<>());
        this.valorTotal = valor;
    }

    public boolean adiciona(Ingrediente ingrediente){
        if(!this.ingredientes.contains(ingrediente)){
            this.ingredientes.add(ingrediente);
            return true;
        }
        return false;
    }


    public void adicionaTodos(List<Ingrediente> ingredientes){
        this.ingredientes.addAll(ingredientes);
    }

    public boolean remove(Ingrediente ingrediente){
        this.ingredientes.remove(ingrediente);

        return true;
    }

    public double getValorTotal(){

        this.valorTotal = 0.0;

        /*for(Ingrediente i:ingredientes){
            this.valorTotal += i.getValor();
        }*/

        this.valorTotal = ingredientes.stream().reduce(0.0,(subTotal,ingrediente)-> subTotal + ingrediente.getValor(), Double::sum );



        return this.valorTotal;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ObservableList<Ingrediente> getIngredientes() {
        return FXCollections.unmodifiableObservableList(ingredientes);
    }

    @Override
    public String toString() {
        return "Sanduiche{" +
                "id=" + id +
                ", ingredientes=" + ingredientes +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
