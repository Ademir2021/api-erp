CREATE TABLE titulos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    empresa_id UUID REFERENCES empresas(id),
    documento_id UUID REFERENCES documentos(id),
    cliente_id UUID REFERENCES clientes(id),
    numero_parcela SMALLINT,
    total_parcelas SMALLINT,
    valor NUMERIC(15,4),
    data_vencimento DATE,
    data_pagamento DATE,
    status SMALLINT, -- 1=ABERTO, 2=PAGO, 3=CANCELADO
    created_at TIMESTAMP DEFAULT now()
);