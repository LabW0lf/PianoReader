# Defining Notes

## 27.11.2025

---
I was thinking on making custom exceptions for things like
"STOPPED" or "RETRY". However, there aren't really many cases
where these exception would be thrown so it's just an idea for now.

---

#### updated class NOTES and PianoReader

The creation and management of notes has been
moved from PianoReader to NOTES. PianoReader now only
gives current held keys and is responsible for choosing 
if it's either a note or chord.

#### added unnecessary colored text

#### created class COMMANDS
- holds a TreeSet with all commands

basically stores all commands that can be usefull in the
experienced user interface.

