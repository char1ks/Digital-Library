package Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class person {
    private int id;
    @NotEmpty(message = "Name is not should be empty!")
    @Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+\\s[A-Z][a-z]+$", message = "Full name is should be: Last name Name Surname (capitalized)")
    private String FullName;
    @NotEmpty(message = "Birth date is not should be empty!")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date is should be in format like : YYYY-MM-DD")
    private String  birthDate;

    public person() {
    }

    public person(int id, String fullName, String birthDate) {
        this.id = id;
        FullName = fullName;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name is not should be empty!") @Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+\\s[A-Z][a-z]+$", message = "Full name is should be: Last name Name Surname (capitalized)") String getFullName() {
        return FullName;
    }

    public void setFullName(@NotEmpty(message = "Name is not should be empty!") @Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+\\s[A-Z][a-z]+$", message = "Full name is should be: Last name Name Surname (capitalized)") String fullName) {
        FullName = fullName;
    }

    public @NotEmpty(message = "Birth date is not should be empty!") @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date is should be in format like : YYYY-MM-DD") String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NotEmpty(message = "Birth date is not should be empty!") @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date is should be in format like : YYYY-MM-DD") String birthDate) {
        this.birthDate = birthDate;
    }
}
