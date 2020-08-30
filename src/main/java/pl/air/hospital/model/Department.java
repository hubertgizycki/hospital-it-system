package pl.air.hospital.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "departments")
@AttributeOverride(column = @Column(name = "dep_id"), name = "id")
@Getter @Setter @NoArgsConstructor @SuperBuilder
public class Department extends BaseEntity{

	@NotBlank
	@Size(max = 50)
	@Column(nullable = false)
	private String name;
	
	@NotBlank
	@Size(max = 100)
	@Column(nullable = false)
	private String location;
	
	@Max(value = 300)
	@Min(value = 0)
	private Long capacity;
}
