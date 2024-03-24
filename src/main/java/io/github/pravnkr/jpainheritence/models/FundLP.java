package io.github.pravnkr.jpainheritence.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("LP")
@Data
@EqualsAndHashCode(callSuper = true)
public class FundLP extends Investor {
  private String lpType;
}
