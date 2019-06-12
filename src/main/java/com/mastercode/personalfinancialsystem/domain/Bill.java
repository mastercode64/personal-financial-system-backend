package com.mastercode.personalfinancialsystem.domain;

import com.mastercode.personalfinancialsystem.domain.auditing.AuditableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Bill extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal value;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @ManyToOne(optional = false)
    private BillCategory category;
}
