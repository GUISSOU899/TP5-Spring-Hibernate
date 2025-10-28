package metier;

import dao.ICategoryDao;
import entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryDaoImpl implements ICategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Category category) {
        getSession().save(category);
    }

    @Override
    public Category get(int id) {
        return getSession().get(Category.class, id);
    }

    @Override
    public List<Category> getAll() {
        return getSession().createQuery("from Category", Category.class).list();
    }

    @Override
    public void delete(int id) {
        Category c = get(id);
        if (c != null) {
            getSession().delete(c);
        }
    }
}
