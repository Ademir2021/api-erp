package br.com.centroinfo.api.providers.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.entities.sales.Sale;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

        private final JavaMailSender mailSender;

        @Value("${spring.mail.username}")
        private String userEmail;

        @Value("${app.mail.title}")
        private String title;

        public void sendMail(
                        Sale sale,
                        String comments) {
                try {
                        MimeMessage message = mailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                        helper.setFrom(userEmail);
                        helper.setTo(new String[] { userEmail, sale.getUser().getLogin() });
                        helper.setSubject("Contato do Formulário on-line de clientes");
                        helper.setText(
                                        "<b>Mensagem de:</b>" +
                                                        "<br><b>Cliente:</b> " + sale.getPerson().getName() +
                                                        "<br><b>Email:</b> " + sale.getUser().getLogin() +
                                                        "<br><b>Telefone:</b> " + sale.getPerson().getPhone() +
                                                        "<br><br><b>Assunto:</b> " + comments,
                                        true);
                        mailSender.send(message);
                        System.out.println("Email enviado");

                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void sendMailNote(
                        Sale sale,
                        byte[] pdfBytes) {
                try {
                        MimeMessage message = mailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(
                                        message, true,
                                        "UTF-8");
                        helper.setFrom(userEmail);
                        helper.setTo(new String[] { userEmail, sale.getUser().getLogin() });
                        helper.setSubject("Envio da Nota de Compra Nº " + sale.getId());
                        helper.setText(
                                        "<b>Comprador:</b> " + sale.getPerson().getName() +
                                                        "<br><b>Nota:</b> " + sale.getId() +
                                                        "<br><b>Email:</b> " + sale.getUser().getLogin() +
                                                        "<br><b>Telefone:</b> " + sale.getPerson().getPhone() +
                                                        "<br><br><b>Endereço:</b> "
                                                        + sale.getPerson().getAddress().getStreet() + " "
                                                        + sale.getPerson().getAddress().getNumber() + " "
                                                        + sale.getPerson().getAddress().getComplement() + " "
                                                        + sale.getPerson().getAddress().getNeighborhood() + " "
                                                        + sale.getPerson().getAddress().getZipCode().getCity().getName()
                                                        + " "
                                                        + sale.getPerson().getAddress().getZipCode().getCity()
                                                                        .getState().getAcronym(),
                                        true);
                        helper.addAttachment(
                                        "nota_" + sale.getId() + ".pdf",
                                        new ByteArrayResource(pdfBytes));
                        mailSender.send(message);
                        System.out.println("Nota enviada");
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void sendRecoverPassword(
                        String email,
                        String hash) {
                try {
                        MimeMessage message = mailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                        helper.setFrom(userEmail);
                        helper.setTo(new String[] { userEmail, email });
                        helper.setSubject("Recuperar Senha");
                        helper.setText(
                                        "<b>Conforme solicitado segue recuperação de acesso da sua conta:</b>" +
                                                        "<br><b>Email do Usuário:</b> " + email +
                                                        "<br><b>Sua nova senha para acesso é:</b> " + hash +
                                                        "<br>Para sua segurança, após logado atualize sua senha!",
                                        true);

                        mailSender.send(message);
                        System.out.println("Recuperação enviada");
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}