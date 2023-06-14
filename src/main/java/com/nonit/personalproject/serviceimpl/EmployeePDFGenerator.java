//package com.nonit.personalproject.serviceimpl;
//
//import com.lowagie.text.*;
//import com.lowagie.text.pdf.CMYKColor;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//import com.nonit.personalproject.dto.EmployeeDTO;
//import com.nonit.personalproject.entity.Employee;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//public class EmployeePDFGenerator {
//    private List<EmployeeDTO> employeeList;
//    public void generate (List<EmployeeDTO> employeeList, HttpServletResponse response) throws DocumentException, IOException {
//        Document document = new Document(PageSize.A2);
//        PdfWriter.getInstance(document, response.getOutputStream());
//
//        // Open the document to modify
//        document.open();
//
//        // Create and set font size
//        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//        fontTiltle.setSize(20);
//
//        // Creating paragraph
//        Paragraph paragraph = new Paragraph("LIST OF EMPLOYEES", fontTiltle);
//
//        // Aligning the paragraph in document
//        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
//
//        // Adding the created paragraph in document
//        document.add(paragraph);
//
//        // Creating a table of 3 columns
//        PdfPTable table = new PdfPTable(10);
//
//        // Setting width of table, its columns and spacing
//        table.setWidthPercentage(100f);
//        table.setWidths(new int[] { 1, 2, 2, 2, 4, 5, 2, 2, 3, 2});
//        table.setSpacingBefore(5);
//
//        // Create Table Cells for table header
//        PdfPCell cell = new PdfPCell();
//
//        // Setting the background color and padding
//        cell.setBackgroundColor(CMYKColor.MAGENTA);
//        cell.setPadding(5);
//
//        // Creating font
//        // Setting font style and size
//        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//        font.setColor(CMYKColor.WHITE);
//
//        // Adding headings in the created table cell/ header
//        // Adding Cell to table
//        cell.setPhrase(new Phrase("ID", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("First Name", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Last Name", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Gender", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Email", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Address", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Phone", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Hire Date", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Position", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Salary", font));
//        table.addCell(cell);
//
//        // Iterating over the list of employees
//        for (EmployeeDTO e : employeeList) {
//
//            table.addCell(String.valueOf(e.getId()));
//
//            table.addCell(e.getFirstName());
//
//            table.addCell(e.getLastName());
//
//            table.addCell(e.getGender());
//
//            table.addCell(e.getEmail());
//
//            table.addCell(e.getAddress());
//
//            table.addCell(e.getPhone());
//
//            table.addCell(String.valueOf(e.getHireDate()));
//
//            table.addCell(e.getPosition());
//
//            table.addCell("$" + String.valueOf(e.getSalary()));
//
//        }
//        // Adding the created table to document
//        document.add(table);
//
//        // Closing the document
//        document.close();
//
//    }
//
//}
