package io.services;

import io.persistence.dao.jpa.JpaBookDao;
import io.persistence.model.Book;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl {
    private JpaBookDao bookDao;

    @Autowired
    public void setBookDao(JpaBookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Book get(Integer id) throws NotFoundException {

        Book book = bookDao.findById(id);

        if (book == null) {
            throw new NotFoundException("Book not found");
        }

        return book;
    }

    public List<Book> list() {
        return bookDao.findAll();
    }

    @Transactional
    public Book save(Book book) {
        return bookDao.saveOrUpdate(book);
    }

    @Transactional
    public void delete(Integer id) throws NotFoundException {
        Book book = bookDao.findById(id);

        if (book == null) {
            throw new NotFoundException("Book not found");
        }

        bookDao.delete(id);
    }
}
