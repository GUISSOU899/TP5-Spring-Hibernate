package metier;

import dao.IDao;
import entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implémentation du DAO pour l'entité Product.
 * Cette classe gère toutes les opérations CRUD avec Hibernate.
 */
@Repository       // ✅ Indique à Spring que cette classe est un composant DAO
@Transactional    // ✅ Active la gestion automatique des transactions
public class ProductDaoImpl implements IDao<Product> {

    @Autowired
    private SessionFactory sessionFactory;

    // --- Méthode utilitaire pour obtenir la session Hibernate courante ---
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    // --- CREATE ---
    @Override
    public boolean create(Product product) {
        try {
            getSession().save(product);
            System.out.println("✅ Produit ajouté : " + product);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'ajout du produit : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // --- DELETE ---
    @Override
    public boolean delete(Product product) {
        try {
            getSession().delete(product);
            System.out.println("🗑️ Produit supprimé : " + product);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la suppression du produit : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // --- UPDATE ---
    @Override
    public boolean update(Product product) {
        try {
            getSession().update(product);
            System.out.println("🔄 Produit mis à jour : " + product);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la mise à jour du produit : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // --- READ BY ID ---
    @Override
    public Product findById(int id) {
        try {
            Product product = getSession().get(Product.class, id);
            if (product != null) {
                System.out.println("🔍 Produit trouvé : " + product);
            } else {
                System.out.println("⚠️ Aucun produit trouvé pour l’ID " + id);
            }
            return product;
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la recherche du produit : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // --- READ ALL ---
    @Override
    public List<Product> findAll() {
        try {
            List<Product> products = getSession()
                    .createQuery("from Product", Product.class)
                    .list();
            System.out.println("📦 Produits trouvés : " + products.size());
            return products;
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la récupération des produits : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
