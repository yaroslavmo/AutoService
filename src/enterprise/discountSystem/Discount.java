package enterprise.discountSystem;

public class Discount {
    private String discountName;
    private double amount;


    public Discount(String discountName, int percentage) {
        this.discountName = discountName;
        this.amount = percentage / 100.0d;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
