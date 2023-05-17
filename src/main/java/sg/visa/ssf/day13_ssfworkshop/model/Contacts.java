package sg.visa.ssf.day13_ssfworkshop.model;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Contacts {
    private String id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 64, message = "Name must be between 3 and 64 characters")
    private String name;
    
    @NotEmpty(message = "Email cannot be empty")
    @Email(message="Must be a valid email")
    private String email;

    @Size(min = 8, message = "Phone number is invalid")
    @Pattern(regexp = "[8-9]{1}[0-9][7]")
    private String tel;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth cannot be beyond today")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate dob;

    public Contacts() {
        this.id=generateId();
    }

    public Contacts(String id, String name, String email, String tel, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.dob = dob;
    }

    private String generateId() {
        Random r = new Random();
        String s = new String();
        int i = 0;
        while(i < 8) {
            s = String.join(s, Integer.toHexString(r.nextInt()));
        }

        return s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
