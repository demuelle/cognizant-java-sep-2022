import java.util.Objects;

public class MenuItem {
    private double price;
    private String description;
    private boolean couponsApply;
    private int sugarGrams = 25;
    private int proteinGrams = 2;
    private int fatGrams = 10;
    private int calorieCount = 198;

    public MenuItem() {
    }

    public MenuItem(double price, String description, boolean couponsApply, int sugarGrams, int proteinGrams, int fatGrams, int calorieCount) {
        this.price = price;
        this.description = description;
        this.couponsApply = couponsApply;
        this.sugarGrams = sugarGrams;
        this.proteinGrams = proteinGrams;
        this.fatGrams = fatGrams;
        this.calorieCount = calorieCount;
    }

    public MenuItem(double price, String description) {
        this.price = price;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCouponsApply() {
        return couponsApply;
    }

    public void setCouponsApply(boolean couponsApply) {
        this.couponsApply = couponsApply;
    }

    public int getSugarGrams() {
        return sugarGrams;
    }

    public void setSugarGrams(int sugarGrams) {
        this.sugarGrams = sugarGrams;
    }

    public int getProteinGrams() {
        return proteinGrams;
    }

    public void setProteinGrams(int proteinGrams) {
        this.proteinGrams = proteinGrams;
    }

    public int getFatGrams() {
        return fatGrams;
    }

    public void setFatGrams(int fatGrams) {
        this.fatGrams = fatGrams;
    }

    public int getCalorieCount() {
        return calorieCount;
    }

    public void setCalorieCount(int calorieCount) {
        this.calorieCount = calorieCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Double.compare(menuItem.price, price) == 0 && couponsApply == menuItem.couponsApply && sugarGrams == menuItem.sugarGrams && proteinGrams == menuItem.proteinGrams && fatGrams == menuItem.fatGrams && calorieCount == menuItem.calorieCount && Objects.equals(description, menuItem.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, description, couponsApply, sugarGrams, proteinGrams, fatGrams, calorieCount);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "price=" + price +
                ", description='" + description + '\'' +
                ", couponsApply=" + couponsApply +
                ", sugarGrams=" + sugarGrams +
                ", proteinGrams=" + proteinGrams +
                ", fatGrams=" + fatGrams +
                ", calorieCount=" + calorieCount +
                '}';
    }
}
