package io.github.pravnkr.jpainheritence.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("INTERNAL")
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class CompanyInvestor extends Investor {
  private String companyType;

  @Column(nullable = false, unique = true)
  private String lei;
}
