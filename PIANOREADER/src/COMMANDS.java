import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class COMMANDS {
    // all saved commands
    public static TreeMap<String, Method> commands = new TreeMap<>();

    static {
        try {
            commands.put("help", COMMANDS.class.getMethod("help"));
            commands.put("quit", COMMANDS.class.getMethod("quit"));
        } catch (NoSuchMethodException _) {
        }
    }


    public static boolean isCommand(String command) {
        return commands.containsKey(command);
    }

    public static void runCommand(String command) {
        Method m = commands.get(command);
        System.out.println();

        if (m == null) {
            System.out.println("Unknown command: " + command);
            return;
        }

        try {
            m.invoke(null); // Für static-Methoden
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void help() {
        System.out.println("""
                Welcome to my school project PianoReader!\s
                With this program, you can find out what note or chord you are playing by pressing \
                keys on your midi piano. You are currently in the experienced user interface for \
                choosing the midi device.\s
                Here's a list of all commands:""");

        System.out.println(commands.keySet());
    }

    public static void quit() {
        System.exit(0);
    }


}
