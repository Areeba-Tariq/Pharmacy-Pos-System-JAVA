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
    private String category;
    private String description;
    private List<CategoryModel> categories;
    private int validity;

    public ProductModel(int sn,  int price, int quantity, int validity, String name, String description) {
        this.sn = sn;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.validity = validity;
        this.categories = new ArrayList<>();
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
    
    public void setCategory(String category) {
        this.category = category;
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
    
    public List<String> getCategories() {
        List<String> categoryNames = new ArrayList<>();
        for (CategoryModel category : categories) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }

    public void addCategory(CategoryModel category) {
        categories.add(category);
    }
 
}