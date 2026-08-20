package main.java.br.pucpr.table;

import br.pucpr.user.User;
import java.util.ArrayList;
import java.util.List;

public class UserTableData implements TableData {
    private final ArrayList<User> users;
    private final boolean maskCpf;

    public UserTableData(ArrayList<User> users, boolean maskCpf) {
        this.users = users;
        this.maskCpf = maskCpf;
    }

    @Override
    public List<String> getColumns() {
        return List.of("ID", "NOME", "EMAIL", "CPF");
    }

    @Override
    public List<List<String>> getRows() {
        var rows = new ArrayList<List<String>>();
        if (users == null) return rows;

        for (var user : users) {
            if (user == null) continue;
            rows.add(List.of(
                    formatId(user.id()),
                    formatName(user),
                    validateAndFormatEmail(user.email()),
                    formatCpf(user.cpf(), maskCpf)
            ));
        }
        return rows;
    }

    private static String formatId(Long id) {
        return id != null ? id.toString() : "0";
    }

    private static String formatCpf(String cpf, boolean mask) {
        if (cpf == null || cpf.length() != 11) {
            return "CPF INVÁLIDO";
        }
        if (mask) {
            return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
        }
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9)
                + "-" + cpf.substring(9, 11);
    }

    private static String validateAndFormatEmail(String email) {
        return email == null || !email.contains("@") ? "INVÁLIDO" : email;
    }

    private static String formatName(User user) {
        var name = user.name();
        return (name == null || name.isEmpty()) ? "NÃO INFORMADO" : name;
    }
}