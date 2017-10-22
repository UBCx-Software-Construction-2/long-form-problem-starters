package model.pets;

import model.Human;

public class Pet {
    protected boolean friendly;
    protected boolean needsAttention;
    protected String species;
    protected String color;
    protected double price;

    public Pet(String species, String color, boolean friendly, boolean needsAttention, double price){
        this.species = species;
        this.color = color;
        this.friendly = friendly;
        this.needsAttention = needsAttention;
        this.price = price;
    }

    public Pet(String species, String color, double price){
        this.species = species;
        this.color = color;
        this.friendly = true;
        this.needsAttention = false;
        this.price = price;
    }

    //getters
    public String getSpecies() {
        return species;
    }

    public boolean isFriendly() { return friendly; }

    public boolean needsAttention() {
        return needsAttention;
    }

    public double getPrice() {
        return price;
    }

    public Human getHuman() {
        return null;
    }

    //REQUIRES: human != null
    //MODIFIES: this, human
    //EFFECTS: adopts human, and vice versa
    public void adoptHuman(Human human) {
        System.out.println("Adopting a human!");

        if (!human.hasPet(this)){
            human.adoptPet(this);
            System.out.println("Success! Adopted " + human);
        }
    }

    @Override
    public String toString() {
        return "Pet {" +
                "species='" + species + '\'' +
                ", friendly=" + friendly +
                ", color='" + color + '\'' +
                ", price='" + price + '\'' +
                ", human= " + //TODO 5
                '}';
    }
}
