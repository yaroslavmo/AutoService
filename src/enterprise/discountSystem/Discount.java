package enterprise.discountSystem;

public class Discount {
    private String discountName;
    private double discountAmount;


    public Discount(String discountName, int percentage) {
        this.discountName = discountName;
        this.discountAmount = percentage / 100.0d;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}
