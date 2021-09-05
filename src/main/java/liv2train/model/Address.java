package liv2train.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

/**
 * A general Address model which can be embedded in other database entities
 */
@Embeddable
@Data
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "detailedAddress", column = @Column(name = "detailed_address")),
        @AttributeOverride(name = "pinCode", column = @Column(name = "pin_code"))
})
public class Address {

    @NotBlank
    private String detailedAddress;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String pinCode;

}
