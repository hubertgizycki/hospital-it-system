package pl.air.hospital.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "rooms")
@AttributeOverride(column = @Column(name = "room_id"), name = "id")
@Getter @Setter @NoArgsConstructor @SuperBuilder
public class Room extends BaseEntity{

	@NotNull
	@Max(value = 999)
	@Min(value = 0)
	@Column(nullable = false)
	private Long number;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "dep_id")
	private Department department;
}
