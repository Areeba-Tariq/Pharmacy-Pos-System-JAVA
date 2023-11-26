package business;
        
import java.util.ArrayList;
import java.util.List;

public class CategoryModel{

    private int sn;
    private String name;
    private String description;
    private List<ProductModel> products;

    public CategoryModel(int sn, String name, String description) {
        this.sn = sn;
        this.name = name;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
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
    
    public List<ProductModel> getProducts() {
        return products;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addProduct(ProductModel product) {
        products.add(product);
    }

    public void removeProduct(ProductModel product) {
        products.remove(product);
    }
       
}
