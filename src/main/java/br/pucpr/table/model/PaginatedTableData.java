package br.pucpr.table.model;

public class PaginatedTableData implements TableData {
  private final TableData data;
  private final int rowsPerPage;
  private int currentPage;

  public PaginatedTableData(TableData data, int rowsPerPage) {
    this.data = data;
    this.rowsPerPage = rowsPerPage;
    this.currentPage = 0;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public int getPageCount() {
    return (int) Math.ceil((double) data.rowCount() / rowsPerPage);
  }

  public void setPage(int page) {
    if (page < 0 || page >= getPageCount()) {
      throw new IllegalArgumentException("Invalid page");
    }
    this.currentPage = page;
  }

  public void nextPage() {
    if (currentPage < getPageCount() - 1) {
      currentPage++;
    }
  }

  public void previousPage() {
    if (currentPage > 0) {
      currentPage--;
    }
  }

  @Override
  public int rowCount() {
    int start = currentPage * rowsPerPage;
    return Math.min(rowsPerPage, data.rowCount() - start);
  }

  @Override
  public int colCount() {
    return data.colCount();
  }

  @Override
  public String header(int col) {
    return data.header(col);
  }

  @Override
  public String get(int row, int col) {
    int realRow = currentPage * rowsPerPage + row;
    return data.get(realRow, col);
  }
}
