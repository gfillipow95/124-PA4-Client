public class Product {
    private int id;
    private int productId;
    private String productName;
    private double price;
    private String pic;
    private String description;



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getProductId() {return productId;}

    public void setProductId(int productId) {this.productId = productId;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
}