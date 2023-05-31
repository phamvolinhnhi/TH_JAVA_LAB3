package phamvolinhnhi.week3.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import phamvolinhnhi.week3.entity.Category;
import phamvolinhnhi.week3.services.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllCategories(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/list";
    }
    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }
    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if(result.hasErrors()) {
            return "category/add";
        }
        else {
            categoryService.addCategory(category);
            return "redirect:/categories";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        Category editCategory = categoryService.getCategoryById(id);
        if(editCategory == null){
            return "not-found";
        }
        else{
            model.addAttribute("category", editCategory);
            return "category/edit";
        }

    }
    @PostMapping("/edit")
    public String editCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if(result.hasErrors()){
            return "category/edit";
        }
        else {
            categoryService.updateCategory(category);
            return "redirect:/categories";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        Category category = categoryService.getCategoryById(id);
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

}
