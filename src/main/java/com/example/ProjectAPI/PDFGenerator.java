package com.example.ProjectAPI;


import java.io.IOException;
import java.util.List;
import java.util.Set;

//import javax.servlet.http.HttpServletResponse;

import com.example.ProjectAPI.entity.Course;
import com.example.ProjectAPI.entity.Student;

import com.example.ProjectAPI.entity.Tutor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PDFGenerator {

    // List to hold all Students
    private List<Student> studentList;

    public PDFGenerator() {

    }

    public void generate(HttpServletResponse response) throws DocumentException, IOException {

        // Creating the Object of Document
        Document document = new Document(PageSize.A4);

        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        // Opening the created document to modify it
        document.open();

        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);

        // Creating paragraph
        Paragraph paragraph = new Paragraph("List Of Students", fontTiltle);

        // Aligning the paragraph in document
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in document
        document.add(paragraph);

        // Creating a table of 3 columns
        PdfPTable table = new PdfPTable(3);
        // Setting width of table, its columns and spacing
        // table.setWidthPercentage(100f);
        // table.setWidths(new int[] { 4, 4, 4 });
        table.setSpacingBefore(5);

        // Create Table Cells for table header
        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding
        cell.setBackgroundColor(CMYKColor.MAGENTA);
//        cell.setPadding(5);

        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        // Adding headings in the created table cell/ header
        // Adding Cell to table
//        cell.setPhrase(new Phrase("ID", font));
//        table.addCell(cell);
        cell.setPhrase(new Phrase("Student Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("List of Tutors", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("List of Course", font));
        table.addCell(cell);

        // Iterating over the list of students
        for (Student student : studentList) {
            // Adding student id
//            table.addCell(String.valueOf(student.getId()));
            // Adding student name
            table.addCell(student.getName());
            // Adding student section
            String tutorList ="";
            for (Tutor s: student.getTutors()){
                String tutorRow = s.getId()+".\nname: "+s.getName()+",\nExperience: "+s.getExperience()+",\nCourses: \n"+covertToString(s.getCourses())+"\n";
                tutorList += tutorRow+"\n\t............................\n";
            }
            table.addCell(tutorList);
            table.addCell(covertToString(student.getCourses()));
        }
        // Adding the created table to document
        document.add(table);

        // Closing the document
        document.close();

    }

    private String covertToString(Set<Course> courses) {
        String res="";
        int i=1;
        for (Course c: courses){
            res += i+". subject: "+c.getName()+","+c.getDuration()+"hrs duration\n";
            i++;
        }
        return res;
    }


}