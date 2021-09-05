package liv2train.entity;

import liv2train.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * Defining an entity for the Training Centers
 */
@Entity
@Table(name = "TrainingCenters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingCenter {

    @Id
    @Size(min = 12, max = 12)
    @Column(name = "center_code", unique = true)
    private String centerCode;

    @NotBlank
    @Size(max = 40)
    @Column(name = "center_name")
    private String centerName;

    @Embedded
    @Valid
    @NotNull
    private Address address;

    @Column(name = "student_capacity")
    private Integer studentCapacity;

    @ElementCollection
    @CollectionTable(name = "courses", joinColumns = @JoinColumn(name = "center_code"))
    @Column(name = "course_name")
    private List<String> courses;

    @Email
    @Column(name = "contact_email")
    @NotBlank
    private String contactEmail;

    @NotBlank
    @Pattern(regexp = "[0-9]+")
    @Size(min = 10, max = 10)
    @Column(name = "contact_phone")
    private String contactPhone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private Date createdOn;

}
