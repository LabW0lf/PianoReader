import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import java.util.*;

class PianoReader implements Receiver {

    // Array that holds all keys that are currently being pressed down
    private static final ArrayList<Integer> HELD_KEYS = new ArrayList<>();


    public static ArrayList<Integer> getHELD_KEYS() {
        return HELD_KEYS;
    }

    public PianoReader() {

        // === number of keys =====
        int AMOUNT_OF_KEYS = 88;
        // ========================

        NOTES.setNotes(AMOUNT_OF_KEYS);
        System.out.println(NOTES.getNotes());

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
