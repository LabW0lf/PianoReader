import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        PianoReaderUI.select_device();
        // Info test = new Info("MidiPiano","SomeVendor","This is a description.", 1.0);
    }
}