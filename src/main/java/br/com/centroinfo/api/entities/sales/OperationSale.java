package br.com.centroinfo.api.entities.sales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "operation_sale")
public class OperationSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(name = "type")
    private String type; // Ex: "Entrada", "Saida"

    @Column(name = "controls_stock")
    private Boolean controlsStock; // movimenta estoque?

    @Column(name = "generate_financial")
    private Boolean generateFinancial; // gera contas a receber/pagar?

    @Column(name = "allow_discount")
    private Boolean allowDiscount; // permite desconto?

    @Column(name = "update_cost")
    private Boolean updateCost; // atualiza custo do produto?

    @Column(name = "is_final_consumer")
    private Boolean finalConsumer; // consumidor final?

    @Column(name = "requires_invoice") // requer fatura
    private Boolean requiresInvoice; // exige nota fiscal?

    @Column(name = "is_return")
    private Boolean isReturn; // devolução?

    @Column(name = "cfop")
    private String cfop;

    @Column(name = "default_nature")
    private String defaultNature;

    @Column(name = "active")
    private Boolean active;

}