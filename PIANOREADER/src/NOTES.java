import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class NOTES {
    // key that is currently being pressed
    private static final ArrayList<Integer> pressing_key = PianoReader.getHELD_KEYS();

    // List that stores a note for every individual key
    private static final Map<Integer, String> notes = new TreeMap<>();


    public static Map<Integer, String> getNotes() {
        return notes;
    }

    public static String show_note() {
        if (pressing_key.size() != 1) {
            return "";
        }

        String result = "";

        // result is the value of the key pressing_key
        String note = notes.get(pressing_key.get(0));

        // if it's a black key
        if (note.contains("/")) {
            // first enharmonic + octave number
            result = note.substring(0, 2).toUpperCase() + note.charAt(note.length() - 1);

            result += " OR ";

            // second enharmonic
            result += note.substring(3, 4).toUpperCase() + note.charAt(4) + note.charAt(5);
        } else {
            result += note.toUpperCase();
        }

        System.out.println(result);

        // result should look something like "A# OR Bb"
        return result;
    }

    public static void setNotes(int AMOUNT_OF_KEYS) {
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
            System.out.println("\u001B[31mERROR, number of mapped notes do not match amount of keys.\u001B[0m");
            System.exit(-1);
        }
    }
}
