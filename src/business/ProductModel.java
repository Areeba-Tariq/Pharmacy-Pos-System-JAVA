package business;

import java.util.ArrayList;
import java.util.List;
import ui.Category;
import business.CategoryModel;

public class ProductModel {
    
    private int sn;
    private int price;
    private int quantity;
    private String name;
    private String description;
    private int validity;
    private List<CategoryModel> categories;

    public ProductModel(int sn,  String name, String description, int price, int quantity, int validity) {
        this.sn = sn;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.validity = validity;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }
    
    public void setValidity(int validity){
        this.validity = validity;
    }
    
    public int getValidity() {
        return validity;
    }
    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }

    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    // Method to get categories associated with the product
    public List<CategoryModel> getCategories() {
        return this.categories;
    }

    // Method to set categories for the product
    public void setCategories(List<CategoryModel> categories) {
        this.categories = categories;
    }
 
}