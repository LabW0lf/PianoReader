import javax.sound.midi.*;

import java.util.ArrayList;
import java.util.Arrays;

public class DEVICES {
    // list of all available detected MIDI devices (updates when getMidiDevices is called)
    // A MidiDevice.Info object contains assorted data about a MidiDevice, including its name, the company who created it, and descriptive text.
    public static final ArrayList<MidiDevice.Info> MIDI_DEVICES = new ArrayList<>();

    // So that this class cannot be instantiated from another class
    private DEVICES() {
    }

    // updates the list of all available midi devices
    private static void findMidiDevices() {
        // get all available Midi Devices in an array first
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

        // get rid of devices that do not have transmitters
        for (int x = 0; x < infos.length; x++) {
            try {
                if (MidiSystem.getMidiDevice(infos[x]).getMaxTransmitters() != 0 && !MIDI_DEVICES.contains(infos[x])) {
                    MIDI_DEVICES.add(infos[x]);
                }
            }
            catch (MidiUnavailableException _) {
                // do nothing
            }
        }
    }

    // outputs all available midi devices with their corresponding details
    public static void getMidiDevices() {
        findMidiDevices();
        for (int i = 0; i < MIDI_DEVICES.size(); i++) {
            System.out.println("----------------------------");
            System.out.println(MIDI_DEVICES.get(i).getName());
            System.out.println("\t" + MIDI_DEVICES.get(i).getDescription());
            System.out.println("\t" + MIDI_DEVICES.get(i).getVendor());
            System.out.println("\t" + MIDI_DEVICES.get(i).getVersion());
            if (i == MIDI_DEVICES.size() - 1) {
                System.out.println("-----------------------------");
            }
        }
    }
}
