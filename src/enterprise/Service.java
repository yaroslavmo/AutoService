package enterprise;

public class Service {
    private String serviceName;
    private double price;
    private Category serviceCategory;

    public Service(String serviceName, double price, Category serviceCategory) {
        this.serviceName = serviceName;
        this.price = price;
        this.serviceCategory = serviceCategory;
    }

    public Service(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Category getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(Category serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceName='" + serviceName + '\'' +
                ", price=" + price +
                ", serviceCategory=" + serviceCategory.getName() +
                '}';
    }
}
