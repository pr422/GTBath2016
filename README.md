# GTBath2016

Monster VS Explorer

========================================
		Abstract	
========================================
	
Monster vs Explorer is a game that encourages exploration.
The goal is to defeat all 6 monsters and obtain 6 objects.
	
The map consists of 16 rooms, each of the room will have
at least 1 door to the next room and up to 1 monster.

Player (explorer) can move through the room by using W,A,S,D
key. The coin in the map will be automatically picked up
when the player is directly over it. The coin is used to
purchase block (attack block) and health potion.

Block (attack block) costs 3 coins, it allows player to
block an incoming attack from the monster thus the health
of the player will remain the same. Blocks must be purchased
before player starts battle with the monster. Blocks will
be used automatically until it runs out. 

Health potion costs 1 coin, restoring 10 Health point. It
can only be purchased during the battle but player can
keep unused health potion from one battle to the next.
Player can choose to use health point during the battle.

The damage dealt by both Monster and Explorer is a random
number 10-25 points. This damage will reduce the health of
the respective opponents. 

If monster runs out of health first, explorer wins and 
explorer obtains the object held by the monster. 
If explorer runs out of health first, the game will quit.

There are two tiers of monster. Standard and Boss tier.
Standard monster have health of 80-120.
Boss monster have health of 200.
Player have health of 100.

Thus, blocks and health potion must be used wisely in order
to defeat monsters.

Enjoy.

======================================
		About 		
======================================

This game is created by a developer team of two.
Naphatr Bootsobha (Napat) and Pornpanit Rasivisuth (Jiew)
We are first year Computer Science students at University of Bath.
We used the knowledge that we learnt during the first year to
create this game.

We created this game at GameTale 2016 at University of Bath.
This game was created in 48 hours by only two of us, it is
far from solid, but it is something. There are many bugs
as we had to rush it and the code smells. 

Fun Fact: We have 2788 lines of uncommented code. Check it out
yourself, navigate to src folder and "git ls-files | xargs wc -l"

=======================================
		Known Bugs	
=======================================
Here we gather list of known bugs in the game

1.
Title: NullPointerException on start.
Fix: Don't worry, just restart the game.
Explanation: Game seeded monster in the room that the player spawns.
	     something happens and we dont know why it doesnt work. 

2.
Title: Duplicate Object.
Fix: see improvement
Explanation: When the monster is created, it is given a random object.
	     No measure was implemented to prevent used object to be
	     assigned to new monster.

========================================
	Possible Improvements		
========================================
1.
Title: Duplicate Object.
Suggestion: Delete the object from array of object each time the
	    object is used to assign to monster.

2.
Title: Different Color Monster
Suggestion: Use different color for each monster. In asset/components
	    folder we have 4 monsters of different color.

3.
Title: Graceful Game Over
Suggestion: When the player is defeated by the monster (player health
	    runs out first), a message popup could appear to let player
	    knows that they have been defeated and the game will exit. 

========================================
		Compile
========================================
This repository is a repository of eclipse project.
It can be imported to Eclipse IDE. Eclipse Mars 2 was used to develop.
JRE version 1.7 was used.
To Compile:

1) Navigate to src folder
	
	$ cd src/

2) Compile

	$ javac PlayGame.java

3) Setup Resource: Map
   copy Map folder to src folder

	$ cp ../maps/ -r .
4) Setup Resource: Assets (images)
   copy asset folder to to src/view/ folder
	
	$ cp ../asset/ -r view/

========================================
		Run
========================================
Make sure Java is installed before running

To Run:

1) Navigate to src folder

	$ cd src/

2) Run using Java

	$ java PlayGame

========================================
		Contact
========================================
Naphatr Bootsobha - nb582@bath.ac.uk
Pornpanit Rasivisuth - pr422@bath.ac.uk
