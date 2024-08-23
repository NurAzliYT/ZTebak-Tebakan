package id.nurazlib.ztebakgambar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer backgroundMusic;
    private TextView questionText, levelText;
    private RadioGroup optionsGroup;
    private Button submitButton;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Mobile Ads SDK
        MobileAds.initialize(this, initializationStatus -> {});

        // Referensi UI
        questionText = findViewById(R.id.question_text);
        levelText = findViewById(R.id.level_text);
        optionsGroup = findViewById(R.id.options_group);
        submitButton = findViewById(R.id.submit_button);
        adView = findViewById(R.id.adView);

        // Memuat iklan
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // Inisialisasi dan mulai musik latar
        backgroundMusic = MediaPlayer.create(this, R.raw.background);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();

        // Memuat pertanyaan
        questions = QuestionBank.getQuestions();
        loadNextQuestion();

        // Submit jawaban
        submitButton.setOnClickListener(view -> {
            int selectedId = optionsGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedId);
                String selectedAnswer = selectedRadioButton.getText().toString();
                checkAnswer(selectedAnswer);
            } else {
                Toast.makeText(MainActivity.this, "Pilih jawaban dulu!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (backgroundMusic != null) {
            backgroundMusic.release();
        }
        if (adView != null) {
            adView.destroy();
        }
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionText.setText(currentQuestion.getQuestionText());
            levelText.setText("Level " + (currentQuestionIndex + 1));

            optionsGroup.removeAllViews();
            for (String option : currentQuestion.getOptions()) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(option);
                optionsGroup.addView(radioButton);
            }
        } else {
            // Selesai, tampilkan pesan akhir
            questionText.setText("Tunggu update terbaru!");
            levelText.setText("Level Selesai!");
            optionsGroup.removeAllViews();
            submitButton.setEnabled(false);
            Toast.makeText(this, "Kamu telah menyelesaikan semua level! Skor: " + score, Toast.LENGTH_LONG).show();
        }
    }

    private void checkAnswer(String selectedAnswer) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion.getCorrectAnswer().equals(selectedAnswer)) {
            score++;
            Toast.makeText(this, "Benar!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Salah! Jawaban yang benar: " + currentQuestion.getCorrectAnswer(), Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        loadNextQuestion();
    }
}
