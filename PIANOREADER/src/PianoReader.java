import javax.print.DocFlavor;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import java.util.*;

class PianoReader implements Receiver {
    // List that stores a note for every individual key
    private static Map<Integer,String> notes = new TreeMap<Integer,String>();

    public PianoReader() {

        // === number of keys =====
        int AMOUNT_OF_KEYS = 88;
        // ========================

        // define the notes and put into map
        setNotes(AMOUNT_OF_KEYS);
    }

    private void setNotes(int AMOUNT_OF_KEYS) {
            // english notation
            final String[] NOTATION = {"a", "b", "c", "d", "e", "f", "g"};

            // for every standard 88-key MIDI piano only!!!
            int begins_from = 21;

            // just in case you are a skinwalker or a low IQ specimen
            if (AMOUNT_OF_KEYS <= 0) {
                System.out.println("Amount of keys must be greater than 0.");
                System.exit(1);
            }

            // important for note names
            int AMOUNT_OF_OCTAVES = 88 / 12;

            // putting all white keys first
            for (int i = 0; i < NOTATION.length; i++) {
                // create notes as long as we don't exceed number of keys
                for (int j = 0; begins_from < AMOUNT_OF_KEYS + 21; j++) {
                    notes.put(begins_from, NOTATION[i] + j);
                    begins_from += 12;
                }

                // because were doing white keys first
                begins_from = switch (i) {
                    // b
                    case 0 -> 23;
                    // c
                    case 1 -> 24;
                    // c
                    case 2 -> 26;
                    // d
                    case 3 -> 28;
                    // e
                    case 4 -> 29;
                    // f
                    case 5 -> 31;
                    // g
                    case 6 -> 33;
                    // a
                    default -> begins_from;
                };
            }

            // a#/bb, c#/db, d#/eb, f#/gb, g#/ab
            String[] BLACK_KEYS_AND_ENHARMONIC = {"a#/bb", "c#/db", "d#/eb", "f#/gb", "g#/ab"};






            System.out.println(notes);
            System.out.println(notes.size());
        }


    @Override
    public void send(MidiMessage message, long timeStamp) {
        // if respond of device is a ShortMessage (see entry_2)
        if (message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;

            int command = sm.getCommand(); // holds the current command number (status byte)
            int note = sm.getData1(); // data byte 1
            int velocity = sm.getData2(); // data byte 2

            if (command == ShortMessage.NOTE_ON && velocity > 0) {
                System.out.println("Note ON: " + note);
            } else if (command == ShortMessage.NOTE_OFF ||
                    (command == ShortMessage.NOTE_ON && velocity == 0)) {
                System.out.println("Note OFF: " + note);
            }
        }
    }

    // release the use of resources
    @Override
    public void close() {}
}
