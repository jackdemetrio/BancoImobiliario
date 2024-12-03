package models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name; // Nome do jogador
    private double balance; // Saldo do jogador
    private Board.Node position; // Posição atual do jogador no tabuleiro
    private List<Property> properties; // Lista de propriedades do jogador
    private int prisonTurns; // Número de turnos que o jogador ainda ficará preso

    // Construtor da classe Player
    public Player(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.properties = new ArrayList<>();
        this.prisonTurns = 0;
    }

    // Métodos getters e setters
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Board.Node getPosition() {
        return position;
    }

    public void setPosition(Board.Node position) {
        this.position = position;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void removeProperty(Property property) {
        properties.remove(property);
    }

    public int getPrisonTurns() {
        return prisonTurns;
    }

    public void setPrisonTurns(int prisonTurns) {
        this.prisonTurns = prisonTurns;
    }
}
