package id.nurazlib.ztebaktebakan;

public class Question {
    private String questionText;
    private String[] options;
    private String correctAnswer;
    private String hint;

    public Question(String questionText, String[] options, String correctAnswer, String hint) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.hint = hint;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getHint() {
        return hint;
    }
}
