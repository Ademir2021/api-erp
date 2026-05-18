package br.com.centroinfo.api.controllers.notecontroller;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.repository.sale.SaleRepository;
import br.com.centroinfo.api.services.sale.NotaCupomService;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/cupom")
public class NotaCupomController {

    private final  SaleRepository saleRepository;
    private final  NotaCupomService notaCupomService;

    public NotaCupomController(SaleRepository saleRepository, NotaCupomService notaCupomService) {
        this.saleRepository = saleRepository;
        this.notaCupomService = notaCupomService;
    }

    @GetMapping("/{saleId}/pdf")
    public Object baixarNotaPdf(@PathVariable Long saleId) {
        return saleRepository.findById(saleId)
                .map(sale -> {
                    byte[] pdf = notaCupomService.gerarCupom(sale);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_PDF);
                    headers.setContentDispositionFormData("attachment", "cupom_" + saleId + ".pdf");
                    return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
