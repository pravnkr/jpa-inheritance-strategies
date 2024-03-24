package io.github.pravnkr.jpainheritence.utils;

import org.springframework.data.jpa.domain.Specification;

import io.github.pravnkr.jpainheritence.models.Investor;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InvestorSpecifications {
  public static Specification<Investor> hasType(Class<? extends Investor> type) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.type(), type);
  }
}
