package Controllers;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeechUtil {
    private static final String VOICE_NAME = "kevin16"; // Try "kevin" if needed
    private Voice voice;

    public TextToSpeechUtil() {
        System.out.println("Initializing Voice Manager...");

        // Manually load voices
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        VoiceManager voiceManager = VoiceManager.getInstance();

        // Print available voices
        System.out.println("Available voices:");
        Voice[] voices = voiceManager.getVoices();
        if (voices.length == 0) {
            System.err.println("❌ No voices found! Check JAR files and classpath.");
        } else {
            for (Voice v : voices) {
                System.out.println(" - " + v.getName());
            }
        }

        // Try to get and allocate voice
        voice = voiceManager.getVoice(VOICE_NAME);
        if (voice != null) {
            voice.allocate();
            System.out.println("✅ Voice initialized: " + VOICE_NAME);
        } else {
            System.err.println("❌ Voice not found: " + VOICE_NAME);
        }
    }

    public void speak(String text) {
        if (voice != null) {
            voice.speak(text);
        } else {
            System.err.println("❌ Voice is not initialized.");
        }
    }

    public void deallocate() {
        if (voice != null) {
            voice.deallocate();
        }
    }
}
