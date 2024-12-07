# Homework4 ARCANE-Game Expansion

### Team Members:

1. Artemis Shaw
2. James Gashi
3. Tyler Kloster

---
### Java Version:
openjdk 17.0.9 2023-10-17

---
### Comments / Descriptions:
The output section looks really crowded, and thus have been replaced with screenshots instead of copy-pasted output.
In this snapshot, we have added the following features:
1. New expanded game characters, including a new creature type "Demon" and new Adventurer types "Knight","Coward", and "Glutton"
   
   1. Demons are like boss fights--they always fight all adventurers, even if there are multiple adventurers (initial health score: 15)
   2. Knights always fight any creatures (initial health score: 8)
   3. Cowards always run if possible, but lose .5 health each time (initial health score: 5)
   4. Gluttons always eat, unless they're fighting a demon (initial health: 3)
   
6. Builder pattern that creates different grid-sized mazes and enables users to place pre-made objects into specific rooms
7. Factory pattern that creates 3 factories: AdventureFactory, CreatureFactory, and FoodFactory

### UML Diagram of Classes:
<img src="screenshots/UML.png" alt="Test coverage">

### Test Coverage:
<img src="screenshots/TestCoverageSS.png" alt="Test coverage">

### Game Outputs:

#### Running the Pre-Updated Game (Run #1):
<img src="screenshots/test-1/a1.png" alt="Test coverage">
<img src="screenshots/test-1/a2.png" alt="Test coverage">
<img src="screenshots/test-1/a3.png" alt="Test coverage">
<img src="screenshots/test-1/a4.png" alt="Test coverage">
<img src="screenshots/test-1/a5.png" alt="Test coverage">
<img src="screenshots/test-1/a6.png" alt="Test coverage">
<img src="screenshots/test-1/a7.png" alt="Test coverage">
<img src="screenshots/test-1/a8.png" alt="Test coverage">
<img src="screenshots/test-1/a9.png" alt="Test coverage">


#### Running the Pre-Updated Game (Run #2):
<img src="screenshots/test-2/b1.png" alt="Test coverage">
<img src="screenshots/test-2/b2.png" alt="Test coverage">
<img src="screenshots/test-2/b3.png" alt="Test coverage">
<img src="screenshots/test-2/b4.png" alt="Test coverage">
<img src="screenshots/test-2/b5.png" alt="Test coverage">
<img src="screenshots/test-2/b6.png" alt="Test coverage">
<img src="screenshots/test-2/b7.png" alt="Test coverage">
<img src="screenshots/test-2/b9.png" alt="Test coverage">
<img src="screenshots/test-2/b10.png" alt="Test coverage">
<img src="screenshots/test-2/b11.png" alt="Test coverage">
<img src="screenshots/test-2/b12.png" alt="Test coverage">
<img src="screenshots/test-2/b13.png" alt="Test coverage">
<img src="screenshots/test-2/b14.png" alt="Test coverage">
<img src="screenshots/test-2/b15.png" alt="Test coverage">
<img src="screenshots/test-2/b16.png" alt="Test coverage">
<img src="screenshots/test-2/b17.png" alt="Test coverage">

#### Running the Pre-Updated Game (Run #3):
<img src="screenshots/test-3/c1.png" alt="Test coverage">
<img src="screenshots/test-3/c2.png" alt="Test coverage">
<img src="screenshots/test-3/c3.png" alt="Test coverage">
<img src="screenshots/test-3/c4.png" alt="Test coverage">
<img src="screenshots/test-3/c5.png" alt="Test coverage">
<img src="screenshots/test-3/c6.png" alt="Test coverage">
<img src="screenshots/test-3/c7.png" alt="Test coverage">
<img src="screenshots/test-3/c8.png" alt="Test coverage">
<img src="screenshots/test-3/c9.png" alt="Test coverage">
