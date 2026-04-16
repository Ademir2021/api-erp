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
