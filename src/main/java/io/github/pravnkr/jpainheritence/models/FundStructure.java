package io.github.pravnkr.jpainheritence.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class FundStructure extends AbstractEntity {
  private String name;

  @ManyToMany
  @JoinTable(
      name = "fund_structure_investor",
      joinColumns = @JoinColumn(name = "fund_structure_id"),
      inverseJoinColumns = @JoinColumn(name = "investor_id"))
  private Set<Investor> investors = new HashSet<>();
}
