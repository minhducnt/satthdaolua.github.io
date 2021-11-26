package Data;

import Model.Product;

import javax.persistence.*;
import java.util.List;

public class productIO {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dhs");


    public void insert (Product product)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(product);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
            em.close();
        }
    }

    public void update (Product product)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dhs");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(product);

            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
            em.close();
        }
    }
    public void delete (Product product)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            em.remove(product);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
            em.close();
        }
    }
    public static Object[] selectProduct(long ID)
    {
        EntityManager em = emf.createEntityManager();

        String q = "Select i from Product i " +
                        "Where i.id = :id";
        TypedQuery<Product> x = em.createQuery(q,Product.class);
        x.setParameter("id",ID);
        try {
            return em.createQuery("SELECT p.id, p.title as productname," +
                            " p.decription as decription" +
                            ",p.price as price ," +
                            "p.shopByShopId.id," +
                            "p.content, ca.amount FROM Product p join p.cartItemsById as ca where p.id =:id",Object[].class).setParameter("id",ID)
                    .getSingleResult();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }finally {
            em.close();
        }

    }
    //xem lại
    public static List<?> selectListProduct()
    {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p.title as productname, " +
                    "p.decription as decription" +
                    ",p.price as price , p.shopByShopId.shopname ,p.id " +
                    "FROM Product p")
                    .getResultList();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }finally {
            em.close();
        }
    }
    public static Product selectProductByid(long idproduct)
    {
        EntityManager em = emf.createEntityManager();
       TypedQuery<Product>  acc;
        try {

             acc = em.createQuery("SELECT p FROM Product p where p.id = :id"
                            ,Product.class);
             acc.setParameter("id",idproduct);


            return acc.getSingleResult();

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }finally {
            em.close();
        }

    }
}
