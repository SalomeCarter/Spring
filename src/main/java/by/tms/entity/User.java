package by.tms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //    @NotBlank(message = "Not Black")
//    @NotEmpty(message = "Not Empty")
    private String name;

    //    @NotBlank(message = "Not Black")
//    @NotEmpty(message = "Not Empty")
    private String username;

    //    @NotBlank(message = "Not Black")
//    @NotEmpty(message = "Not Empty")
    private String password;


    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
