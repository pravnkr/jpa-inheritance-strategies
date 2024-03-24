package io.github.pravnkr.jpainheritence.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("INTERNAL")
@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyInvestor extends Investor {
  private String companyType;

  @Column(unique = true)
  private String lei;
}
