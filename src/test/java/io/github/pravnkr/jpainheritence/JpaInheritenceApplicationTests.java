package io.github.pravnkr.jpainheritence;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import io.github.pravnkr.jpainheritence.models.CoInvestor;
import io.github.pravnkr.jpainheritence.models.CompanyInvestor;
import io.github.pravnkr.jpainheritence.models.FundLP;
import io.github.pravnkr.jpainheritence.models.Investor;
import io.github.pravnkr.jpainheritence.repository.InvestorRepository;
import io.github.pravnkr.jpainheritence.utils.InvestorSpecifications;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@AllArgsConstructor(onConstructor_ = @Autowired)
class JpaInheritenceApplicationTests {

  @Container @ServiceConnection
  static MySQLContainer<?> mysqlContainer =
      new MySQLContainer<>(DockerImageName.parse("mysql:latest"));

  private InvestorRepository investorRepository;

  @Test
  @Transactional
  @Rollback
  void testPolymorphicQueryForInvestors() {
    CompanyInvestor companyInvestor = new CompanyInvestor();
    companyInvestor.setName("123 Investments Ltd.");
    companyInvestor.setCompanyType("Limited Liability Partnership");
    companyInvestor.setLei("213800KKDOEODF9DYR26");

    FundLP fundLP = new FundLP();
    fundLP.setName("XYZ Endowment Fund");
    fundLP.setLpType("Endowment Fund");

    CoInvestor coInvestor = new CoInvestor();
    coInvestor.setName("XYZ Capital Partners");
    coInvestor.setCoInvestorType("Private Equity Firm");

    investorRepository.saveAll(List.of(companyInvestor, fundLP, coInvestor));

    List<Investor> investors = investorRepository.findAll();

    assertThat(investors)
        .hasSize(3)
        .contains(companyInvestor, fundLP, coInvestor)
        .hasAtLeastOneElementOfType(CompanyInvestor.class)
        .hasAtLeastOneElementOfType(FundLP.class)
        .hasAtLeastOneElementOfType(CoInvestor.class);
  }

  @Test
  @Transactional
  @Rollback
  void testFindCompanyInvestorsByNamePart() {
    CompanyInvestor companyInvestor1 = new CompanyInvestor();
    companyInvestor1.setName("Acme Inc.");
    companyInvestor1.setCompanyType("Private Limited Company");
    companyInvestor1.setLei("5493006MHB84DD0ZWV18");

    CompanyInvestor companyInvestor2 = new CompanyInvestor();
    companyInvestor2.setName("XYZ Corporation");
    companyInvestor2.setCompanyType("Public Limited Company");
    companyInvestor2.setLei("635400HHWFDIQVP8LM87");

    FundLP fundLP = new FundLP();
    fundLP.setName("ABC Pension Fund");
    fundLP.setLpType("Pension Fund");

    investorRepository.saveAll(List.of(companyInvestor1, companyInvestor2, fundLP));

    List<CompanyInvestor> companyInvestors =
        investorRepository.findCompanyInvestorsByNamePart("Inc");

    assertThat(companyInvestors).hasSize(1).contains(companyInvestor1);
  }

  @Test
  @Transactional
  @Rollback
  void testQueryToFetchSpecificInvestorTypeWithInvestorJpaSpecification() {
    CompanyInvestor companyInvestor1 = new CompanyInvestor();
    companyInvestor1.setName("Acme Inc.");
    companyInvestor1.setCompanyType("Private Limited Company");
    companyInvestor1.setLei("5493006MHB84DD0ZWV18");

    CompanyInvestor companyInvestor2 = new CompanyInvestor();
    companyInvestor2.setName("XYZ Corporation");
    companyInvestor2.setCompanyType("Public Limited Company");
    companyInvestor2.setLei("635400HHWFDIQVP8LM87");

    FundLP fundLP = new FundLP();
    fundLP.setName("ABC Pension Fund");
    fundLP.setLpType("Pension Fund");

    CoInvestor coInvestor = new CoInvestor();
    coInvestor.setName("XYZ Capital Partners");
    coInvestor.setCoInvestorType("Private Equity Firm");

    investorRepository.saveAll(List.of(companyInvestor1, companyInvestor2, fundLP, coInvestor));

    List<Investor> investors =
        investorRepository.findAll(InvestorSpecifications.hasType(CompanyInvestor.class));

    assertThat(investors)
        .hasSize(2)
        .containsExactlyInAnyOrder(companyInvestor1, companyInvestor2)
        .allMatch(investor -> investor instanceof CompanyInvestor);

    investors = investorRepository.findAll(InvestorSpecifications.hasType(FundLP.class));

    assertThat(investors)
        .hasSize(1)
        .contains(fundLP)
        .allMatch(investor -> investor instanceof FundLP);

    investors = investorRepository.findAll(InvestorSpecifications.hasType(CoInvestor.class));

    assertThat(investors)
        .hasSize(1)
        .contains(coInvestor)
        .allMatch(investor -> investor instanceof CoInvestor);
  }
}
