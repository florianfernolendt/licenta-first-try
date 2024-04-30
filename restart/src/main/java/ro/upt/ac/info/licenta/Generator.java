package ro.upt.ac.info.licenta;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Generator {//data autoamata
    public static void generare(String facultatea, String specializarea, String nr, String nume, String anuniv, int anul, String cursuri, String nevoie, String mentiuni) {
        try {
            String path = "/Users/florian/Desktop/Licenta/Adeverinta.pdf";
            Document document = new Document(PageSize.A6.rotate(), 18, 18, 18, 18);//se poate face a3 daca se vrea pe formuatul ala
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            float fntSize;
            fntSize = 8f;
            Font boldFont = FontFactory.getFont("/Users/florian/Desktop/Licenta/Axiforma-Regular.ttf", BaseFont.IDENTITY_H, fntSize, Font.BOLD);
            Font normalFont = FontFactory.getFont("/Users/florian/Desktop/Licenta/Axiforma-Regular.ttf", BaseFont.IDENTITY_H, fntSize);

            Paragraph para = new Paragraph(new Phrase("UNIVERSITATEA POLITEHNICA TIMIȘOARA", boldFont));
            document.add(para);
            para = new Paragraph(new Phrase(facultatea, boldFont));
            document.add(para);
            para = new Paragraph(new Phrase("Specializare: " + specializarea, normalFont));
            document.add(para);

            Image img = Image.getInstance("/Users/florian/Desktop/Licenta/Licenta/assets/upt.png");
            img.setAbsolutePosition(265, 230);
            img.scaleAbsolute(168, 56);
            document.add(img);

            String numarFilePath = "numar.txt";
            int numar = 0;
            try {
                File numarFile = new File(numarFilePath);
                Scanner scanner = new Scanner(numarFile);
                numar = scanner.nextInt();
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Fișierul numar.txt nu a fost găsit.");
            }
            para = new Paragraph(new Phrase("\nNr.: " + numar, normalFont));
            numar++;
            FileWriter writer = new FileWriter(numarFilePath);
            writer.write(String.valueOf(numar));
            writer.close();

            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String formattedDate = dateFormat.format(new Date());
            para = new Paragraph(new Phrase("Data: " + formattedDate, normalFont));//aici data automata
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);

            fntSize = 12f;
            boldFont = FontFactory.getFont("/Users/florian/Desktop/Licenta/Axiforma-Regular.ttf", BaseFont.IDENTITY_H, fntSize, Font.BOLD);
            para = new Paragraph(new Phrase("ADEVERINȚĂ", boldFont));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);

            fntSize = 9f;
            normalFont = FontFactory.getFont("/Users/florian/Desktop/Licenta/Axiforma-Regular.ttf", BaseFont.IDENTITY_H, fntSize);
            para = new Paragraph(new Phrase("\nStudenta/Studentul   " + nume + "   este înscris/ă în anul universitar   " + anuniv + "   în anul   " + anul + "   de studii, cursuri    " + cursuri + ".", normalFont));
            para.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para);

            para = new Paragraph(new Phrase("Adeverința se eliberează pentru a-i servi la:   " + nevoie, normalFont));
            document.add(para);

            para = new Paragraph(new Phrase("Mențiuni:   " + mentiuni, normalFont));
            document.add(para);

            fntSize = 10f;
            boldFont = FontFactory.getFont("/Users/florian/Desktop/Licenta/Axiforma-Regular.ttf", BaseFont.IDENTITY_H, fntSize, Font.BOLD);
            para = new Paragraph(new Phrase("\nDECAN,                                                   SECRETAR ȘEF FACULTATE,", boldFont));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            para = new Paragraph(new Phrase("              L.S.", normalFont));
            document.add(para);


            Image img2 = Image.getInstance("/Users/florian/Desktop/Licenta/Licenta/assets/ss.png");
            img2.setAlignment(Image.UNDERLYING);
            img2.setAbsolutePosition(70, 10);
            img2.scaleToFit(99, 100);
            document.add(img2);
            img2 = Image.getInstance("/Users/florian/Desktop/Licenta/Licenta/assets/s.png");
            img2.setAlignment(Image.UNDERLYING);
            img2.setAbsolutePosition(250, 10);
            img2.scaleToFit(99, 100);
            document.add(img2);


            document.close();
            System.out.println("\nAdeverinta generata cu succes!");
        } catch (Exception e) {
            System.out.println("Eroare!");
        }
    }

}
