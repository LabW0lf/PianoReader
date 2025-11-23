import javax.sound.midi.*;
import java.util.Scanner;

public class PianoReaderUI {
    private static MidiDevice device = null;

    // lets the user select the device via name input
    public static void select_device() {
        Scanner scanner = new Scanner(System.in);

        boolean found = false;
        while (!found) {
            // list devices
            System.out.println("DEVICES:");
            DEVICES.getMidiDevices();

            // input
            System.out.print("Please enter your device name: ");
            String user_input = scanner.nextLine();

            // go through the list of devices
            for (int i = 0; i < DEVICES.MIDI_DEVICES.size(); i++) {
                // if the device the user inputted is on the list
                if (DEVICES.MIDI_DEVICES.get(i).getName().equals(user_input)) {
                    try {
                        // select that device
                        device = MidiSystem.getMidiDevice(DEVICES.MIDI_DEVICES.get(i));
                        found = true;
                        break;
                    }
                    // if device is found but not available
                    catch (MidiUnavailableException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            // successfully selected device
            if (found) {
                try {
                    // start device for transporting data
                    device.open();
                    System.out.println("Opened: " + device.getDeviceInfo().getName());

                    // define the transmitter of the device
                    Transmitter transmitter = device.getTransmitter();
                    // define the receiver of the device
                    Receiver receiver = new PianoReader();
                    // I don't think I have to explain what this does :P
                    transmitter.setReceiver(receiver);

                    // to keep the program running
                    Thread.sleep(Long.MAX_VALUE);
                }
                catch (MidiUnavailableException e) {
                    System.out.println("ERROR: Midi device is found but is not available!");
                }
                catch (InterruptedException e) {
                    System.out.println("STOPPED: program got interrupted!");
                }
            }
        }
    }
}