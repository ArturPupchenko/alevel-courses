package com.alevel.courses.modules.module3.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.*;

public class CsvTable {

    private final List<String> headers;

    private final Map<String, Integer> headerIndices;

    private final String[][] cells;

    private final int rows;

    private final int columns;

    public CsvTable(List<String> headers, String[][] cells) {
        this.cells = cells;
        this.rows = cells.length;
        this.columns = headers.size();
        this.headers = Collections.unmodifiableList(headers);
        Map<String, Integer> headerIndices = new HashMap<>(headers.size());
        int index = 0;
        for (String header : headers) {
            if (headerIndices.put(header, index++) != null)
                throw new IllegalArgumentException("Duplicate header: " + header);
        }
        this.headerIndices = headerIndices;
    }

    public int height() {
        return rows;
    }

    public int width() {
        return columns;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<String> getRow(int row) {
        return Collections.unmodifiableList(Arrays.asList(cells[row]));
    }

    public String get(int row, int col) {
        return cells[row][col];
    }

    public String get(int row, String col) {
        return cells[row][headerIndices.get(col)];
    }

    public String[][] getCells() {
        return cells;
    }

    public static Optional<CsvTable> fromFile(Path path) throws IOException {
        var lines = Files.readAllLines(path);
        return fromLines(lines);
    }

    public static Optional<CsvTable> fromLines(List<String> lines) {
        if (lines == null || lines.isEmpty()) return Optional.empty();

        var iterator = lines.iterator();

        var headers = split(iterator.next());

        int columns = headers.size();

        int rows = lines.size() - 1;

        String[][] cells = new String[rows][];

        for (int row = 0; iterator.hasNext(); row++) {
            cells[row] = split(iterator.next(), columns);
        }

        return Optional.of(new CsvTable(headers, cells));
    }

    private static List<String> split(String csvRow) {
        List<String> row = new ArrayList<>();
        for (int end = 0, start = 0, length = csvRow.length(); end < length; end++) {
            if (csvRow.charAt(end) == ',') {
                row.add(start == end ? "" : csvRow.substring(start, end));
                start = end + 1;
            }
        }
        return row;
    }

    private static String[] split(String csvRow, int limit) {
        String[] row = new String[limit];
        for (int count = 0, start = 0, end = 0, length = csvRow.length(); end < length && count < limit; end++) {
            if (csvRow.charAt(end) == ',') {
                row[count++] = start == end ? "" : csvRow.substring(start, end);
                start = end + 1;
            }
        }
        return row;
    }

    public static void createCsvTableAndWriteItTiFile(String path, List<Long> operationsId, List<Long> operationsAmount, List<Timestamp> operationTimestamps, List<Long> sumsOfIncome, List<Long> saldos) {
        CsvTable csvTable = getCsvTable(operationsId, operationsAmount, operationTimestamps, sumsOfIncome, saldos);
        writeIntoFile(csvTable,path);
    }


    public static CsvTable getCsvTable(List<Long> operationsId, List<Long> operationsAmount, List<Timestamp> operationTimestamps, List<Long> sumsOfIncome, List<Long> saldos) {
        List<String> headers = Arrays.asList("operation_id", "amount", "time", "sum_of_income", "saldo");
        String[][] cells = new String[operationsId.size()][headers.size()];
        for (int i = 0; i < operationsId.size(); i++) {

            String operationId = operationsId.get(i).toString();
            String amount = operationsAmount.get(i).toString();
            String time = DatesUtil.formatTimestampToISO(operationTimestamps.get(i));
            String sumOfIncome = sumsOfIncome.get(i).toString();
            String saldo = saldos.get(i).toString();

            int j = 0;

            cells[i][j] = operationId;
            cells[i][++j] = amount;
            cells[i][++j] = time;
            cells[i][++j] = sumOfIncome;
            cells[i][++j] = saldo;
        }
        return new CsvTable(headers, cells);
    }


    public static void writeIntoFile(CsvTable csvTable, String path) {
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(path))) {

            List<String> headers = csvTable.getHeaders();
            String[][] cells = csvTable.getCells();

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < headers.size() - 1; i++) {
                stringBuilder.append(headers.get(i)).append(",");
            }
            stringBuilder.append(headers.get(headers.size() - 1)).append("\n");
            writter.write(stringBuilder.toString());


            for (int i = 0; i < cells.length; i++) {
                stringBuilder.setLength(0);
                for (int j = 0; j < cells[0].length - 1; j++) {
                    stringBuilder.append(cells[i][j]).append(",");
                }
                stringBuilder.append(cells[i][cells[0].length - 1]).append("\n");
                writter.write(stringBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}