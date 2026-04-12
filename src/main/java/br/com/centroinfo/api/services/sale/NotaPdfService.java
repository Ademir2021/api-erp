package br.com.centroinfo.api.services.sale;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import br.com.centroinfo.api.entities.accountsReceivable.AccountsReceivable;
import br.com.centroinfo.api.entities.address.address.Address;
import br.com.centroinfo.api.entities.sales.ItemSale;
import br.com.centroinfo.api.entities.sales.Sale;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class NotaPdfService {
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

    private PdfPCell cell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        return cell;
    }

    private PdfPCell rightCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }

    private PdfPCell centerCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
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
            InputStream is = getClass().getResourceAsStream("/logo.png");
            Image logo = Image.getInstance(is.readAllBytes());
            logo.scaleToFit(60, 60);
            logoCell.addElement(logo);
            header.addCell(logoCell);
            // EMPRESA
            PdfPCell e = new PdfPCell();
            e.addElement(new Paragraph(sale.getBranch().getFantasyName(), headerFont));
            e.addElement(new Paragraph(sale.getBranch().getName(), headerFont));
            e.addElement(new Paragraph("CNPJ: " + sale.getBranch().getCnpj() + " " +
                    "Inscrição Estadual: " + sale.getBranch().getInscricState(), normalFont));
            e.addElement(new Paragraph("Telefone: " + sale.getBranch().getPhoneNumber(), normalFont));
            e.addElement(
                    new Paragraph("Endereço: " + sale.getBranch().getPerson().getAddress().getStreet() + " " +
                            sale.getBranch().getPerson().getAddress().getNumber() + " " +
                            sale.getBranch().getPerson().getAddress().getNeighborhood() + "\n" +
                            sale.getBranch().getPerson().getAddress().getZipCode().getCity().getName() + " " +
                            sale.getBranch().getPerson().getAddress().getZipCode().getCity().getState().getAcronym() +
                            " CEP: " + sale.getBranch().getPerson().getAddress().getZipCode().getCode() + "\n\n",
                            normalFont));
            e.setBorder(Rectangle.BOX);
            header.addCell(e);
            // TITULO
            Paragraph p = new Paragraph();
            p.add(new Chunk(sale.getOperationSale().getDescription() + "\n", titleFont));
            p.add(new Chunk("Nº: " + String.format("%06d", sale.getId()) + "\n\n", titleFont));
            p.add(new Chunk("CFOP: " + sale.getOperationSale().getCfop(), normalFont));
            p.setAlignment(Element.ALIGN_CENTER);
            PdfPCell cell = new PdfPCell(p);
            cell.setBorder(Rectangle.BOX);
            cell.setLeading(0f, 1.4f);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            header.addCell(cell);
            document.add(header);
            // ================= DADOS VENDA =================
            PdfPTable v = new PdfPTable(4);
            v.setWidthPercentage(100);
            v.addCell(cell("Natureza da Operação: ", headerFont));
            v.addCell(cell(sale.getOperationSale().getDefaultNature(), normalFont));
            v.addCell(cell("Data da Emissão:", headerFont));
            v.addCell(
                    cell(sale.getIssueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), normalFont));
            v.addCell(cell("Email do Vendedor: ", headerFont));
            v.addCell(cell(sale.getUser().getUsername(), normalFont));
            v.addCell(cell("Filial: ", headerFont));
            v.addCell(cell((String.format("%03d", sale.getBranch().getId())), normalFont));
            document.add(v);
            // ================= CLIENTE =================
            PdfPTable cliente = new PdfPTable(2);
            cliente.setWidthPercentage(100);
            cliente.addCell(cell("Cliente: ", headerFont));
            cliente.addCell(cell(sale.getPerson().getName(), normalFont));
            cliente.addCell(cell("CPF: ", headerFont));
            cliente.addCell(cell(sale.getPerson().getCpf(), normalFont));
            Address a = sale.getPerson().getAddress();
            cliente.addCell(cell("Endereço: ", headerFont));
            cliente.addCell(cell(a.getStreet() + ", " + a.getNumber() + ", " +
                    a.getZipCode().getCity().getName() + ", " + a.getZipCode().getCity().getState().getAcronym(),
                    normalFont));
            document.add(cliente);
            // ================ FATURAS ===================
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            if (sale.getAccountsReceivable() != null && !sale.getAccountsReceivable().isEmpty()) {
                Paragraph titleAr = new Paragraph(
                        sale.getOperationSale().getId() == 3 ? "FATURA"
                                : (sale.getOperationSale().getId() == 2 ? "CARTÃO" : "PIX"),
                        headerFont);
                titleAr.setAlignment(Element.ALIGN_LEFT);
                titleAr.setSpacingAfter(5);
                document.add(titleAr);
                PdfPTable contas = new PdfPTable(new float[] { 1, 2, 3 });
                contas.setWidthPercentage(100);
                // HEADER
                Stream.of("Número", "Data Vcto", "Valor").forEach(h -> {
                    PdfPCell c = new PdfPCell(new Phrase(h, headerFont));
                    c.setBackgroundColor(new Color(220, 220, 220));
                    c.setHorizontalAlignment(Element.ALIGN_CENTER);
                    contas.addCell(c);
                });
                for (AccountsReceivable ar : sale.getAccountsReceivable()) {
                    contas.addCell(centerCell(String.valueOf(ar.getId()), normalFont));
                    contas.addCell(centerCell(ar.getDueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                            normalFont));
                    contas.addCell(centerCell(nf.format(ar.getValue()), normalFont));
                }
                document.add(contas);
            }
            // ================= ITENS =================
            Paragraph titleITems = new Paragraph("DADOS DO PRODUTO/SERVIÇO", headerFont);
            titleITems.setAlignment(Element.ALIGN_LEFT); // centralizado (opcional)
            titleITems.setSpacingAfter(5); // espaço depois do título
            document.add(titleITems);
            PdfPTable itens = new PdfPTable(new float[] { 4, 1, 1, 2, 2 });
            itens.setWidthPercentage(100);
            // HEADER
            Stream.of("DESCRIÇÃO PRODUTO/SERVIÇO", "QTD", "UN", "UNIT", "TOTAL").forEach(h -> {
                PdfPCell c = new PdfPCell(new Phrase(h, headerFont));
                c.setBackgroundColor(new Color(220, 220, 220));
                c.setHorizontalAlignment(Element.ALIGN_CENTER);
                itens.addCell(c);
            });
            // DADOS
            for (ItemSale item : sale.getItemsSale()) {
                itens.addCell(cell(item.getItem().getName(), normalFont));
                itens.addCell(centerCell(String.valueOf(item.getAmount()), normalFont));
                itens.addCell(centerCell(String.valueOf(item.getItem().getUnitMeasure().getName()), normalFont));
                itens.addCell(rightCell(String.format("R$ %.2f", item.getPrice()), normalFont));
                BigDecimal total = item.getAmount().multiply(item.getPrice());
                itens.addCell(rightCell(String.format("R$ %.2f", total), normalFont));
            }
            document.add(itens);
            // ================= TOTAIS =================
            PdfPTable totais = new PdfPTable(2);
            totais.setWidthPercentage(40);
            totais.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totais.addCell(cell("Desconto", headerFont));
            totais.addCell(rightCell(String.format("R$ %.2f", sale.getDiscount()), normalFont));
            totais.addCell(cell("TOTAL", headerFont));
            PdfPCell totalFinal = rightCell(String.format("R$ %.2f", sale.getTotalNote()), normalFont);
            totalFinal.setBackgroundColor(new Color(230, 230, 230));
            totais.addCell(totalFinal);
            document.add(totais);
            // ================= QR =================
            Image qr = gerarQRCode("http://localhost/nota/" + sale.getId() + "/pdf", 100, 100);
            qr.setAlignment(Element.ALIGN_RIGHT);
            document.add(qr);
            String documentoPessoa = sale.getPerson().getCpf() != null
                    ? sale.getPerson().getCpf()
                    : (sale.getPerson().getCnpj() != null ? sale.getPerson().getCnpj() : "");
            String texto = "________________________\n"
                    + sale.getPerson().getName() + "\n"
                    + documentoPessoa;
            Paragraph assinatura = new Paragraph(texto, headerFont);
            assinatura.setAlignment(Element.ALIGN_LEFT);
            assinatura.setSpacingAfter(10);
            document.add(assinatura);
            // ================= RODAPÉ =================
            Paragraph rodape = new Paragraph(
                    "Documento gerado automaticamente - pelo ERP da Empresa, em http://localhost",
                    FontFactory.getFont(FontFactory.HELVETICA, 8));
            rodape.setAlignment(Element.ALIGN_CENTER);
            document.add(rodape);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}