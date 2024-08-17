package DAO;

import Model.book;
import Model.person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class bookDAO {
    public JdbcTemplate operations;
    @Autowired
    public void setOperations(JdbcTemplate operations) {
        this.operations = operations;
    }
    public List<book> getAllBooks(){
        return operations.query("SELECT * FROM books WHERE person_id IS NULL", (resultSet, i) -> {
            book bo=new book();
            bo.setId(resultSet.getInt("id"));
            bo.setAuthor(resultSet.getString("author"));
            bo.setNomination(resultSet.getString("nomination"));
            bo.setPersonId(resultSet.getInt("person_id"));
            bo.setYear(resultSet.getString("year"));
            return bo;
        });
    }
    public List<book> getAllBooksWhereId(int id){
        return operations.query("SELECT * FROM books WHERE person_id =?",new Object[]{id},(resultSet, i) -> {
            book bo=new book();
            bo.setId(resultSet.getInt("id"));
            bo.setAuthor(resultSet.getString("author"));
            bo.setNomination(resultSet.getString("nomination"));
            bo.setPersonId(resultSet.getInt("person_id"));
            bo.setYear(resultSet.getString("year"));
            return bo;
        });
    }
    public book getConcretBook(int id){
        return operations.queryForObject("SELECT * FROM books WHERE id=?",  new Object[]{id}, (resultSet, i) -> {
            book bo=new book();
            bo.setId(resultSet.getInt("id"));
            bo.setAuthor(resultSet.getString("author"));
            bo.setNomination(resultSet.getString("nomination"));
            bo.setPersonId(resultSet.getInt("person_id"));
            bo.setYear(resultSet.getString("year"));
            return bo;
        });
    }
    public void setPersonIdTo(int personIdTo, int id) {
        operations.update("UPDATE books SET person_id = ? WHERE id = ?", personIdTo, id);
    }
    public void addBook(book book){
        operations.update("INSERT INTO books(author,nomination,year) VALUES(?,?,?)",book.getAuthor(),book.getNomination(),book.getYear());
    }
    public void editInDB(int id, book book) {
        operations.update("UPDATE books SET author = ?, nomination = ?, year = ? WHERE id = ?",
                book.getAuthor(), book.getNomination(), book.getYear(), id);
    }
    public void delete(int id){
        operations.update("DELETE FROM books WHERE id=?",id);
    }
    public void setPerconIdNULL(int bookId){
        operations.update("UPDATE books SET person_id = NULL WHERE id = ?",bookId);
    }
}
