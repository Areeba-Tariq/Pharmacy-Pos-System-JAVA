package business;
        
import java.util.ArrayList;
import java.util.List;

public class CategoryModel{

    private int sn;
    private String name;
    private String description;

    public CategoryModel(int sn, String name, String description) {
        this.sn = sn;
        this.name = name;
        this.description = description;
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
    
    public void setDescription(String description) {
        this.description = description;
    }
    
}
