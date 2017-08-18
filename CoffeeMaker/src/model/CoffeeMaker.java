package model;

public class CoffeeMaker {

    public CoffeeMaker(){
        // TODO: complete the implementation of this method
    }

    // getters
    public int getTimeSinceLastBrew() {
        // TODO: complete the implementation of this method
        return 0;
    }
    public int getCupsRemaining() {
        // TODO: complete the implementation of this method
        return 0;
    }

    // EFFECTS: return true iff there are coffee cups remaining
    public boolean areCupsRemaining() {
        // TODO: complete the implementation of this method
        return false;
    }

    //REQUIRES: a non-negative integer
    //EFFECTS: sets time since last brew
    public void setTimeSinceLastBrew(int time) {
        // TODO: complete the implementation of this method
    }

    //REQUIRES: beans between 2.40 and 2.60 cups, water between 14.9 and 15.1 cups
    //EFFECTS: sets cups remaining to full (20 cups) and time since last brew to 0
    public void brew(double beans, double water){
        // TODO: complete the implementation of this method
    }

    ///REQUIRES: cups remaining > 0, time since last brew < 60
    //MODIFIES: this
    //EFFECTS: subtracts one cup from cups remaining
    public void pourCoffee() {
        // TODO: complete the implementation of this method
    }


}
