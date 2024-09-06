package io.persistence.dao.jpa;

import io.persistence.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class JpaBookDao {

    @PersistenceContext
    protected EntityManager em;

//    @Override
    public List<Book> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        return em.createQuery(criteriaQuery).getResultList();

        // Using JPA
        // return em.createQuery( "from " + modelType.getSimpleName(), modelType).getResultList();
    }

    public Book findById(Integer id) {
        return em.find(Book.class, id);
    }

    public Book saveOrUpdate(Book modelObject) {
        return em.merge(modelObject);
    }

    public void delete(Integer id) {
        em.remove(em.find(Book.class, id));
    }
}
