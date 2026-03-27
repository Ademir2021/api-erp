package br.com.centroinfo.api.services.sale;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import br.com.centroinfo.api.entities.address.address.Address;
import br.com.centroinfo.api.entities.sales.ItemSale;
import br.com.centroinfo.api.entities.sales.Sale;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class NotaPdfService {

    private PdfPCell cell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        return cell;
    }

    private PdfPCell rightCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }

    private PdfPCell centerCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    public byte[] gerarPdf(Sale sale) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document(PageSize.A4, 30, 30, 30, 30);
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // FONTES
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 9);

            // ================= HEADER =================
            PdfPTable header = new PdfPTable(new float[] { 1, 3, 2 });
            header.setWidthPercentage(100);

            // LOGO
            PdfPCell logoCell = new PdfPCell();
            logoCell.setBorder(Rectangle.BOX);
            // Image logo = Image.getInstance(...);
            // logo.scaleToFit(60, 60);
            // logoCell.addElement(logo);
            header.addCell(logoCell);

            // EMPRESA
            PdfPCell empresa = new PdfPCell();
            empresa.addElement(new Paragraph(sale.getBranch().getName(), headerFont));
            empresa.addElement(new Paragraph("CNPJ: " + sale.getBranch().getCnpj(), normalFont));
            empresa.addElement(new Paragraph("Telefone: " + sale.getBranch().getPhoneNumber(), normalFont));
            empresa.setBorder(Rectangle.BOX);
            header.addCell(empresa);

            // TÍTULO
            PdfPCell titulo = new PdfPCell(new Paragraph("NOTA DE VENDA", titleFont));
            titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titulo.setBorder(Rectangle.BOX);
            header.addCell(titulo);

            document.add(header);

            // ================= DADOS VENDA =================
            PdfPTable venda = new PdfPTable(4);
            venda.setWidthPercentage(100);

            venda.addCell(cell("Número", headerFont));
            venda.addCell(cell(String.valueOf(sale.getId()), normalFont));

            venda.addCell(cell("Data", headerFont));
            venda.addCell(
                    cell(sale.getIssueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), normalFont));

            venda.addCell(cell("Vendedor", headerFont));
            venda.addCell(cell(sale.getUser().getUsername(), normalFont));

            venda.addCell(cell("Filial", headerFont));
            venda.addCell(cell(sale.getBranch().getName(), normalFont));

            document.add(venda);

            // ================= CLIENTE =================
            PdfPTable cliente = new PdfPTable(2);
            cliente.setWidthPercentage(100);

            cliente.addCell(cell("Cliente", headerFont));
            cliente.addCell(cell(sale.getPerson().getName(), normalFont));

            cliente.addCell(cell("CPF", headerFont));
            cliente.addCell(cell(sale.getPerson().getCpf(), normalFont));

            Address a = sale.getPerson().getAddress();
            cliente.addCell(cell("Endereço", headerFont));
            cliente.addCell(cell(a.getStreet() + ", " + a.getNumber(), normalFont));

            document.add(cliente);

            // ================= ITENS =================
            PdfPTable itens = new PdfPTable(new float[] { 4, 1, 2, 2 });
            itens.setWidthPercentage(100);

            // HEADER
            Stream.of("Produto", "Qtd", "Unitário", "Total").forEach(h -> {
                PdfPCell c = new PdfPCell(new Phrase(h, headerFont));
                c.setBackgroundColor(new Color(220, 220, 220));
                c.setHorizontalAlignment(Element.ALIGN_CENTER);
                itens.addCell(c);
            });

            // DADOS
            for (ItemSale item : sale.getItemsSale()) {
                itens.addCell(cell(item.getItem().getName(), normalFont));

                itens.addCell(centerCell(String.valueOf(item.getAmount())));

                itens.addCell(rightCell(String.format("R$ %.2f", item.getPrice())));

                double total = item.getAmount() * item.getPrice();
                itens.addCell(rightCell(String.format("R$ %.2f", total)));
            }

            document.add(itens);

            // ================= TOTAIS =================
            PdfPTable totais = new PdfPTable(2);
            totais.setWidthPercentage(40);
            totais.setHorizontalAlignment(Element.ALIGN_RIGHT);

            totais.addCell(cell("Desconto", headerFont));
            totais.addCell(rightCell(String.format("R$ %.2f", sale.getDiscount())));

            totais.addCell(cell("TOTAL", headerFont));
            PdfPCell totalFinal = rightCell(String.format("R$ %.2f", sale.getTotalNote()));
            totalFinal.setBackgroundColor(new Color(230, 230, 230));
            totais.addCell(totalFinal);

            document.add(totais);

            // ================= QR =================
            Image qr = gerarQRCode("http://localhost/notas/" + sale.getId(), 100, 100);
            qr.setAlignment(Element.ALIGN_RIGHT);
            document.add(qr);

            // ================= RODAPÉ =================
            Paragraph rodape = new Paragraph(
                    "Documento gerado automaticamente - www.sistema.com",
                    FontFactory.getFont(FontFactory.HELVETICA, 8));
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
