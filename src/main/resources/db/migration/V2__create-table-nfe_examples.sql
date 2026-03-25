
CREATE TABLE nfe (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    documento_id UUID UNIQUE REFERENCES documentos(id),
    chave_acesso VARCHAR(44),
    numero INTEGER,
    serie INTEGER,
    protocolo TEXT,
    xml TEXT,
    status SMALLINT,
    data_emissao TIMESTAMP
);
