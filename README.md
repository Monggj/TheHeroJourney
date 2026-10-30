# teamWork01 – Japanese-Style Map Exploration RPG

A console RPG written in Java. Move a ninja across a 7×7 map, fight monsters, use potions, and reach the goal before your HP runs out.

## Features

- 7×7 map drawn with emojis and colors in the console
- Random monster encounters with 3 monster types
- Turn-based battles: attack, use potions, or run
- Shrines that heal you and traps that hurt you
- Gold rewards for defeating monsters
- Sound effects for battles, victory, and game over (MP3)

## Map Legend

| Symbol | Meaning |
|---|---|
| 🥷 | Player |
| 🚩 | Goal |
| 🐍 | Monster |
| ⛩️ | Shrine (restores 5–12 HP) |
| ☠️ | Trap (deals 8–37 damage) |

## How to Play

**Movement**

| Key | Action |
|---|---|
| 1 | Up |
| 2 | Down |
| 4 | Left |
| 3 | Right |
| 0 | Exit |

**Battle**

| Key | Action |
|---|---|
| 1 | Attack |
| 2 | Use a potion |
| 3 | Run (ends the game) |

**Starting stats:** HP 150, ATK 20, Gold 80

**Potions:** Small (heals 35, x5), Medium (heals 55, x2), Large (heals 100, x1)

**Monsters**

| Type | HP | ATK | Gold reward |
|---|---|---|---|
| Weak | 35 | 12 | 30 |
| Normal | 60 | 18 | 50 |
| Strong | 120 | 30 | 80 |

You win by reaching the goal. You lose if your HP drops to 0.

## Requirements

- Java 8 or later
- Eclipse IDE (or any Java IDE)
- [JLayer](https://github.com/umjammer/jlayer) library (`javazoom.jl.player.Player`) for MP3 playback

## How to Run (Eclipse)

1. Clone or download this repository.
2. Import it into Eclipse as a Java project.
3. Add the JLayer `.jar` to the project's build path
   (Right-click project → Build Path → Add External Archives).
4. Make sure all `.mp3` files are in the **project root folder**, next to `src/`.
5. Run `teamWork01.java` as a Java Application.

> Note: The game loads sound files by relative path, so the `.mp3` files must stay in the project root. If a file is missing, the game will throw a `FileNotFoundException`.

## Tech Stack

- Java
- Eclipse IDE
- JLayer (MP3 playback)

## Team

- Team project (2 members) – Thinh - zin
