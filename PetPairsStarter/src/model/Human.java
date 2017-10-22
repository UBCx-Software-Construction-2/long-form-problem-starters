package model;

import model.pets.Pet;


public class Human {
    private String name;
    private double spendibees;

    public Human(String name) {
        this.name = name;
        this.spendibees = 0;
    }

    //getters
    public double getSpendibees() {
        return spendibees;
    }

    public boolean hasPet(Pet pet) {
        return this.pets.contains(pet);
    }

    //REQUIRES: this can afford pet
    //MODIFIES: this, pet
    //EFFECTS: if this can afford pet, adopt pet and have pet adopt human
    public void adoptPet(Pet pet){
        System.out.println("Adopting a pet!");
        if (!this.equals(pet.getHuman())) {
            spendSpendibees(pet.getPrice());
            this.pets.add(pet);
            pet.adoptHuman(this);
            System.out.println("Success! Adopted " + pet);
        }
    }

    //REQUIRES: pet != null
    //EFFECTS: returns true if this has enough money to buy pet
    public boolean canAffordPet(Pet pet){
        return spendibees >= pet.getPrice();
    }

    //MODIFIES: this
    //EFFECTS: adds spendibees to this account
    public void addSpendibees(double spendibees) {
        this.spendibees += spendibees;
    }

    @Override
    public String toString() {
        return "Human: " + name;
    }


    //REQUIRES: spent >= spendibees
    //MODIFIES: this
    //EFFECTS: reduces spendibees by spent
    private void spendSpendibees(double spent) {
        this.spendibees -= spent;
    }

    //EFFECTS: returns the number of pets belonging to species
    public int numPetsOfSpecies(String species) {
        //TODO 6
        return 0;
    }

}
