package com.mastercode.personalfinancialsystem.domain;

import com.mastercode.personalfinancialsystem.domain.auditing.AuditableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Expense extends AuditableEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String description;

	@NotNull
	private BigDecimal value;

	@ManyToOne(optional = false)
	private User creator;

	@ManyToOne(optional = true)
	private User debtor;

	@NotNull
	private LocalDateTime date;

	private boolean payed = false;

	public Expense(String description, BigDecimal value, User creator, User debtor) {
		this.description = description;
		this.value = value;
		this.creator = creator;
		this.debtor = debtor;
		this.date = LocalDateTime.now();
	}

}
