# 🧭 Convex Hull Visualizer – Java Swing GUI

This is an interactive Java GUI application that allows users to draw points on a canvas and visualize their **convex hull** using the **Gift Wrapping (Jarvis March)** algorithm.

Built entirely with **Java Swing**, this project demonstrates algorithm visualization and basic 2D graphics programming.

---

## 🎯 Features

- 🖱️ Click to add points on a canvas
- 🔁 Press “Convex Hull” to compute and draw the convex polygon
- 🧹 Press “Clear” to reset the canvas
- 🎨 Real-time drawing using `Graphics2D`

---

## 🛠️ Technologies Used

- Java SE (Standard Edition)
- Java Swing GUI
- 2D Graphics
- Gift Wrapping algorithm

---

## 🚀 How to Run

### Option 1: Run the JAR file (recommended)

1. Download `ConvexHullApp.jar`
2. Run it:

   ```bash
   java -jar ConvexHullApp.jar
   ```

   Requires Java 8 or later

---

### Option 2: Compile from Source

```bash
cd src
javac *.java
java ConvexHull
```


---

## 🧠 Algorithm Overview

This project uses the **Gift Wrapping (Jarvis March)** algorithm to compute the convex hull. It begins from the leftmost point and iteratively selects the most counter-clockwise point until it wraps around to the start.

- ✅ Handles colinear edge cases  
- 💻 Time complexity: `O(nh)` where `n` is the number of total points and `h` is the number of points on the hull

---

## 🙋‍♂️ Author

**Mudit Mayank Jha**  
GitHub: [@muditjha20](https://github.com/muditjha20)
