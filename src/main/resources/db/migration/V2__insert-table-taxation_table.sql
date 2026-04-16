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
UPDATE taxation_tables SET
    fcp_uf_dest_perc = 2.0,
    cod_benef_fiscal = NULL,
    code_trib_issqn = 'N',

    cofins_aliq = 7.6,
    cofins_base = 100,

    cs_aliq = 9.0,
    cs_base = 100,

    cst_cofins = 1,
    cst_issqn_pref = NULL,
    cst_pis = 1,
    cst_ipi = 50,

    fcp_uf_dest_base = 0,
    fcp_uf_dest_base_st = 0,
    fcp_uf_dest_perc_st = 0,

    icms_aliq = 18,
    icms_aliq_interestadual = 12,
    icms_aliq_st = 0,
    icms_aliq_uf_dest = 18,

    icms_base = 100,
    icms_diferido = 0,
    icms_margem_st = 0,
    icms_mod_bc = 3,

    icms_obs = NULL,
    icms_st_tributado = 0,
    icms_trib = 1,
    icms_usa_margem_st = 0,

    ipi_aliq = 0,
    ipi_unit = 0,

    ir_aliq = 15,
    ir_base = 100,

    iss_aliq = 0,
    iss_base = 0,

    name = 'Tributação de Mercadoria Normal',

    natureza_receita_pis_cofins = 1,

    pis_base = 100,
    sf_base = 100,
    sf_aliq = 0,
    st_mod_bc = 0

WHERE id = 1;

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

    'Prestação de Serviços',

    1,

    100,              -- PIS base
    100,
    0,
    0
);