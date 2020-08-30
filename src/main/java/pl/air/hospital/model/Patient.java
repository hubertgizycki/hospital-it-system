package pl.air.hospital.model;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "patients")
@AttributeOverride(column = @Column(name = "pat_id"), name = "id")
@Getter @Setter @NoArgsConstructor @SuperBuilder
public class Patient extends BaseEntity {

	@NotBlank
	@Size(max = 30)
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotBlank
	@Size(max = 100)
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotBlank
	@Column(nullable = false)
	@Pattern(regexp = "(?:kobieta|mężczyzna)")
	private String sex;
	
	@NotNull
	@Column(nullable = false)
	@Min(value = 0)
	@Max(value = 120)
	private Integer age;
	
	@NotNull
	@Column(nullable = false)
	@Size(max = 300)
	private String sickness;
	
	@NotNull
	@Column(name = "admission_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate admissionDate;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "doc_id")
	private Doctor doctor;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "dep_id")
	private Department department;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "nur_id")
	private Nurse nurse;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
}
