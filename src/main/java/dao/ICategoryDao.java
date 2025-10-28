package dao;

import entities.Category;
import java.util.List;

public interface ICategoryDao {
    void add(Category category);
    Category get(int id);
    List<Category> getAll();
    void delete(int id);
}
