package pl.air.hospital.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "nurses")
@AttributeOverride(column = @Column(name = "nur_id"), name = "id")
@Getter @Setter @NoArgsConstructor @SuperBuilder
public class Nurse extends BaseEntity {

	@NotBlank
	@Size(max = 30)
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotBlank
	@Size(max = 100)
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotNull
	@Column(name = "hire_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hireDate;
	
	@NotNull
	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal salary;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "dep_id")
    private Department department;
}
