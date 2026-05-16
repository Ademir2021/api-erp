package br.com.centroinfo.api.helpers;

public class FormatHelper {

    public static String formatPhone(String phone) {

        if (phone == null || phone.isEmpty()) {
            return "";
        }

        // Remove tudo que não for número
        phone = phone.replaceAll("\\D", "");

        // Celular: (44) 99999-9999
        if (phone.length() == 11) {
            return phone.replaceFirst(
                    "(\\d{2})(\\d{5})(\\d{4})",
                    "($1) $2-$3");
        }

        // Fixo: (44) 3333-3333
        if (phone.length() == 10) {
            return phone.replaceFirst(
                    "(\\d{2})(\\d{4})(\\d{4})",
                    "($1) $2-$3");
        }
        return phone;
    };

    public static String formatCnpj(String cnpj) {

        if (cnpj == null || cnpj.isEmpty()) {
            return "";
        }

        // Remove tudo que não for número
        cnpj = cnpj.replaceAll("\\D", "");

        // Verifica se possui 14 dígitos
        if (cnpj.length() != 14) {
            return cnpj;
        }

        // Formata: 00.000.000/0000-00
        return cnpj.replaceFirst(
                "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})",
                "$1.$2.$3/$4-$5");
    };

    public static String formatCpf(String cpf) {

        if (cpf == null || cpf.isEmpty()) {
            return "";
        }

        // Remove tudo que não for número
        cpf = cpf.replaceAll("\\D", "");

        // Verifica se possui 11 dígitos
        if (cpf.length() != 11) {
            return cpf;
        }

        // Formata: 000.000.000-00
        return cpf.replaceFirst(
                "(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
                "$1.$2.$3-$4");
    }
}
