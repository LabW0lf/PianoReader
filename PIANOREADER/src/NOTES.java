import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class NOTES {
    // key that is currently being pressed
    private static final ArrayList<Integer> pressing_key = PianoReader.getHELD_KEYS();
    private static final Map<Integer, String> notes = PianoReader.getNotes();


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
            result += note.substring(3,4).toUpperCase() + note.charAt(4) + note.charAt(5);
        } else {
            result += note.toUpperCase();
        }

        System.out.println(result);

        // result should look something like "A# OR Bb"
        return result;
    }
}
