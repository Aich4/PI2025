package com.monapp.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class PDFService {

    public static void generateContract(String fileName) {
        PDDocument document = new PDDocument();

        try {
            // Crée une nouvelle page
            PDPage page = new PDPage();
            document.addPage(page);

            // Crée un flux de contenu pour ajouter du texte à la page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Ouvre le flux de contenu pour l'écriture
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(100, 750);
            contentStream.showText("Voici le contrat généré...");
            contentStream.endText();

            // Ferme le flux de contenu
            contentStream.close();

            // Enregistre le fichier PDF dans le répertoire spécifié
            File pdfFile = new File("exported_pdfs/" + fileName);
            pdfFile.getParentFile().mkdirs();  // Crée les répertoires si nécessaire
            document.save(pdfFile);

            // Ferme le document
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la création du PDF.");
        }
    }
}
