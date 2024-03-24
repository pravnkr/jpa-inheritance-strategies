package io.github.pravnkr.jpainheritence.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("CO_INVESTOR")
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class CoInvestor extends Investor {
  private String coInvestorType;
}
