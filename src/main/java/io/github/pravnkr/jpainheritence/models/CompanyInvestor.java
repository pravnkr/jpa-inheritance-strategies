package io.github.pravnkr.jpainheritence.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyInvestor extends Investor {
  private String companyType;

  @Column(nullable = false, unique = true)
  private String lei;
}
