package br.com.centroinfo.api.entities.items.taxationTable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "taxation_tables")
@Entity
@Getter
@Setter
@NoArgsConstructor

public class TaxationTable { // Tabela de Tributação
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(name = "icms_trib")
  private Integer icmsTrib = 90;
  @Column(name = "icms_base")
  private double icmsBase = 0.000;
  @Column(name = "icms_aliq")
  private double icmsAliq = 0.000;
  @Column(name = "icms_obs")
  private String icmsObs;
  @Column(name = "iss_base")
  private double issBase = 0.000;
  @Column(name = "iss_aliq")
  private double issAliq = 0.000;
  @Column(name = "sf_base")
  private double sfBase = 100.00;
  @Column(name = "sf_aliq")
  private double sf_aliq = 5.400;
  @Column(name = "ir_base")
  private double ir_base = 0.000;
  @Column(name = "ir_aliq")
  private double irAliq = 0.000;
  @Column(name = "cs_base")
  private double csBase = 0.000;
  @Column(name = "cs_aliq")
  private double csAliq = 0.000;
  @Column(name = "pis_base")
  private double pisBase = 0.000;
  @Column(name = "cofins_base")
  private double cofinsBase = 0.000;
  @Column(name = "cofins_aliq")
  private double cofinsAliq = 0.000;
  @Column(name = "ipi_aliq")
  private double ipiAliq = 0.000;
  @Column(name = "cst_pis")
  private Integer cstPis = 99;
  @Column(name = "cst_cofins")
  private Integer cstCofins = 99;
  @Column(name = "cst_ipi")
  private Integer cst_ipi = 99;
  @Column(name = "icms_st_tributado")
  private double icmsStTributado = 0.000;
  @Column(name = "icms_aliq_st")
  private Double icmsAliqSt = 0.000;
  @Column(name = "icms_margem_st")
  private double icmsMargemSt = 0.000;
  @Column(name = "icms_usa_margem_st")
  private char icmsUsaMargemSt = 'N';
  @Column(name = "icms_mod_bc")
  private Integer icmsModBc = 3;
  @Column(name = "st_mod_bc")
  private Integer stModBc = 4;
  @Column(name = "icms_diferido")
  private Double icmsDiferido = 0.000;
  @Column(name = "code_trib_issqn")
  private char codTribIssqn = '1';
  @Column(name = "cst_issqn_pref")
  private Integer cstIssqnPref = 0;
  @Column(name = "ipi_unit")
  private char ipiUnit = 'N';
  @Column(name = "icms_aliq_uf_dest")
  private Double icmsAliqUfDest = 0.000;
  @Column(name = "icms_aliq_interestadual")
  private Double icmsAliqInterestadual = 0.000;
  @Column(name = "fcp_uf_dest_perc")
  private Double cfcpUfDestPerc = 0.000;
  @Column(name = "fcp_uf_dest_base")
  private Double fcpUfDestBase = 0.000;
  @Column(name = "fcp_uf_dest_base_st")
  private Double fcpUfDestBaseSt = 0.000;
  @Column(name = "fcp_uf_dest_perc_st")
  private Double fcpUfDestPercSt = 0.000;
  @Column(name = "cod_benef_fiscal")
  private Integer codBenefFiscal = 1;
  @Column(name = "natureza_receita_pis_cofins")
  private Integer naturezaReceitaPisCofins = 0;
}

