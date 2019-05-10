package atmmaster.controller;
public class RangeValidation {
    private int min;
    private int max;

    public RangeValidation(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean isValid(int input) {
        return ((min <= input) && (input <= max));
    }
}