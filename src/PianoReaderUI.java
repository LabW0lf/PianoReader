import javax.sound.midi.MidiDevice;
import java.util.Scanner;

public class PianoReaderUI {
    private static MidiDevice.Info piano;

    private PianoReaderUI() {
    }

    // find out what device is going to be used
    public static void select_device() {
        Scanner sc = new Scanner(System.in);
        DEVICES.getMidiDevices();

        boolean midi_doesnt_exist = true;
        while (midi_doesnt_exist) {
            System.out.print("Please Select a MIDI Device: ");
            String user_input = sc.nextLine();

            for (int i = 0; i < DEVICES.MIDI_DEVICES.size(); i++) {
                if ( (DEVICES.MIDI_DEVICES.get(i).getName()).equals(user_input) ) {
                    piano = DEVICES.MIDI_DEVICES.get(i);
                    midi_doesnt_exist = false;
                }
            }
        }


    }
}
