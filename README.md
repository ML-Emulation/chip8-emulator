ML Chip-8 emulator
====

MissingLink aims to open up a new way to play old games!

What is new about MissingLink?
--------

From time to time, don't you feel an urge to pick your old dusty Console and play those awesome games you used to love as a kid? It doesn't matter how many years go by, you will probably continue to do that for a long time. Nevertheless, even if those games were AWESOME at the time, we all know they could be a bit better and that is the reason behind MissingLink (ML for short).

It seems meaningless to simply develop new games to try and copycat old titles that still engage us on long sleepless nights of gaming. Instead, MissingLink attempts to add new features to existing games - like online play.


How does it work?
--------

As we don't have access to the game's source code, we cannot simply code these features in as we'd like. What we can do, however, is reverse-engineer some of the instructions that trigger certain behaviours in a game. Taking advantage of the fact an emulator runs on machine with internet access, we can broadcast what is happening on a player's game to everyone else - be it people on the same server or your friends.


Limitations
--------

Every game works differently, so one game's instructions for a certain behaviour will probably be unique. Having said that, MissingLink can support any game we decide to reverse-engineer but that effort is game-specific.


Future
--------

For the time being, MissingLink's emulators will be provided with out-of-the-box support for the first few games. This is not ideal, as it would cause unnecessary bloat for our users, so that will eventually be changed - allowing every user to download features for a certain game.


License
---------

MissingLink software is distributed under the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html). A copy of the license is available in the distributed LICENSE file.