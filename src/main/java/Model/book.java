package Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class book  {
    private int id;
    @NotEmpty(message = "Book name is not should be empty!")
    private String nomination;
    @NotEmpty(message = "Author name is not should be empty!")
    @Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+\\s[A-Z][a-z]+$", message = "Author name is should be: Last name Name Surname (capitalized)")
    private String author;
    @NotEmpty(message = "Date is not should be empty!")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date is should be in format like : YYYY-MM-DD")
    private String year;
    private int personId;

    public book() {
    }

    public book(int id, String nomination, String author, String year, int personId) {
        this.id = id;
        this.nomination = nomination;
        this.author = author;
        this.year = year;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Book name is not should be empty!") String getNomination() {
        return nomination;
    }

    public void setNomination(@NotEmpty(message = "Book name is not should be empty!") String nomination) {
        this.nomination = nomination;
    }

    public @NotEmpty(message = "Author name is not should be empty!") @Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+\\s[A-Z][a-z]+$", message = "Author name is should be: Last name Name Surname (capitalized)") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotEmpty(message = "Author name is not should be empty!") @Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+\\s[A-Z][a-z]+$", message = "Author name is should be: Last name Name Surname (capitalized)") String author) {
        this.author = author;
    }

    public @NotEmpty(message = "Date is not should be empty!") @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date is should be in format like : YYYY-MM-DD") String getYear() {
        return year;
    }

    public void setYear(@NotEmpty(message = "Date is not should be empty!") @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date is should be in format like : YYYY-MM-DD") String year) {
        this.year = year;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}