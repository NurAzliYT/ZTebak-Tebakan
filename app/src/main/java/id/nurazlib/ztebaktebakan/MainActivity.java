package id.nurazlib.ztebaktebakan;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer backgroundMusic;
    private TextView questionText, levelText, hintCounterText;
    private RadioGroup optionsGroup;
    private Button submitButton, hintButton, watchAdButton;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int maxLevel = 40;
    private AdView adView;
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd; // Diganti menjadi mRewardedAd
    private int hintCounter = 3; 
    private AudioManager audioManager;
    private int currentMusicIndex = 1; // Indeks musik saat ini

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> { });

        questionText = findViewById(R.id.question_text);
        levelText = findViewById(R.id.level_text);
        hintCounterText = findViewById(R.id.hint_counter);
        optionsGroup = findViewById(R.id.options_group);
        submitButton = findViewById(R.id.submit_button);
        hintButton = findViewById(R.id.hint_button);
        watchAdButton = findViewById(R.id.watch_ad_button);
        adView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Inisialisasi musik pertama
        backgroundMusic = MediaPlayer.create(this, R.raw.background_music1);
        backgroundMusic.setOnCompletionListener(mp -> playNextMusic()); // Memutar musik selanjutnya saat musik selesai
        backgroundMusic.start();

        questions = QuestionBank.getQuestions();

        currentQuestionIndex = loadLevel() - 1; 

        loadNextQuestion();
        loadInterstitialAd();
        loadRewardedAd();
        updateHintCounter();

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

        hintButton.setOnClickListener(view -> {
            if (hintCounter > 0) {
                showHintPopup(questions.get(currentQuestionIndex).getHint());
                hintCounter--;
                updateHintCounter();
            } else {
                Toast.makeText(MainActivity.this, "Tonton iklan untuk mendapatkan hint!", Toast.LENGTH_SHORT).show();
            }
        });

        watchAdButton.setOnClickListener(view -> {
            if (mRewardedAd != null) { // Diganti menjadi mRewardedAd
                mRewardedAd.show(MainActivity.this, rewardItem -> { // Diganti menjadi mRewardedAd
                    hintCounter++;
                    updateHintCounter();
                    loadRewardedAd(); 
                });
            } else {
                Toast.makeText(MainActivity.this, "Iklan belum siap. Coba lagi nanti.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseBackgroundMusic();
        saveLevel(currentQuestionIndex + 1); 
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeBackgroundMusic();
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

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, "ca-app-pub-4186599691041011/7680150324", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdShowedFullScreenContent() {
                        pauseBackgroundMusic();
                        mInterstitialAd = null; 
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        resumeBackgroundMusic();
                        loadInterstitialAd(); 
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        resumeBackgroundMusic();
                        loadInterstitialAd(); 
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                mInterstitialAd = null;
            }
        });
    }

    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-4186599691041011/8469643922", adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedAd ad) {
                mRewardedAd = ad; // Diganti menjadi mRewardedAd
                mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() { // Diganti menjadi mRewardedAd
                    @Override
                    public void onAdShowedFullScreenContent() {
                        mRewardedAd = null; // Diganti menjadi mRewardedAd
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        loadRewardedAd(); 
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        mRewardedAd = null; // Diganti menjadi mRewardedAd
                        loadRewardedAd(); 
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                mRewardedAd = null; // Diganti menjadi mRewardedAd
            }
        });
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < maxLevel && currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionText.setText(currentQuestion.getQuestionText());
            levelText.setText(String.format("Level %d", (currentQuestionIndex + 1)));

            optionsGroup.removeAllViews();
            for (String option : currentQuestion.getOptions()) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(option);
                optionsGroup.addView(radioButton);
            }

            if (mInterstitialAd != null && currentQuestionIndex % 5 == 0 && currentQuestionIndex != 0) {
                mInterstitialAd.show(MainActivity.this);
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
        hintButton.setEnabled(false);
    }

    private void showHintPopup(String hint) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.custom_popup_hint, null);

        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        TextView hintTextView = popupView.findViewById(R.id.hint_text);
        hintTextView.setText("Hint: " + hint); 

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        Button closePopupButton = popupView.findViewById(R.id.close_popup_button);
        closePopupButton.setOnClickListener(view -> popupWindow.dismiss());
    }

    private void updateHintCounter() {
        hintCounter = Math.max(0, hintCounter); 
        hintCounterText.setText(String.format("Hint: %d", hintCounter));
    }

    private void saveLevel(int level) {
        SharedPreferences sharedPreferences = getSharedPreferences("GameData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("last_level", level);
        editor.apply();
    }

    private int loadLevel() {
        SharedPreferences sharedPreferences = getSharedPreferences("GameData", MODE_PRIVATE);
        return sharedPreferences.getInt("last_level", 1); 
    }

    private void pauseBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isPlaying()) {
            backgroundMusic.pause();
        }
    }

    private void resumeBackgroundMusic() {
        if (backgroundMusic != null && !backgroundMusic.isPlaying() && audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) > 0) {
            backgroundMusic.start();
        }
    }

    // Method untuk memutar musik selanjutnya
    private void playNextMusic() {
        currentMusicIndex = (currentMusicIndex == 1) ? 2 : 1; // Ganti indeks musik

        int nextMusicResId = (currentMusicIndex == 1) ? R.raw.background_music1 : R.raw.background_music2; // Tentukan resource musik

        backgroundMusic.reset(); // Reset MediaPlayer
        backgroundMusic = MediaPlayer.create(this, nextMusicResId); // Buat MediaPlayer baru dengan musik selanjutnya
        backgroundMusic.setOnCompletionListener(mp -> playNextMusic()); // Set listener untuk memutar musik selanjutnya
        backgroundMusic.start(); // Mulai musik
    }
}
