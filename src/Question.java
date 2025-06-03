import java.util.List;

public class Question {

    private String questionText;
    private List<String> choices;
    private int correctAnswerIndex;
    private Questions.QuestionType type;

    public Question(String questionText, List<String> choices, int correctAnswerIndex, Questions.QuestionType type) {
        this.questionText = questionText;
        this.choices = choices;
        this.correctAnswerIndex = correctAnswerIndex;
        this.type = type;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getChoices() {
        return choices;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public Questions.QuestionType getType() {
        return type;
    }

    public boolean isCorrect(int userAnswerIndex) {
        return userAnswerIndex == correctAnswerIndex;
    }

}
