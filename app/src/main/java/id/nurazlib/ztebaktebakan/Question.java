package id.nurazlib.ztebaktebakan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private String questionText;
    private List<String> options;
    private String correctAnswer;
    private String hint;

    public Question(String questionText, List<String> options, String correctAnswer, String hint) {
        if (questionText == null || questionText.isEmpty()) {
            throw new IllegalArgumentException("Teks pertanyaan tidak boleh kosong.");
        }
        if (options == null || options.isEmpty() || options.size() < 2) {
            throw new IllegalArgumentException("Pilihan jawaban harus memiliki minimal 2 pilihan.");
        }
        if (correctAnswer == null || correctAnswer.isEmpty()) {
            throw new IllegalArgumentException("Jawaban benar tidak boleh kosong.");
        }
        if (!options.contains(correctAnswer)) {
            throw new IllegalArgumentException("Jawaban benar harus ada di dalam pilihan jawaban.");
        }

        this.questionText = questionText;
        this.options = new ArrayList<>(options);
        this.correctAnswer = correctAnswer;
        this.hint = hint;

        shuffleOptions();
    }

    public Question(String questionText, String[] options, String correctAnswer, String hint) {
        this(questionText, List.of(options), correctAnswer, hint);
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getHint() {
        return hint;
    }

    public boolean isCorrectAnswer(String answer) {
        return correctAnswer.equals(answer);
    }

    private void shuffleOptions() {
        Collections.shuffle(this.options);
    }
}
