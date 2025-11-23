# Transmit and Receive

## 22.11.2025

---

I have finished the part for communication. When I press keys, my program
can read it in realtime. I updated class DEVICES so the Array only holds 
devices that HAVE transmitters. In my case the RD-88 shows -1 transmitters
which means it holds infinite transmitters.

I get numbers outputted for every key that I press. To turn them into notes, 
I have decided to use a HashMap which holds 2 pieces of datum per element. An
Integer and a String will be assigned for every element which is seen as a single note.
Since my RD-88 has 88 keys like every standard piano or full sized piano, obviously,
the map will have 88 elements in total.

---

## current progress:

#### created class PianoReader]()

- implements the class [Receiver](https://docs.oracle.com/javase/8/docs/api/javax/sound/midi/Receiver.html)
- holds HashMap for all individual notes
- holds the method [send](https://docs.oracle.com/javase/8/docs/api/javax/sound/midi/Receiver.html)
- [ShortMessage](https://docs.oracle.com/javase/8/docs/api/javax/sound/midi/ShortMessage.html)

This class is generally responsible for reading MIDI in Java
and will correctly interpret note-on and note-off messages. You can edit
this class to make it work for your midi device.