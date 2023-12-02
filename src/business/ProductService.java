package business;
import data.ProductDAO;
import java.util.List;

public class ProductService {


    
    private ProductDAO pdao;
    
    public ProductService()
    {
        pdao=new ProductDAO();
    }

    public void addProduct(ProductModel p){
        pdao.addProduct(p);
    }

    public void delProduct(ProductModel p){
        pdao.deleteProduct(p);
    }

    public void updateProduct(ProductModel _p, ProductModel p){
       pdao.updateProduct(_p, p);
    }
    
    public List<ProductModel> getProducts(){
        return pdao.getProducts();
    }

}



