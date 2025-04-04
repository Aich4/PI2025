package services;

import net.sourceforge.tess4j.*;
import java.io.File;

public class OCRService {
private static final String TESSERACT_PATH = "C:/Program Files/Tesseract-OCR/tessdata"; // Modifier selon ton installation

    public String extraireTexte(File imageFile) {
        try {
            ITesseract instance = new Tesseract();
            instance.setDatapath(TESSERACT_PATH); // Spécifier le chemin d’installation
            instance.setLanguage("fra"); // Définir la langue (ex: "fra" pour le français)

            return instance.doOCR(imageFile);
        } catch (TesseractException e) {
            e.printStackTrace();  // Log de l'exception complète pour plus de détails
            return null;
        }
    }
}
