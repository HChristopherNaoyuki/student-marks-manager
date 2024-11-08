package Solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Question2 extends JFrame implements ActionListener
{
    private JMenuItem loadDataMenu, saveDataMenu, exitMenu;
    private JMenuItem calculateAverageMenu, highestMarkMenu, printReportMenuItem;
    private JTextField nameField, mark1Field, mark2Field, mark3Field;
    private JTextArea reportArea;
    private ArrayList<String> names;
    private ArrayList<Integer> marks1, marks2, marks3;

    public Question2()
    {
        // Set Look and Feel
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        setTitle("Mark Processor");
        setLayout(new BorderLayout());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize data storage
        names = new ArrayList<>();
        marks1 = new ArrayList<>();
        marks2 = new ArrayList<>();
        marks3 = new ArrayList<>();

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        loadDataMenu = new JMenuItem("Load Data");
        saveDataMenu = new JMenuItem("Save Data");
        exitMenu = new JMenuItem("Exit");

        loadDataMenu.addActionListener(this);
        saveDataMenu.addActionListener(this);
        exitMenu.addActionListener(this);
        
        fileMenu.add(loadDataMenu);
        fileMenu.add(saveDataMenu);
        fileMenu.add(exitMenu);

        // Report Menu
        JMenu printReportMenu = new JMenu("Print Report");
        calculateAverageMenu = new JMenuItem("Calculate Average");
        highestMarkMenu = new JMenuItem("Highest Mark");
        printReportMenuItem = new JMenuItem("Print Report");

        calculateAverageMenu.addActionListener(this);
        highestMarkMenu.addActionListener(this);
        printReportMenuItem.addActionListener(this);

        printReportMenu.add(calculateAverageMenu);
        printReportMenu.add(highestMarkMenu);
        printReportMenu.add(printReportMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(printReportMenu);

        setJMenuBar(menuBar);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Mark 1:"));
        mark1Field = new JTextField();
        inputPanel.add(mark1Field);

        inputPanel.add(new JLabel("Mark 2:"));
        mark2Field = new JTextField();
        inputPanel.add(mark2Field);

        inputPanel.add(new JLabel("Mark 3:"));
        mark3Field = new JTextField();
        inputPanel.add(mark3Field);

        add(inputPanel, BorderLayout.NORTH);

        // Report area
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        reportArea.setBackground(new Color(245, 245, 245));
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        // Initially disable Save Data
        saveDataMenu.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == loadDataMenu)
        {
            loadData();
            loadDataMenu.setEnabled(false);
            saveDataMenu.setEnabled(true);
        }
        else if (e.getSource() == saveDataMenu)
        {
            saveData();
        }
        else if (e.getSource() == exitMenu)
        {
            confirmExit();
        }
        else if (e.getSource() == calculateAverageMenu)
        {
            calculateAverage();
        }
        else if (e.getSource() == highestMarkMenu)
        {
            highestMark();
        }
        else if (e.getSource() == printReportMenuItem)
        {
            printReport();
        }
    }

    private void loadData()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("MyMarks.txt")))
        {
            String line;
            names.clear();
            marks1.clear();
            marks2.clear();
            marks3.clear();

            while ((line = reader.readLine()) != null)
            {
                String[] data = line.split(",");
                names.add(data[0].trim());
                marks1.add(Integer.parseInt(data[1].trim()));
                marks2.add(Integer.parseInt(data[2].trim()));
                marks3.add(Integer.parseInt(data[3].trim()));
            }

            if (!names.isEmpty())
            {
                nameField.setText(names.get(0));
                mark1Field.setText(marks1.get(0).toString());
                mark2Field.setText(marks2.get(0).toString());
                mark3Field.setText(marks3.get(0).toString());
            }

            JOptionPane.showMessageDialog(this, "Data loaded successfully.", "Load Data", JOptionPane.INFORMATION_MESSAGE);
            appendToReport("Data loaded successfully.\n");
        }
        catch (IOException ex)
        {
            appendToReport("Error loading data: " + ex.getMessage() + "\n");
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "Error: Please ensure all marks are numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveData()
    {
        try
        {
            String name = nameField.getText().trim();
            int mark1 = Integer.parseInt(mark1Field.getText().trim());
            int mark2 = Integer.parseInt(mark2Field.getText().trim());
            int mark3 = Integer.parseInt(mark3Field.getText().trim());

            names.add(name);
            marks1.add(mark1);
            marks2.add(mark2);
            marks3.add(mark3);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("MyReport.txt")))
            {
                writer.write(reportArea.getText());
            }

            JOptionPane.showMessageDialog(this, "Data saved successfully.", "Save Data", JOptionPane.INFORMATION_MESSAGE);
            appendToReport("Data added and saved successfully.\n");
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for marks.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex)
        {
            appendToReport("Error saving data: " + ex.getMessage() + "\n");
        }
    }

    private void calculateAverage()
    {
        if (!marks1.isEmpty())
        {
            int totalMarks = marks1.stream().mapToInt(Integer::intValue).sum() +
                             marks2.stream().mapToInt(Integer::intValue).sum() +
                             marks3.stream().mapToInt(Integer::intValue).sum();
            int totalEntries = marks1.size() * 3;
            double avg = totalMarks / (double) totalEntries;
            JOptionPane.showMessageDialog(this, String.format("Average mark: %.2f", avg), "Calculate Average", JOptionPane.INFORMATION_MESSAGE);
            appendToReport(String.format("Average mark: %.2f\n", avg));
        }
        else
        {
            appendToReport("No data loaded.\n");
        }
    }

    private void highestMark()
    {
        if (!marks1.isEmpty())
        {
            int maxMark = Math.max(
                Math.max(marks1.stream().mapToInt(Integer::intValue).max().orElse(0),
                         marks2.stream().mapToInt(Integer::intValue).max().orElse(0)),
                marks3.stream().mapToInt(Integer::intValue).max().orElse(0)
            );
            JOptionPane.showMessageDialog(this, "Highest mark: " + maxMark, "Highest Mark", JOptionPane.INFORMATION_MESSAGE);
            appendToReport("Highest mark: " + maxMark + "\n");
        }
        else
        {
            appendToReport("No data loaded.\n");
        }
    }

    private void printReport()
    {
        if (!names.isEmpty())
        {
            StringBuilder report = new StringBuilder("Student Report:\n");
            for (int i = 0; i < names.size(); i++)
            {
                report.append("Name: ").append(names.get(i)).append("\n");
                report.append("Mark 1: ").append(marks1.get(i)).append("%\n");
                report.append("Mark 2: ").append(marks2.get(i)).append("%\n");
                report.append("Mark 3: ").append(marks3.get(i)).append("%\n\n");
            }
            reportArea.setText(report.toString());
        }
        else
        {
            appendToReport("No data loaded.\n");
        }
    }

    private void confirmExit()
    {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }

    private void appendToReport(String message)
    {
        reportArea.append(message);
        reportArea.setCaretPosition(reportArea.getDocument().getLength());
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            new Question2().setVisible(true);
        });
    }
}
