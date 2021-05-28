package com.lampiris.service.impl;

import com.lampiris.dto.BookFamilyDTO;
import com.lampiris.entity.BookFamily;
import com.lampiris.repository.BookFamilyRepository;
import com.lampiris.service.BookFamilyService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Service
public class BookFamilyServiceImpl implements BookFamilyService {

    @Autowired
    private BookFamilyRepository repository;

    @Override
    public List<BookFamily> getAll() {
        List<BookFamily> bookFamilies = repository.findAll();
        return bookFamilies;
    }

    @Override
    public BookFamily save(BookFamily bookFamily) {
        return repository.save(bookFamily);
    }

    public BookFamily fromDTO(BookFamilyDTO dto) {
        return BookFamily.builder().id(dto.getId()).name(dto.getName()).build();
    }

    public BookFamily find(Integer id) {
        Optional<BookFamily> bookFamily = repository.findById(id);
        return bookFamily.orElseThrow(() ->
                new RuntimeException("Object not found: " + BookFamily.class.getName()));
    }

    @Override
    public BookFamily update(BookFamily bookFamily) {
        BookFamily newBookFamily = find(bookFamily.getId());
        newBookFamily.setName(bookFamily.getName());
        return repository.save(newBookFamily);
    }

    @Override
    public void generateExcel() {
        String jdbcURL = "jdbc:h2:mem:testdb";
        String username = "sa";
        String password = "";
        String excelFilePath = "book_family.xlsx";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "SELECT * FROM book_family";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Book Family");

            writeHeaderLine(sheet);
            writeDataLines(result, workbook, sheet);
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeHeaderLine(XSSFSheet sheet) {
        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Book Id");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Book Name");
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
                                XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            Integer id = result.getInt("id");
            String name = result.getString("name");

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(id);

            cell = row.createCell(columnCount++);
            cell.setCellValue(name);
        }
    }

}
