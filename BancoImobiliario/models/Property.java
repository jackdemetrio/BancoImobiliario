package models;

public class Property {
    private String name; // Nome da propriedade
    private double purchasePrice; // Preço de compra da propriedade
    private double rentPrice; // Preço do aluguel da propriedade
    private Player owner; // Proprietário da propriedade

    // Construtor da classe Property
    public Property(String name, double purchasePrice, double rentPrice) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.rentPrice = rentPrice;
        this.owner = null;
    }

    // Métodos getters e setters
    public String getName() {
        return name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
