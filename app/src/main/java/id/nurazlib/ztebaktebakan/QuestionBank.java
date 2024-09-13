package id.nurazlib.ztebaktebakan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class QuestionBank {

    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("Apa ibu kota Indonesia?", 
            shuffleOptions(new String[]{"Jakarta", "Surabaya", "Bandung", "Medan"}), 
            "Jakarta", "Ibu kota negara Indonesia."));
        
        questions.add(new Question("Gunung tertinggi di dunia?", 
            shuffleOptions(new String[]{"Kilimanjaro", "Everest", "Elbrus", "Denali"}), 
            "Everest", "Terletak di pegunungan Himalaya."));
        
        questions.add(new Question("Hewan tercepat di dunia?", 
            shuffleOptions(new String[]{"Cheetah", "Singa", "Elang", "Harimau"}), 
            "Cheetah", "Hewan ini terkenal karena kecepatan larinya."));
        
        questions.add(new Question("Planet terdekat dengan Matahari?", 
            shuffleOptions(new String[]{"Venus", "Mars", "Merkurius", "Bumi"}), 
            "Merkurius", "Planet dengan jarak terdekat ke Matahari."));
        
        questions.add(new Question("Benua terbesar di dunia?", 
            shuffleOptions(new String[]{"Afrika", "Asia", "Eropa", "Amerika Utara"}), 
            "Asia", "Benua yang memiliki populasi terbesar."));
        
        questions.add(new Question("Apa ibu kota Jepang?", 
            shuffleOptions(new String[]{"Tokyo", "Kyoto", "Osaka", "Hokkaido"}), 
            "Tokyo", "Salah satu kota paling modern di dunia."));
        
        questions.add(new Question("Bahasa resmi Brasil?", 
            shuffleOptions(new String[]{"Spanyol", "Portugis", "Inggris", "Prancis"}), 
            "Portugis", "Bahasa yang digunakan di Portugal."));
        
        questions.add(new Question("Hewan nasional Australia?", 
            shuffleOptions(new String[]{"Kanguru", "Koala", "Platipus", "Emu"}), 
            "Kanguru", "Hewan marsupial yang melompat."));
        
        questions.add(new Question("Sungai terpanjang di dunia?", 
            shuffleOptions(new String[]{"Nil", "Amazon", "Yangtze", "Mississippi"}), 
            "Nil", "Sungai ini melintasi Mesir."));
        
        questions.add(new Question("Siapa penemu telepon?", 
            shuffleOptions(new String[]{"Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "James Watt"}), 
            "Alexander Graham Bell", "Penemuan yang merevolusi komunikasi."));

        questions.add(new Question("Planet terbesar di tata surya?", 
            shuffleOptions(new String[]{"Jupiter", "Saturnus", "Neptunus", "Mars"}), 
            "Jupiter", "Planet gas terbesar."));
        
        questions.add(new Question("Siapa penulis 'Harry Potter'?", 
            shuffleOptions(new String[]{"J.K. Rowling", "J.R.R. Tolkien", "George R.R. Martin", "Stephen King"}), 
            "J.K. Rowling", "Seri buku fantasi yang sangat populer."));
        
        questions.add(new Question("Apa ibu kota Mesir?", 
            shuffleOptions(new String[]{"Kairo", "Alexandria", "Giza", "Luxor"}), 
            "Kairo", "Ibu kota yang terletak di Afrika."));
        
        questions.add(new Question("Siapa presiden pertama Indonesia?", 
            shuffleOptions(new String[]{"Soekarno", "Soeharto", "Habibie", "Gus Dur"}), 
            "Soekarno", "Pemimpin kemerdekaan Indonesia."));
        
        questions.add(new Question("Hewan terbesar di dunia?", 
            shuffleOptions(new String[]{"Gajah", "Paus Biru", "Beruang", "Jerapah"}), 
            "Paus Biru", "Hidup di lautan, ukurannya sangat besar."));
        
        questions.add(new Question("Apa ibu kota Prancis?", 
            shuffleOptions(new String[]{"Paris", "Marseille", "Lyon", "Toulouse"}), 
            "Paris", "Dikenal sebagai 'Kota Cinta'."));
        
        questions.add(new Question("Benua terkecil di dunia?", 
            shuffleOptions(new String[]{"Australia", "Antartika", "Eropa", "Amerika Selatan"}), 
            "Australia", "Satu-satunya benua yang juga negara."));
        
        questions.add(new Question("Hewan yang bisa terbang?", 
            shuffleOptions(new String[]{"Kelelawar", "Ayam", "Burung Unta", "Penguin"}), 
            "Kelelawar", "Satu-satunya mamalia yang bisa terbang."));
        
        questions.add(new Question("Logam mulia yang berwarna kuning?", 
            shuffleOptions(new String[]{"Emas", "Perak", "Tembaga", "Besi"}), 
            "Emas", "Logam ini sering digunakan dalam perhiasan."));
        
        questions.add(new Question("Planet merah?", 
            shuffleOptions(new String[]{"Mars", "Venus", "Jupiter", "Saturnus"}), 
            "Mars", "Dikenal karena warna tanahnya yang merah."));

        return questions;
    }

    private static String[] shuffleOptions(String[] options) {
        List<String> optionList = Arrays.asList(options);
        Collections.shuffle(optionList);
        return optionList.toArray(new String[0]);
    }
}
