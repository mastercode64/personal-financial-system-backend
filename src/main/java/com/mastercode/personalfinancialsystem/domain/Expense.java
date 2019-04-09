package com.mastercode.personalfinancialsystem.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.mastercode.personalfinancialsystem.domain.auditing.AuditableEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	public Expense(String description, BigDecimal value, User creator, User debtor) {
		this.description = description;
		this.value = value;
		this.creator = creator;
		this.debtor = debtor;
		this.date = LocalDateTime.now();
	}

}
