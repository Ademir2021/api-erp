package br.com.centroinfo.api.services.sale;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;



import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;

import br.com.centroinfo.api.entities.sales.ItemSale;
import br.com.centroinfo.api.entities.sales.Sale;

@Service
public class NotaCupomService {

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

    public byte[] gerarCupom(Sale sale) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try {
        // 📏 Tamanho tipo bobina 80mm
        Rectangle pageSize = new Rectangle(226, 800);
        Document document = new Document(pageSize, 10, 10, 10, 10);

        PdfWriter.getInstance(document, outputStream);
        document.open();

        // 🔤 Fontes
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 9);

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        // ================= HEADER =================
        Paragraph empresa = new Paragraph();
        empresa.setAlignment(Element.ALIGN_CENTER);

        empresa.add(new Chunk(sale.getBranch().getFantasyName() + "\n", headerFont));
        empresa.add(new Chunk("CNPJ: " + sale.getBranch().getCnpj() + "\n", normalFont));
        empresa.add(new Chunk("Tel: " + sale.getBranch().getPhoneNumber() + "\n", normalFont));
        empresa.add(new Chunk("----------------------------------\n"));

        document.add(empresa);

        // ================= VENDA =================
        document.add(new Paragraph("CUPOM NÃO FISCAL", headerFont));
        document.add(new Paragraph("Venda Nº: " + String.format("%06d", sale.getId()), normalFont));
        document.add(new Paragraph(
                "Data: " + sale.getIssueDate()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                normalFont));

        document.add(new Paragraph("----------------------------------"));

        // ================= CLIENTE =================
        if (sale.getPerson() != null) {
            document.add(new Paragraph("Cliente: " + sale.getPerson().getName(), normalFont));

            if (sale.getPerson().getCpf() != null) {
                document.add(new Paragraph("CPF: " + sale.getPerson().getCpf(), normalFont));
            }
        }

        document.add(new Paragraph("----------------------------------"));

        // ================= ITENS =================
        for (ItemSale item : sale.getItemsSale()) {
            BigDecimal total = item.getAmount().multiply(item.getPrice());

            document.add(new Paragraph(item.getItem().getName(), normalFont));

            document.add(new Paragraph(
                    item.getAmount() + " x " +
                            nf.format(item.getPrice()) + " = " +
                            nf.format(total),
                    normalFont));
        }

        document.add(new Paragraph("----------------------------------"));

        // ================= TOTAIS =================
        document.add(new Paragraph("Desconto: " + nf.format(sale.getDiscount()), normalFont));

        Paragraph total = new Paragraph("TOTAL: " + nf.format(sale.getTotalNote()), headerFont);
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);

        document.add(new Paragraph("----------------------------------"));

        // ================= PAGAMENTO =================
        String tipoPagamento =
                sale.getOperationSale().getId() == 3 ? "FATURA" :
                sale.getOperationSale().getId() == 2 ? "CARTÃO" : "PIX";

        document.add(new Paragraph("Pagamento: " + tipoPagamento, normalFont));

        // ================= QR CODE =================
        Image qr = gerarQRCode("http://localhost/cupom/" + sale.getId() + "/pdf", 80, 80);
        if (qr != null) {
            qr.setAlignment(Element.ALIGN_CENTER);
            document.add(qr);
        }

        document.add(new Paragraph("----------------------------------"));

        // ================= RODAPÉ =================
        Paragraph rodape = new Paragraph(
                "CUPOM NÃO FISCAL\nObrigado pela preferência!",
                normalFont);

        rodape.setAlignment(Element.ALIGN_CENTER);
        document.add(rodape);

        document.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return outputStream.toByteArray();
}
    
}
