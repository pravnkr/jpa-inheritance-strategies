package io.github.pravnkr.jpainheritence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.pravnkr.jpainheritence.models.CompanyInvestor;
import io.github.pravnkr.jpainheritence.models.Investor;

@Repository
public interface InvestorRepository
    extends JpaRepository<Investor, Long>, JpaSpecificationExecutor<Investor> {

  @Query("SELECT i FROM Investor i WHERE TYPE(i) = CompanyInvestor AND i.name LIKE %:namePart%")
  List<CompanyInvestor> findCompanyInvestorsByNamePart(@Param("namePart") String namePart);
}
