import java.util.*;
import java.util.concurrent.*;
class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public boolean isCorrect(int answerIndex) {
        return correctOptionIndex == answerIndex;
    }
}

public class Quiz {
    private List<Question> questions;
    private int score;
    private List<Boolean> results;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
        this.results = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();

            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            Future<Integer> future = executor.submit(() -> {
                System.out.print("Your answer (1-" + options.size() + "): ");
                return scanner.nextInt() - 1;
            });

            try {
                int answerIndex = future.get(10, TimeUnit.SECONDS);

                if (question.isCorrect(answerIndex)) {
                    System.out.println("Correct!");
                    score+=100;
                    results.add(true);
                } else {
                    System.out.println("Incorrect.");
                    results.add(false);
                }
            } catch (TimeoutException e) {
                System.out.println("Time's up!");
                results.add(false);
                future.cancel(true);
            } catch (Exception e) {
                System.out.println("Invalid input.");
                results.add(false);
            }

            System.out.println();
        }

        executor.shutdown();
        displayResults();
    }

    private void displayResults() {
        System.out.println("Quiz Over!");
        System.out.println("Your score: " + score + "/" + questions.size());

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println(question.getQuestionText());
            System.out.println("Correct answer: " + question.getOptions().get(question.getCorrectOptionIndex()));
            System.out.println("Your answer was: " + (results.get(i) ? "Correct" : "Incorrect"));
            System.out.println();
        }
        System.out.println("Your score is:"+score);
    }

    public static void main(String[] args) {
        List<Question> questions = Arrays.asList(
            new Question("What is the capital of France?", Arrays.asList("Berlin", "Madrid", "Paris", "Rome"), 2),
            new Question("What is 2 + 2?", Arrays.asList("3", "4", "5", "6"), 1),
            new Question("Who wrote 'Hamlet'?", Arrays.asList("Charles Dickens", "Mark Twain", "William Shakespeare", "Jane Austen"), 2),
            new Question("Which cricketer is having the heighest Tik-Tok followers?", Arrays.asList("Dohni", "David Warner", "Virat Kohli", "Sachin Tendulkar"),2)
        );

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}

