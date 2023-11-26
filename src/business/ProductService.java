package business;
import data.ProductDAO;
import data.CategoryDAO;
import java.util.List;
import business.CategoryModel;


public class ProductService {
    
    private ProductDAO pdao;
    private CategoryDAO categoryDAO; // Assuming you have a CategoryDAO

    public ProductService() {
        pdao = new ProductDAO();
        categoryDAO = new CategoryDAO(); // Initialize CategoryDAO
    }

    public void addProduct(ProductModel p) {
        pdao.addProduct(p);
        for (String categoryName : p.getCategories()) {
            CategoryModel category = categoryDAO.getCategoryByName(categoryName);
            if (category != null) {
                pdao.addProductToCategory(p, category);
            }
        }
    }

    public void delProduct(ProductModel p){
        pdao.deleteProduct(p);
    }

    public void updateProduct(ProductModel p){
       pdao.updateProduct(p);
    }
    
    public List<ProductModel> getProducts(){
        return pdao.getProducts();
    }
 
}



