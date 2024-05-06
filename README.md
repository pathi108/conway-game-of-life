# Conway Game of Life

## Introduction
This is a command-line project that simulates Conway's Game of Life, producing the next 100 states based on an initial state provided as a CSV file. The game is played on a 200x200 grid.
This project will deliver 3 deliverables upon  a successful build which are
* The application jar file
* A jar file of the application with dependencies.
* The application distribution as a zip file

## Installation and Usage

### Build From Source Code
This project is built with Maven. To build, navigate to the project directory and run:
```
mvn clean install
```

### Running the Application

#### Using an IDE:
1. Import the project into your preferred IDE.
2. Run the application with the input file path as a Program argument.

#### Command Line:
Run the following command using the executable jar - make sure you use only the jar file with dependencies:

```
java -jar lib/conway-game-of-life-1.0-SNAPSHOT-jar-with-dependencies.jar {path to the input file}
```

#### Using the Distribution:
1. After a successful build, locate the distribution ZIP file in the `target` folder (e.g., `conway-game-of-life-{VERSION}-distribution.zip`).
2. Unzip the folder.
3. Inside the unzipped folder, you'll find:
   - `input/input.csv`: Use this CSV file to input the initial live cells.
   - `lib`: Contains the application JAR.
   - `start.bat`: Execute this file to run the application on Windows.
   - `start.sh`: Execute this file to run the application on Unix-based systems.

## Input Format
The application expects a CSV file as input. Each line of the file represents a point in the grid with x,y coordinates of live cells.

### Example:
For live cells `[[2,1][3,2][3,3][2,3][1,3]]`, the CSV file should look like:
|   |   |
|---|---|
|2|1|
|3|2|
|3|3|
|2|3|
|1|3|
## Output
The application generates the next 100 states of Conway's Game of Life based on the provided initial state.
