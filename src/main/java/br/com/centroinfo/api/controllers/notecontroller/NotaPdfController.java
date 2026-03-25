package br.com.centroinfo.api.controllers.notecontroller;

// import java.net.http.HttpHeaders; // Remove this line
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.repository.sale.SaleRepository;
import br.com.centroinfo.api.services.sale.NotaPdfService;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("")
public class NotaPdfController {

    private final SaleRepository saleRepository;
    private final NotaPdfService notaPdfService;

    public NotaPdfController(SaleRepository saleRepository, NotaPdfService notaPdfService) {
        this.saleRepository = saleRepository;
        this.notaPdfService = notaPdfService;
    }

    @GetMapping("/nota/{saleId}/pdf")
    public Object baixarNotaPdf(@PathVariable Long saleId) {
        return saleRepository.findById(saleId)
                .map(sale -> {
                     byte[] pdf = notaPdfService.gerarPdf(sale);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_PDF);
                    headers.setContentDispositionFormData("attachment", "nota_" + saleId + ".pdf");
                    return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}


