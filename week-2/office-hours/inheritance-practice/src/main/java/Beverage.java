public class Beverage extends MenuItem {

    public Beverage(double price, String description) {
        super(price, description);
        this.setCalorieCount(0);
    }
}
