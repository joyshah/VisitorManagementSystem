package com.example.vms.util;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.stereotype.Component;

@Component
public class GeneratePdfUtility {

    private static String FILE = "gatePass.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD, BaseColor.BLUE);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);

    private final String TITLE_MESSAGE = "Visitor Entry Pass";
    private final String NAME_KEY = "Name: ";
    private final String PURPOSE_OF_VISIT = "Purpose Of Visit: ";
    private final String DATE_OF_VISIT = "Date of Visit: ";
    private final String LAPTOP_DETAILS = "Laptop Serial No: ";



    public File createPdf(String name, String purposeOfVisit, String durationOfVisit, String laptopDetails, BufferedImage qrImagePath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            generateGatePass(document,name,purposeOfVisit,durationOfVisit,laptopDetails, qrImagePath);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File(FILE);

    }

    private void generateGatePass(Document document, String name, String purposeOfVisit, String durationVisit, String laptopDetails, BufferedImage buffer) throws DocumentException, URISyntaxException, IOException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add( getAlignedParagraph(TITLE_MESSAGE ,catFont, Element.ALIGN_CENTER));
        addEmptyLine(preface, 2);

        //Path path = Paths.get(ClassLoader.getSystemResource("src/main/resources/Banjo_Approval.png").toURI());

        Image img = Image.getInstance(buffer,null);
        //Image img = Image.getInstance(qrImagePath);

        //BufferedImage image = null;
        //Image.getInstance(image,null);
        //Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scaleAbsolute(100,100);
        img.setAlignment(Element.ALIGN_CENTER);
        preface.add(img);
        preface.add(getAlignedParagraph(NAME_KEY + name ,smallBold, Element.ALIGN_CENTER));
        preface.add(getAlignedParagraph(PURPOSE_OF_VISIT + purposeOfVisit ,smallBold, Element.ALIGN_CENTER));
        preface.add(getAlignedParagraph(DATE_OF_VISIT + durationVisit ,smallBold, Element.ALIGN_CENTER));
        if (laptopDetails!=null && !laptopDetails.equals("")) {

            preface.add( getAlignedParagraph(LAPTOP_DETAILS + laptopDetails,smallBold, Element.ALIGN_CENTER));

        }
        document.add(preface);
        document.newPage();
    }


    private Paragraph getAlignedParagraph(String content, Font font,int alignment){
        Paragraph p =  new Paragraph( content, font);
        p.setAlignment(alignment);
        return p;
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
