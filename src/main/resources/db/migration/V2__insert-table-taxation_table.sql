INSERT INTO taxation_tables (
    id, fcp_uf_dest_perc, cod_benef_fiscal, code_trib_issqn,
    cofins_aliq, cofins_base, cs_aliq, cs_base,
    cst_cofins, cst_issqn_pref, cst_pis, cst_ipi,
    fcp_uf_dest_base, fcp_uf_dest_base_st, fcp_uf_dest_perc_st,
    icms_aliq, icms_aliq_interestadual, icms_aliq_st, icms_aliq_uf_dest,
    icms_base, icms_diferido, icms_margem_st, icms_mod_bc,
    icms_obs, icms_st_tributado, icms_trib, icms_usa_margem_st,
    ipi_aliq, ipi_unit, ir_aliq, ir_base,
    iss_aliq, iss_base, name, natureza_receita_pis_cofins,
    pis_base, sf_base, sf_aliq, st_mod_bc
)
VALUES (
    1,
    2.0,              -- FCP (0 a 2%)
    NULL,
    'N',              -- Não é ISS

    7.6,              -- COFINS (Lucro Presumido/Real)
    100,

    9.0,              -- CSLL (presumido pode variar)
    100,

    1,                -- CST COFINS (tributado)
    NULL,
    1,                -- CST PIS
    50,               -- CST IPI

    0,
    0,
    0,

    18,               -- ICMS interno (PR normalmente 18%)
    12,               -- Interestadual
    0,                -- ST (se não usar)
    18,

    100,
    0,
    0,
    3,                -- modalidade BC (3 = valor operação)

    NULL,
    0,
    1,
    0,

    0,                -- IPI (depende do produto)
    0,

    15,               -- IRPJ presumido (depende)
    100,

    0,                -- ISS (não é serviço)
    0,

    'Tributação de Mercadoria Normal',

    1,

    100,              -- PIS base
    100,
    0,
    0
);

INSERT INTO taxation_tables (
    id, fcp_uf_dest_perc, cod_benef_fiscal, code_trib_issqn,
    cofins_aliq, cofins_base, cs_aliq, cs_base,
    cst_cofins, cst_issqn_pref, cst_pis, cst_ipi,
    fcp_uf_dest_base, fcp_uf_dest_base_st, fcp_uf_dest_perc_st,
    icms_aliq, icms_aliq_interestadual, icms_aliq_st, icms_aliq_uf_dest,
    icms_base, icms_diferido, icms_margem_st, icms_mod_bc,
    icms_obs, icms_st_tributado, icms_trib, icms_usa_margem_st,
    ipi_aliq, ipi_unit, ir_aliq, ir_base,
    iss_aliq, iss_base, name, natureza_receita_pis_cofins,
    pis_base, sf_base, sf_aliq, st_mod_bc
)
VALUES (
    2,
    2.0,              -- FCP (0 a 2%)
    NULL,
    'N',

    7.6,              -- COFINS
    100,

    9.0,              -- CSLL
    100,

    1,                -- CST COFINS
    NULL,
    1,                -- CST PIS
    50,               -- CST IPI

    0,
    100,              -- base ST
    2.0,              -- FCP ST

    18,               -- ICMS interno (PR)
    12,               -- interestadual
    18,               -- ICMS ST usa interna
    18,

    100,
    0,

    40,               -- MVA (exemplo realista)
    4,                -- 4 = MVA

    NULL,
    1,                -- tem ST
    1,
    1,                -- usa margem

    0,                -- IPI depende produto
    0,

    15,
    100,

    0,                -- ISS não aplica
    0,

    'Tributação de Mercadoria por ST',

    1,

    100,
    100,
    0,
    4                 -- ST com MVA
);

INSERT INTO taxation_tables (
    id, fcp_uf_dest_perc, cod_benef_fiscal, code_trib_issqn,
    cofins_aliq, cofins_base, cs_aliq, cs_base,
    cst_cofins, cst_issqn_pref, cst_pis, cst_ipi,
    fcp_uf_dest_base, fcp_uf_dest_base_st, fcp_uf_dest_perc_st,
    icms_aliq, icms_aliq_interestadual, icms_aliq_st, icms_aliq_uf_dest,
    icms_base, icms_diferido, icms_margem_st, icms_mod_bc,
    icms_obs, icms_st_tributado, icms_trib, icms_usa_margem_st,
    ipi_aliq, ipi_unit, ir_aliq, ir_base,
    iss_aliq, iss_base, name, natureza_receita_pis_cofins,
    pis_base, sf_base, sf_aliq, st_mod_bc
)
VALUES (
    3,
    0,
    NULL,
    'S',              -- Serviço

    3.0,              -- COFINS (Simples ou cumulativo)
    100,

    9.0,              -- CSLL (depende regime)
    100,

    1,
    1,
    1,
    NULL,             -- IPI não aplica

    0,
    0,
    0,

    0,                -- ICMS não aplica
    0,
    0,
    0,

    0,
    0,
    0,
    0,

    NULL,
    0,
    0,
    0,

    0,
    0,

    15,               -- IRPJ (exemplo)
    100,

    5.0,              -- ISS (geralmente 2% a 5%)
    100,

    'Tributação de Serviços',

    1,

    100,              -- PIS base
    100,
    0,
    0
);