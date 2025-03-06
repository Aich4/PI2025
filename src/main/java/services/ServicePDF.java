package services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.io.IOException;

public class ServicePDF {

    public static void generatePDF(String filePath) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Charger et positionner le logo
            Image logo = Image.getInstance("C:\\Users\\MSI\\Desktop\\integration user+aicha+dest\\src\\main\\resources\\LOGO.png");
            logo.scaleToFit(80, 80);
            logo.setAlignment(Image.ALIGN_RIGHT);
            document.add(logo);

            // Ajouter un espace après le logo
            document.add(new Paragraph("\n\n"));

            // Titre principal
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
            Paragraph title = new Paragraph("Règlement des Abonnements", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Ajouter une ligne de séparation
            document.add(new Chunk(new LineSeparator()));
            document.add(new Paragraph("\n"));

            // Introduction
            Font introFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.DARK_GRAY);
            Paragraph introduction = new Paragraph(
                    "Ce règlement définit les conditions générales d’abonnement aux services proposés. "
                            + "En souscrivant à un abonnement, l’utilisateur accepte les conditions énoncées ci-dessous.",
                    introFont);
            introduction.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(introduction);

            document.add(new Paragraph("\n"));

            // Section 1 : Conditions Générales
            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
            Paragraph section1 = new Paragraph("1. Conditions Générales", sectionFont);
            document.add(section1);

            Font textFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
            Paragraph text1 = new Paragraph(
                    "L'abonnement est strictement personnel et ne peut être transféré à un tiers. "
                            + "Il permet d’accéder aux services souscrits pour la durée spécifiée. "
                            + "Toute utilisation abusive pourra entraîner la suspension de l’abonnement sans remboursement.",
                    textFont);
            text1.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(text1);

            document.add(new Paragraph("\n"));

            // Section 2 : Modalités de Paiement
            Paragraph section2 = new Paragraph("2. Modalités de Paiement", sectionFont);
            document.add(section2);

            Paragraph text2 = new Paragraph(
                    "Le paiement de l’abonnement doit être effectué en totalité avant l’activation du service. "
                            + "Aucun abonnement ne sera valide sans règlement complet. "
                            + "En cas de non-paiement, l’accès au service sera suspendu jusqu’à régularisation.",
                    textFont);
            text2.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(text2);

            document.add(new Paragraph("\n"));

            // Section 3 : Durée et Renouvellement
            Paragraph section3 = new Paragraph("3. Durée et Renouvellement", sectionFont);
            document.add(section3);

            Paragraph text3 = new Paragraph(
                    "L’abonnement est valable pour la durée indiquée au moment de la souscription. "
                            + "Le renouvellement peut être automatique ou manuel selon le type d’abonnement choisi. "
                            + "En cas de renouvellement automatique, le prélèvement sera effectué à la date prévue sauf annulation préalable.",
                    textFont);
            text3.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(text3);

            document.add(new Paragraph("\n"));

            // Section 4 : Résiliation et Remboursement
            Paragraph section4 = new Paragraph("4. Résiliation et Remboursement", sectionFont);
            document.add(section4);

            Paragraph text4 = new Paragraph(
                    "L’utilisateur peut demander la résiliation de son abonnement à tout moment. "
                            + "Toutefois, aucun remboursement ne sera effectué après l’activation du service. "
                            + "Dans des cas exceptionnels (fraude, erreur de facturation), un remboursement partiel pourra être envisagé.",
                    textFont);
            text4.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(text4);

            document.add(new Paragraph("\n"));

            // Section 5 : Responsabilités et Obligations
            Paragraph section5 = new Paragraph("5. Responsabilités et Obligations", sectionFont);
            document.add(section5);

            Paragraph text5 = new Paragraph(
                    "L’utilisateur s’engage à respecter les conditions d’utilisation du service. "
                            + "Toute utilisation frauduleuse ou non conforme peut entraîner la résiliation immédiate de l’abonnement. "
                            + "Le prestataire se réserve le droit de modifier ces conditions avec un préavis raisonnable.",
                    textFont);
            text5.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(text5);

            document.add(new Paragraph("\n"));

            // Ajout d'une page de signature
            document.newPage();
            Paragraph signature = new Paragraph("Signature du Client : ___________________________", textFont);
            signature.setSpacingBefore(50);
            document.add(signature);

            document.close();
            System.out.println("PDF généré avec succès !");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
