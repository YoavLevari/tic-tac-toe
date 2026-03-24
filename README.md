# Tic-Tac-Toe (Java, MVC, GUI, AI)

A fully object‑oriented Tic‑Tac‑Toe game implemented in Java using a clean **MVC architecture**, a Swing‑based GUI, 
and multiple AI difficulty levels (Random, Medium, Perfect).  
The project includes a complete **JUnit 4 test suite** covering the model, strategies, players, and controller logic.

---

## 🎮 Features

### ✔ Classic 3×3 Tic‑Tac‑Toe
Play as X or O against another human or one of several AI opponents.

### ✔ Multiple AI Difficulty Levels
- **Human** — manual input
- **Easy** — random valid moves
- **Medium** — win if possible, block if needed
- **Hard** — perfect play (minimax‑style)

### ✔ Clean MVC Architecture
- **Model** — game logic, board state, win/draw detection
- **View** — Swing GUI for board and player selection
- **Controller** — connects user actions to model updates

### ✔ Fully Tested
Includes a complete JUnit 4 test suite:
- Model tests
- Strategy tests
- Player tests
- Factory tests
- Controller logic tests
- View logic tests (non‑GUI)

---

## 🧩 Project Structure

    src/
    ├── model/
    │    ├── BasicModel.java
    │    ├── TicTacToeModel.java
    │    ├── XO.java
    │    └── board/
    │         ├── Cell.java
    │         ├── BasicCell.java
    │         └── Coordinate.java
    │
    ├── model/player/
    │    ├── Player.java
    │    ├── BasicPlayer.java
    │    ├── Strategy.java
    │    ├── RandomStrat.java
    │    ├── MediumStrat.java
    │    ├── PerfectStrat.java
    │    ├── HumanStrat.java
    │    ├── Difficulty.java
    │    └── PlayerFactory.java
    │
    ├── view/
    │    ├── TicTacToeView.java
    │    └── PlayerSelectView.java
    │
    └── controller/
         └── GuiController.java

---

## 🧠 How the Game Works

### Board
The board is a 3×3 grid of `Cell` objects.  
Each cell stores either:
- `XO.X`
- `XO.O`
- `null` (empty)

### Turns
The model alternates turns automatically:
- X always goes first
- After each move, the model checks for:
    - Win (row, column, diagonal)
    - Draw (board full, no winner)

### Strategies (AI)
Each AI implements the `Strategy` interface:

#### **RandomStrat**
Chooses any empty cell.

#### **MediumStrat**
1. Take a winning move if available
2. Block opponent’s winning move
3. Otherwise choose a random empty cell

#### **PerfectStrat**
Implements optimal play:
- Never loses
- Always wins when possible
- Forces a draw otherwise

---

## 🖥 Running the Program

### **Option 1: Using `javac` and `java`**

Compile:

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
