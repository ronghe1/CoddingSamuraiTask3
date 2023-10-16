import java.util.Scanner;

public class GradeCalculator {
    
    // ANSI escape codes for text colors
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\033[1;32m";
    public static final String RED =  "\033[1;31m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("");

        System.out.println("WELCOME TO THE STUDENT GRADE CALCULATOR!");
        System.out.println("");

        boolean calculateAnother = true;

        while (calculateAnother) {
            double assignmentGrade = getGrade(sc, "Enter Assignment grade :: ");
            double quizGrade = getGrade(sc, "Enter Quizzes grade :: ");
            double midtermGrade = getGrade(sc, "Enter Midterm exam grade :: ");
            double finalExamGrade = getGrade(sc, "Enter Final exam grade :: ");
            double projectGrade = getGrade(sc, "Enter Project grade :: ");

            double finalGrade = calculateFinalGrade(
                assignmentGrade, quizGrade, midtermGrade, finalExamGrade, projectGrade
            );

            String letterGrade = determineLetterGrade(finalGrade);

            // Display the final result with color
            System.out.println("Your final grade is: " + getColoredGrade(finalGrade));
            System.out.println("Letter grade is: " + getColoredLetterGrade(letterGrade));

            // Ask if the user wants to calculate grades for another student
            System.out.print("Do you want to calculate grades for another student? (yes/no): ");
            String Another = sc.next();
            calculateAnother = Another.equalsIgnoreCase("yes");
            System.out.println("");

        }

        System.out.println("Thank you for using the Student Grade Calculator!");
        sc.close();
    }

    public static double getGrade(Scanner scanner, String prompt) {
        double grade = -1;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.print(prompt);
                scanner.next();
            }
            grade = scanner.nextDouble();
            if (grade < 0 || grade > 100) {
                System.out.println("Invalid input. Grade should be between 0 and 100.");
            }
        } while (grade < 0 || grade > 100);
        return grade;
    }

    public static double calculateFinalGrade(double... grades) {
        double[] weights = {0.15, 0.15, 0.3, 0.2, 0.1}; // Adjust the weights to match the number of grades
        if (grades.length != weights.length) {
            throw new IllegalArgumentException("Number of grades and weights should match.");
        }

        double finalGrade = 0;
        for (int i = 0; i < grades.length; i++) {
            finalGrade += grades[i] * weights[i];
        }
        return finalGrade;
    }

    public static String determineLetterGrade(double finalGrade) {
        if (finalGrade >= 90) {
            return "A";
        } else if (finalGrade >= 80) {
            return "B";
        } else if (finalGrade >= 70) {
            return "C";
        } else if (finalGrade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Helper method to add color to the final grade
    public static String getColoredGrade(double finalGrade) {
        if (finalGrade >= 60) {
            return GREEN + finalGrade + RESET; // Green for passing grades
        } else {
            return RED + finalGrade + RESET; // Red for failing grades
        }
    }

    // Helper method to add color to the letter grade
    public static String getColoredLetterGrade(String letterGrade) {
        if (letterGrade.equals("A") || letterGrade.equals("B") || letterGrade.equals("C")) {
            return GREEN + letterGrade + RESET; // Green for A, B, and C
        } else {
            return RED + letterGrade + RESET; // Red for D and F
        }
    }
     
}