
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@Data
@AllArgsConstructor
public class Customer {
    private String name;
    private LocalDate dateOfBirth;
    private String numberPhone;
    private Gender gender;

}
