package ua.nure.korotkov.SummaryTask4.web.forMail;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ua.nure.korotkov.SummaryTask4.db.entity.Edition;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Андрей on 24.01.2017.
 */
public class MyPDF {

    public static void createPDFEdition(String name, List<Edition> editions) throws DocumentException, FileNotFoundException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:\\temp\\"+name+".pdf"));

        document.open();

        Paragraph title = new Paragraph(name, FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new CMYKColor(0, 255, 255,17)));
        title.setAlignment(Element.ALIGN_CENTER);
        Chapter chapter = new Chapter(title, 1);
        chapter.setNumberDepth(0);
        Section section = chapter.addSection("My"+title);

        PdfPTable table = new PdfPTable(5);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);

        PdfPCell head1 = new PdfPCell(new Phrase("№"));
        PdfPCell head2 = new PdfPCell(new Phrase("Name"));
        PdfPCell head3 = new PdfPCell(new Phrase("Topic"));
        PdfPCell head4 = new PdfPCell(new Phrase("Price"));
        PdfPCell head5 = new PdfPCell(new Phrase("Publisher"));
        table.addCell(head1);
        table.addCell(head2);
        table.addCell(head3);
        table.addCell(head4);
        table.addCell(head5);

        int k = 1;
        for(Edition edit : editions){
            table.addCell(k+"");
            k++;
            String priwet = edit.getName();
            String priwet2 = null;
            byte[] utf8Bytes;

            try {
                utf8Bytes = priwet.getBytes("UTF8");
                priwet2 = new String(utf8Bytes,"UTF8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            table.addCell(priwet2);
            table.addCell(edit.getTopic()+"");
            table.addCell(edit.getPrice()+"");
            table.addCell(edit.getPublisher()+"");
        }

        section.add(table);

        document.add(chapter);
        document.close();
    }
}
