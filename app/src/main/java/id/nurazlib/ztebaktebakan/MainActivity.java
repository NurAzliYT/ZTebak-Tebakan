package id.nurazlib.ztebaktebakan;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer backgroundMusic;
    private TextView questionText, levelText;
    private RadioGroup optionsGroup;
    private Button submitButton;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int maxLevel = 40;
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

        // Memuat level terakhir yang tersimpan
        currentQuestionIndex = loadLevel() - 1; // -1 karena level disimpan dimulai dari 1

        // Melanjutkan ke pertanyaan terakhir yang belum dijawab
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

    @Override
    protected void onPause() {
        super.onPause();
        if (currentQuestionIndex < maxLevel) {
            saveLevel(currentQuestionIndex + 1); // +1 karena indeks mulai dari 0
        } else {
            // Jika sudah di level maksimal, simpan data final dan hapus cache
            saveFinalDataAndClearCache();
        }
    }

    private void saveLevel(int level) {
        if (level >= maxLevel) {
            return; // Jangan menyimpan jika sudah mencapai level maksimal
        }

        SharedPreferences sharedPreferences = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("last_level", level);
        editor.apply();

        File directory = new File(getExternalFilesDir(null), "level");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, "last_level.txt");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(Integer.toString(level).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int loadLevel() {
        SharedPreferences sharedPreferences = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        int lastLevel = sharedPreferences.getInt("last_level", 1); // Default ke level 1 jika tidak ada data

        File directory = new File(getExternalFilesDir(null), "level");
        File file = new File(directory, "last_level.txt");

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                lastLevel = Integer.parseInt(new String(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lastLevel;
    }

    private void saveFinalDataAndClearCache() {
        // Menyimpan data terakhir
        SharedPreferences sharedPreferences = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("last_level", maxLevel);
        editor.apply();

        // Simpan file ke Android/data/id.nurazlib.ztebaktebakan
        File dataDir = new File(getExternalFilesDir(null), "../data/id.nurazlib.ztebaktebakan");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        File finalDataFile = new File(dataDir, "final_data.txt");
        try (FileOutputStream fos = new FileOutputStream(finalDataFile)) {
            String finalData = "Level terakhir: " + maxLevel + "\nSkor: " + score;
            fos.write(finalData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Hapus cache
        File cacheDir = getCacheDir();
        if (cacheDir.isDirectory()) {
            for (File file : cacheDir.listFiles()) {
                file.delete();
            }
        }
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < maxLevel && currentQuestionIndex < questions.size()) {
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
            endGame();
        }
    }

    private void checkAnswer(String selectedAnswer) {
        if (currentQuestionIndex < maxLevel) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            if (currentQuestion.getCorrectAnswer().equals(selectedAnswer)) {
                score++;
                Toast.makeText(this, "Benar!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Salah! Jawaban yang benar: " + currentQuestion.getCorrectAnswer(), Toast.LENGTH_SHORT).show();
            }

            currentQuestionIndex++;
            loadNextQuestion();
        } else {
            endGame();
        }
    }

    private void endGame() {
        questionText.setText("Tunggu update terbaru!");
        levelText.setText("Level Selesai!");
        optionsGroup.removeAllViews();
        submitButton.setEnabled(false);
        Toast.makeText(this, "Kamu telah menyelesaikan semua level! Skor: " + score, Toast.LENGTH_LONG).show();

        // Simpan data akhir dan bersihkan cache
        saveFinalDataAndClearCache();
    }
}
