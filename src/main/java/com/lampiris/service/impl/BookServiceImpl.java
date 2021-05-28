package com.lampiris.service.impl;

import com.lampiris.dto.BookDTO;
import com.lampiris.entity.Book;
import com.lampiris.entity.BookFamily;
import com.lampiris.repository.BookFamilyRepository;
import com.lampiris.repository.BookRepository;
import com.lampiris.service.BookService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.soap.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookFamilyRepository bookFamilyRepository;

    @Override
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book fromDTO(BookDTO dto) {
        BookFamily bookFamily = bookFamilyRepository.findByName(dto.getFamily());
        return Book.builder().title(dto.getTitle()).author(dto.getAuthor()).bookFamily(bookFamily).build();
    }

    public Book find(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() ->
                new RuntimeException("Object not found: " + Book.class.getName()));
    }

    @Override
    public Book update(Book book) {
        Book newBook = find(book.getId());
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setBookFamily(book.getBookFamily());
        return bookRepository.save(newBook);
    }

    @Override
    public void generateExcel() {
        String jdbcURL = "jdbc:h2:mem:testdb";
        String username = "sa";
        String password = "";
        String excelFilePath = "data.xlsx";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "SELECT * FROM book";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Books");

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
        headerCell.setCellValue("Book Title");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Book Author");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Book Family");
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
                                XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            Integer id = result.getInt("id");
            String title = result.getString("title");
            String author = result.getString("author");
            Integer familyId = result.getInt("book_family_id");

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(id);

            cell = row.createCell(columnCount++);
            cell.setCellValue(title);

            cell = row.createCell(columnCount++);
            cell.setCellValue(author);

            Optional<BookFamily> bookFamily = bookFamilyRepository.findById(familyId);
            String name = bookFamily.map(BookFamily::getName).orElse(null);

            cell = row.createCell(columnCount++);
            cell.setCellValue(name);

        }
    }

}
