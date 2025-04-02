package utils;

public class DiscountCalculator {
    public double calculateDiscounst(double value){
        if(value > 1000) {
            return 5;
        }

        return 2;
    }
}
