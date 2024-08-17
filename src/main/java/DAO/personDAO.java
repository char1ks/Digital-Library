package DAO;

import Model.person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class personDAO {
    public JdbcTemplate operations;
    @Autowired
    public void setOperations(JdbcTemplate operations) {
        this.operations = operations;
    }
    public List<person>getAllPerson(){
        return operations.query("SELECT * FROM people", (resultSet, i) -> {
            person per=new person();
            per.setId(resultSet.getInt("id"));
            per.setBirthDate(resultSet.getString("birthdate"));
            per.setFullName(resultSet.getString("fullname"));
            return per;
        });
    }
    public person getConcretPerson(int id) {
        return operations.queryForObject("SELECT * FROM people WHERE id = ?", new Object[]{id}, (resultSet, i) -> {
            person per = new person();
            per.setId(resultSet.getInt("id"));
            per.setBirthDate(resultSet.getString("birthdate"));
            per.setFullName(resultSet.getString("fullname"));
            return per;
        }); // Возвращаем первый элемент или null, если не найден
    }
    public void addPerson(person person){
        operations.update("INSERT INTO people(fullname,birthdate) VALUES(?,?)",person.getFullName(),person.getBirthDate());
    }
    public void editPerson(int id,person person){
        operations.update("UPDATE people SET fullname = ?,birthdate = ? WHERE id = ?",person.getFullName(),person.getBirthDate(),id);
    }
    public void deleteInDB(int id){
        operations.update("DELETE FROM people WHERE id = ?",id);
    }
}
