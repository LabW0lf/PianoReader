# The Beginning

## 21.11.2025

---

After seeing the lack of interesting programming projects today at my schools
annual open house event, where the majority was just plain
good old Tetris or an unadmirable interactive showcase of
the game Minecraft, I got inspired on my own to make a project that
vastly differed from other ones I had seen today.

This is the beginning of my first ever big Java project
called PianoReader. My goal is to simply read keys that are being
played on a midi piano more specifically my stage piano RD-88
and display the corresponding note or chord in realtime. I'm also planing on maybe
adding a song detection system, that tries to figure out what song the user
might be playing at the given time. At the moment, I'm thinking on making 2
modes for notes and chords detection and song detection. Thinking of ideas on how to implement
song detection is still on the run!

---

## current progress:

#### created class DEVICES
- uses the [javax.sound.midi package](https://docs.oracle.com/javase/8/docs/api/javax/sound/midi/package-summary.html)
- holds an array of MidiDevice.Info instances
- holds a method that if called, outputs all available midi devices along with all the additional details
- cannot be instantiated from outside

Responsible for finding connected, detected and available midi devices. It also sorts
out devices that do not possess transmitters which are essential for getting data.

#### created class MENU
- uses the [javax.swing package](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html)
- holds the method start()

This class simply starts the whole program by creating a window and 
displaying the given data. Currently, it has a minimalistic design and I will
most definitely improve it after finishing backend.

#### created class PianoReaderUI
- holds a method for getting the device
- holds a method that lets the user select a midi device
- cannot be instantiated
- starts communication

An experienced user interface for choosing a midi device and starting communication. 




