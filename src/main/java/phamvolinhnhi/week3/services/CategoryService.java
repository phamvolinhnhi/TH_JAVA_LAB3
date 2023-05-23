package phamvolinhnhi.week3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phamvolinhnhi.week3.entity.Category;
import phamvolinhnhi.week3.repository.ICategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository CategoryRepository;
    public List<Category> getAllCategorys(){
        return CategoryRepository.findAll();
    }
    public Category getCategoryById(Long id){
        return CategoryRepository.findById(id).orElse(null);
    }
    public void addCategory(Category category){
        CategoryRepository.save(category);
    }
    public void deleteCategory(Long id){
        CategoryRepository.deleteById(id);
    }
    public void updateCategory(Category category){
        CategoryRepository.save(category);
    }
}
