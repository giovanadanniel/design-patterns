package main.java.br.pucpr.table;

import br.pucpr.user.Theme;
import java.util.List;

public class Table {
    private final TableData data;

    public Table(TableData data) {
        this.data = data;
    }

    public void print(Theme theme, boolean alignRight) {
        var columns = data.getColumns();
        if (columns == null || columns.isEmpty()) {
            System.out.println("ERRO: Tabela sem colunas.");
            return;
        }

        var widths = new int[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            widths[i] = columns.get(i).length();
        }

        var borderChar = theme.getBorderChar();
        var sb = new StringBuilder();

        appendLine(sb, borderChar, widths);
        appendRow(sb, columns, widths);
        appendLine(sb, borderChar, widths);
        for (var row : data.getRows()) {
            if (row == null) continue;
            appendRow(sb, row, widths);
        }
        appendLine(sb, borderChar, widths);

        if (alignRight) {
            for (var line : sb.toString().split("\n")) {
                System.out.println("                    " + line);
            }
        } else {
            System.out.print(sb);
        }
    }

    private void appendRow(StringBuilder sb, List<String> row, int[] widths) {
        sb.append("|");
        for (int i = 0; i < widths.length; i++) {
            var value = i < row.size() && row.get(i) != null ? row.get(i) : "";
            sb.append(" ").append(fit(value, widths[i])).append(" |");
        }
        sb.append("\n");
    }

    private void appendLine(StringBuilder sb, String borderChar, int[] widths) {
        int total = widths.length + 1;
        for (int w : widths) total += w + 2;
        sb.append(borderChar.repeat(total)).append("\n");
    }

    private String fit(String value, int width) {
        if (value.length() > width) {
            value = width > 3 ? value.substring(0, width - 3) + "..." : value.substring(0, width);
        }
        return String.format("%-" + width + "s", value);
    }
}