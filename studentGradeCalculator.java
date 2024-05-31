import java.util.*;

public class studentGradeCalculator{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int marks[] = new int[6];
        int total_marks = 0;
        
        for (int i = 0; i < 6; i++) {
            System.out.print("Enter the marks obtained in subject " + (i + 1) + " out of 100: ");
            marks[i] = sc.nextInt();
            total_marks += marks[i];
        }
        
        float avg_percentage = (float) total_marks / 6;
        String grade;
        
        if (avg_percentage >= 90)
            grade = "A+";
        else if (avg_percentage < 90 && avg_percentage >= 80)
            grade = "A";
        else if (avg_percentage < 80 && avg_percentage >= 70)
            grade = "B+";
        else if (avg_percentage < 70 && avg_percentage >= 60)
            grade = "B";
        else if (avg_percentage < 60 && avg_percentage >= 50)
            grade = "C";
        else if (avg_percentage < 50 && avg_percentage >= 40)
            grade = "D";
        else if (avg_percentage >= 35 && avg_percentage < 40)
            grade = "E";
        else 
            grade = "F";
        
        System.out.println("The total marks you secured are: " + total_marks);
        System.out.format("The average percentage you gained is: %.2f%%\n", avg_percentage);
        System.out.println("The overall grade you secured is: " + grade);
    }
}
