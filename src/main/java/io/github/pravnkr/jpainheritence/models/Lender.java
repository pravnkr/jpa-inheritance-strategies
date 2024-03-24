package io.github.pravnkr.jpainheritence.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("LENDER")
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class Lender extends Investor {
  private String lenderType;
}
