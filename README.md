# Student Marks Manager

**Student Marks Manager** is a Java-based desktop application that helps in managing and processing student marks. It allows users to load and save data, calculate averages, identify the highest mark, and print detailed reports. The application uses the Swing library for its graphical user interface (GUI) and handles file input/output operations for storing and processing student data.

## Features

- **Load Data**: Load student marks from a text file (`MyMarks.txt`).
- **Save Data**: Save the current marks and report to a file (`MyReport.txt`).
- **Calculate Average**: Calculate the average mark across all students and display it.
- **Find Highest Mark**: Identify and display the highest mark among all students.
- **Print Report**: Print a detailed student report including names and marks.

## Requirements

- Java 8 or higher
- An IDE (e.g., IntelliJ IDEA, Eclipse) or JDK setup for compiling and running the project.

## How to Use

1. **Load Data**:  
   Click **File > Load Data** to load student marks from `MyMarks.txt`. This file should be formatted with comma-separated values (name, mark1, mark2, mark3) for each student.

2. **Enter Marks**:  
   You can manually enter the student name and marks in the provided fields at the top of the window.

3. **Save Data**:  
   After entering or modifying data, click **File > Save Data** to save the current data to `MyReport.txt`.

4. **Calculate Average**:  
   Click **Print Report > Calculate Average** to view the average of all entered marks.

5. **Find Highest Mark**:  
   Click **Print Report > Highest Mark** to find and display the highest mark among all students.

6. **Print Report**:  
   Click **Print Report > Print Report** to view a detailed list of all students with their respective marks.

## File Format for Data

The data file (`MyMarks.txt`) should be in the following format:

```
John Doe, 85, 90, 78
Jane Smith, 92, 88, 84
Alice Johnson, 79, 85, 91
```

Each line represents one student's data:
- **Name**: The student's full name.
- **Mark 1, Mark 2, Mark 3**: The marks obtained in three subjects, separated by commas.

## Example Output

After loading the data and printing a report, the application will display something like:

```
Student Report:

Name: John Doe
Mark 1: 85%
Mark 2: 90%
Mark 3: 78%

Name: Jane Smith
Mark 1: 92%
Mark 2: 88%
Mark 3: 84%

...
```

## Installation

1. Clone the repository or download the source code.
   ```bash
   git clone https://github.com/HChristopherNaoyuki/student-marks-manager.git
   ```

2. Open the project in your IDE.

3. Compile and run the `Question2.java` file.

4. The GUI should launch, and you can start using the application.


---
