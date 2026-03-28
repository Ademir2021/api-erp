# Explicação dos principais campos
🔸 Estoque
controlsStock
👉 Define se a operação movimenta estoque
Ex:
Venda normal → true
Orçamento → false

updateCost
👉 Atualiza custo médio do produto (mais usado em entrada)
🔸 Financeiro

generateFinancial
👉 Cria contas a receber/pagar
Ex:
Venda a prazo → true
Bonificação → false
🔸 Regras comerciais

allowDiscount
👉 Permite desconto na venda

finalConsumer
👉 Indica se é consumidor final (impacta imposto)
🔸 Fiscal (Brasil 👀)

requiresInvoice
👉 Se precisa gerar NF-e/NFC-e
cfop
👉 Código Fiscal de Operações (ex: 5102)

defaultNature
👉 Natureza da operação (ex: "Venda de mercadoria")

🔸 Casos especiais
isReturn
👉 Se é devolução (pode inverter estoque/financeiro)

🧩 Exemplos práticos:

🛒 Venda normal
controlsStock = true
generateFinancial = true
requiresInvoice = true
isReturn = false

📄 Orçamento
controlsStock = false
generateFinancial = false
requiresInvoice = false

🔄 Devolução
controlsStock = true
generateFinancial = true
isReturn = true
⚠️ Dica importante (arquitetura)

Evite colocar regra diretamente na Sale.
Use a OperationSale como configuração de comportamento, por exemplo:

if (sale.getOperationSale().getControlsStock()) {
    stockService.update(...);
}
💥 Se quiser evoluir ainda mais

Você pode depois adicionar:

Tipo da operação como ENUM (ENTRY, EXIT)
Integração com:
ICMS / IPI
Tabela de CFOP separada
Regras por filial (Branch)
Perfis de operação (ex: "PDV", "E-commerce")

## Exemplo de sql:
`insert into operation_sale values (1, true, true, '5102', true, 'Venda de Mercadoria', 'Venda Normal', true, true, false, true, 'saida', true);`