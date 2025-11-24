import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import java.util.*;

class PianoReader implements Receiver {
    // List that stores a note for every individual key
    private static final Map<Integer,String> notes = new TreeMap<>();

    // Array that holds all keys that are currently being pressed down
    private static final ArrayList<Integer> HELD_KEYS = new ArrayList<>();


    public static ArrayList<Integer> getHELD_KEYS() {
        return HELD_KEYS;
    }

    public static Map<Integer, String> getNotes() {
        return notes;
    }

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

        System.out.println("starting mapping white keys...");

        // putting all white keys first
        for (int i = 0; i < NOTATION.length; i++) {
            // create notes as long as we don't exceed number of white keys
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
                default -> 21;
            };
        }

        System.out.println("finished mapping white keys...");

        begins_from = 22;

        // a#/bb, c#/db, d#/eb, f#/gb, g#/ab
        String[] BLACK_KEYS_AND_ENHARMONIC = {"a#/bb", "c#/db", "d#/eb", "f#/gb", "g#/ab"};

        System.out.println("starting mapping black keys...");

        // 0 to 4
        for (int i = 0; i < BLACK_KEYS_AND_ENHARMONIC.length; i++) {
            // create notes as long as we don't exceed number of black keys
            for (int j = 0; begins_from < AMOUNT_OF_KEYS + 21; j++) {
                notes.put(begins_from, BLACK_KEYS_AND_ENHARMONIC[i] + j);
                begins_from += 12;
            }

            begins_from = switch (i) {
                // c#
                case 0 -> 25;
                // d#
                case 1 -> 27;
                // f#
                case 2 -> 30;
                // g#
                case 3 -> 32;
                // a#
                default -> 22;
            };
        }

        System.out.println("-------------------------------------------------");
        System.out.println("finished mapping all notes...");

        System.out.println("Total: " + notes.size());

        if (notes.size() != AMOUNT_OF_KEYS) {
            System.out.println("ERROR, number of mapped notes do not match amount of keys.");
            System.exit(-1);
        }

        System.out.println();
        System.out.println();
        System.out.println("=============================================");
        System.out.println("     WELCOME TO THE PIANO READER!");
        System.out.println("Please play something on your midi piano.");
        System.out.println("==============================================");

        MENU.start();
    }


    @Override
    public void send(MidiMessage message, long timeStamp) {
        // if respond of device is a ShortMessage (see entry_2)
        if (message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;

            int command = sm.getCommand(); // holds the current command number (status byte)
            int note = sm.getData1(); // data byte 1
            int velocity = sm.getData2(); // data byte 2

            // add note to list if pressed down
            if (command == ShortMessage.NOTE_ON && velocity > 0) {
                HELD_KEYS.add(note);

                // findout what note it is if only 1 key is currently being pressed down
                if (HELD_KEYS.size() == 1) {
                    NOTES.show_note();
                }

            // remove note from list when no longer pressed down
            } else if (command == ShortMessage.NOTE_OFF) {
                HELD_KEYS.remove(HELD_KEYS.indexOf(note));

                if (HELD_KEYS.size() == 1) {
                    NOTES.show_note();
                }
            }
        }
    }

    // release the use of resources
    @Override
    public void close() {}
}
