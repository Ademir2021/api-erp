package br.com.centroinfo.api.services.sale;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

// import br.com.centroinfo.api.entities.address.address.Address;
import br.com.centroinfo.api.entities.sales.ItemSale;
import br.com.centroinfo.api.entities.sales.Sale;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
// import java.util.List;

@Service
public class NotaPdfService {

    public byte[] gerarPdf(Sale sale) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Logo da empresa
            // Image logo = Image.getInstance(new
            // ClassPathResource("static/logo.png").getURL());
            // logo.scaleToFit(120, 120);
            // logo.setAlignment(Element.ALIGN_LEFT);
            // document.add(logo);

            // Título
            Paragraph title = new Paragraph("Nota de Venda", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Número da Venda: " + sale.getId()));
            document.add(new Paragraph(
                    "Data de Emissão: " + sale.getIssueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
            document.add(new Paragraph("Filial: " + sale.getBranch().getName()));
            document.add(new Paragraph("Telefone: " + sale.getBranch().getPhoneNumber()));
            document.add(new Paragraph("Vendedor: " + sale.getUser().getUsername()));
            document.add(new Paragraph("========== Dados do Cliente =========="));
            document.add(new Paragraph("Cliente: " + sale.getPerson().getName()));
            document.add(new Paragraph("CPF: " + sale.getPerson().getCpf()));
            document.add(new Paragraph("Endereço(s) do Cliente: "));
            // List<Address> addresses = sale.getPerson().getAddress().getPerson().getId()
            // for (Address address : addresses) {
            //     document.add(new Paragraph("Endereço: " + address.getStreet()));
            //     document.add(new Paragraph("Num: " + address.getNumber()));
            //     document.add(new Paragraph("Bairro: " + address.getNeighborhood()));
            //     document.add(new Paragraph("Cidade: " + address.getZipCode().getCity().getName()));
            //     document.add(new Paragraph("CEP: " + address.getZipCode().getCode()));
            //     document.add(new Paragraph(" "));
            // }

            // Tabela de Itens
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            Stream.of("Produto", "Quantidade", "Unitário", "Total")
                    .forEach(header -> {
                        PdfPCell cell = new PdfPCell(new Phrase(header));
                        cell.setBackgroundColor(Color.LIGHT_GRAY);
                        table.addCell(cell);
                    });

            for (ItemSale item : sale.getItemsSale()) {
                table.addCell(item.getItem().getName());
                table.addCell(String.valueOf(item.getAmount()));
                table.addCell(String.format("R$ %.2f", item.getPrice()));
                double total = item.getAmount() * item.getPrice();
                table.addCell(String.format("R$ %.2f", total));
            }

            document.add(table);

            document.add(new Paragraph("Desconto: R$ " + String.format("%.2f", sale.getDiscount())));
            document.add(new Paragraph("Total da Nota: R$ " + String.format("%.2f", sale.getTotalNote())));
            document.add(new Paragraph(" "));

            // Assinatura fictícia
            document.add(new Paragraph("______________________________"));
            document.add(new Paragraph("Assinatura do Vendedor"));

            document.add(new Paragraph(" "));

            // QR Code com link da nota (exemplo)
            String notaUrl = "http://localhost/notas/" + sale.getId(); // exemplo
            Image qrCodeImage = gerarQRCode(notaUrl, 120, 120);
            if (qrCodeImage != null) {
                qrCodeImage.setAlignment(Element.ALIGN_RIGHT);
                document.add(qrCodeImage);
            }

            // Rodapé
            Paragraph rodape = new Paragraph(
                    "Empresa: " + sale.getBranch().getName()
                            + " - CNPJ: 00.000.000/0001-00 | www.empresa.com | (11) 99999-9999",
                    FontFactory.getFont(FontFactory.HELVETICA, 9));
            rodape.setAlignment(Element.ALIGN_CENTER);
            document.add(rodape);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    private Image gerarQRCode(String text, int width, int height) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
            return Image.getInstance(image, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
