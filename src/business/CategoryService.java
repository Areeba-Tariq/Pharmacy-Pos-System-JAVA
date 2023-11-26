package business;
import data.CategoryDAO;
import java.util.List;


public class CategoryService {
    
    private CategoryDAO cdao;
    
    public CategoryService()
    {
        cdao=new CategoryDAO();
    }

    public void addCategory(CategoryModel c){
        cdao.addCategory(c);
    }

    public void delCategory(CategoryModel c){
        cdao.deleteCategory(c);
    }

    public List<CategoryModel> getCategories(){
        return cdao.getCategories();
    }
    
}
