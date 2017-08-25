package model.random;

public class NumberSquare extends RandomNumber {

    private boolean stamped;

    public NumberSquare() {
        super();
    }

    // getters
    public boolean isStamped() { return stamped; }

    public void stamp() { stamped = true; }


}