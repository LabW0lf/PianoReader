# Defining Notes

## 23.11.2025

---

I have finished establishing communication to the piano, so I'm now moving on to 
defining notes to each individual key. To do that
I have made it so when the Receiver class PianoReader is instantiated, it not only
calls the implemented method send, but also its constructor. This is important because 
first of all, it is the first method that is called during instantiation which makes it more
secure than just calling any other method. Secondly, the map that should hold the keys 
with their corresponding note must be filled for later use.

It's also responsible for configruations like key amount and notation.
I'm thinking on making a config file for that later on.

---

## current progress:

#### created constructor for class PianoReader
- holds essential infos for the map
- private method that sets notes for the map

---

### Logic and how the map elements work:


    (Integer, String)
---
    60="c3" 
&rarr; c natural in third octave

    61="c#3/db3"
&rarr; c sharp in third octave OR d flat in third octave;

    62="d3"
&rarr; d natural in third octave;
\
\
\
As you might have already noticed, I've implemented enharmonic equalism
where a note, can have alternative names. There is also double flat or double sharp
however I will stick to enharmonics exclusively for the black keys only because of simplicity reasons. 


