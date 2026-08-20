package main.java.br.pucpr.table;

import java.util.List;

public interface TableData {
    List<String> getColumns();
    List<List<String>> getRows();
}