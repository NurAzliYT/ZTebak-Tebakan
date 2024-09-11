package id.nurazlib.ztebaktebakan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {

    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("Apa ibu kota Indonesia?", shuffleOptions(new String[]{"Jakarta", "Surabaya", "Bandung", "Medan"}), "Jakarta"));
        questions.add(new Question("Gunung tertinggi di dunia?", shuffleOptions(new String[]{"Kilimanjaro", "Everest", "Elbrus", "Denali"}), "Everest"));
        questions.add(new Question("Hewan tercepat di dunia?", shuffleOptions(new String[]{"Cheetah", "Singa", "Elang", "Harimau"}), "Cheetah"));
        questions.add(new Question("Planet terdekat dengan Matahari?", shuffleOptions(new String[]{"Venus", "Mars", "Merkurius", "Bumi"}), "Merkurius"));
        questions.add(new Question("Benua terbesar di dunia?", shuffleOptions(new String[]{"Afrika", "Asia", "Eropa", "Amerika Utara"}), "Asia"));
        questions.add(new Question("Apa ibu kota Jepang?", shuffleOptions(new String[]{"Tokyo", "Kyoto", "Osaka", "Hokkaido"}), "Tokyo"));
        questions.add(new Question("Bahasa resmi Brasil?", shuffleOptions(new String[]{"Spanyol", "Portugis", "Inggris", "Prancis"}), "Portugis"));
        questions.add(new Question("Hewan nasional Australia?", shuffleOptions(new String[]{"Kanguru", "Koala", "Platipus", "Emu"}), "Kanguru"));
        questions.add(new Question("Sungai terpanjang di dunia?", shuffleOptions(new String[]{"Nil", "Amazon", "Yangtze", "Mississippi"}), "Nil"));
        questions.add(new Question("Siapa penemu telepon?", shuffleOptions(new String[]{"Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "James Watt"}), "Alexander Graham Bell"));

        questions.add(new Question("Planet terbesar di tata surya?", shuffleOptions(new String[]{"Jupiter", "Saturnus", "Neptunus", "Mars"}), "Jupiter"));
        questions.add(new Question("Siapa penulis 'Harry Potter'?", shuffleOptions(new String[]{"J.K. Rowling", "J.R.R. Tolkien", "George R.R. Martin", "Stephen King"}), "J.K. Rowling"));
        questions.add(new Question("Apa ibu kota Mesir?", shuffleOptions(new String[]{"Kairo", "Alexandria", "Giza", "Luxor"}), "Kairo"));
        questions.add(new Question("Siapa presiden pertama Indonesia?", shuffleOptions(new String[]{"Soekarno", "Soeharto", "Habibie", "Gus Dur"}), "Soekarno"));
        questions.add(new Question("Hewan terbesar di dunia?", shuffleOptions(new String[]{"Gajah", "Paus Biru", "Beruang", "Jerapah"}), "Paus Biru"));
        questions.add(new Question("Apa ibu kota Prancis?", shuffleOptions(new String[]{"Paris", "Marseille", "Lyon", "Toulouse"}), "Paris"));
        questions.add(new Question("Benua terkecil di dunia?", shuffleOptions(new String[]{"Australia", "Antartika", "Eropa", "Amerika Selatan"}), "Australia"));
        questions.add(new Question("Hewan yang bisa terbang?", shuffleOptions(new String[]{"Kelelawar", "Ayam", "Burung Unta", "Penguin"}), "Kelelawar"));
        questions.add(new Question("Logam mulia yang berwarna kuning?", shuffleOptions(new String[]{"Emas", "Perak", "Tembaga", "Besi"}), "Emas"));
        questions.add(new Question("Planet merah?", shuffleOptions(new String[]{"Mars", "Venus", "Jupiter", "Saturnus"}), "Mars"));

        questions.add(new Question("Lambang negara Indonesia?", shuffleOptions(new String[]{"Garuda Pancasila", "Burung Elang", "Macan Asia", "Gajah Putih"}), "Garuda Pancasila"));
        questions.add(new Question("Apa nama sungai terpanjang di Asia?", shuffleOptions(new String[]{"Yangtze", "Huang He", "Ganges", "Indus"}), "Yangtze"));
        questions.add(new Question("Benda yang digunakan untuk mengukur suhu?", shuffleOptions(new String[]{"Termometer", "Barometer", "Higrometer", "Anemometer"}), "Termometer"));
        questions.add(new Question("Di mana Menara Eiffel berada?", shuffleOptions(new String[]{"Paris", "Berlin", "Roma", "London"}), "Paris"));
        questions.add(new Question("Siapa yang menemukan bola lampu?", shuffleOptions(new String[]{"Thomas Edison", "Nikola Tesla", "Alexander Graham Bell", "James Watt"}), "Thomas Edison"));
        questions.add(new Question("Hewan yang berhibernasi selama musim dingin?", shuffleOptions(new String[]{"Beruang", "Singa", "Kanguru", "Harimau"}), "Beruang"));
        questions.add(new Question("Siapa penulis 'The Hobbit'?", shuffleOptions(new String[]{"J.R.R. Tolkien", "J.K. Rowling", "George Orwell", "Mark Twain"}), "J.R.R. Tolkien"));
        questions.add(new Question("Pulau terbesar di dunia?", shuffleOptions(new String[]{"Greenland", "Madagaskar", "Borneo", "Sumatra"}), "Greenland"));
        questions.add(new Question("Apa ibu kota Italia?", shuffleOptions(new String[]{"Roma", "Milan", "Venice", "Florence"}), "Roma"));
        questions.add(new Question("Bintang yang paling dekat dengan Bumi?", shuffleOptions(new String[]{"Matahari", "Proxima Centauri", "Sirius", "Alpha Centauri"}), "Matahari"));
        questions.add(new Question("Siapa penemu teori relativitas?", shuffleOptions(new String[]{"Albert Einstein", "Isaac Newton", "Galileo Galilei", "Stephen Hawking"}), "Albert Einstein"));
        questions.add(new Question("Hewan tercepat di air?", shuffleOptions(new String[]{"Ikan Layar", "Lumba-lumba", "Hiu", "Paus Orca"}), "Ikan Layar"));
        questions.add(new Question("Planet yang memiliki cincin?", shuffleOptions(new String[]{"Saturnus", "Jupiter", "Uranus", "Neptunus"}), "Saturnus"));
        questions.add(new Question("Apa nama candi terbesar di Indonesia?", shuffleOptions(new String[]{"Borobudur", "Prambanan", "Sewu", "Penataran"}), "Borobudur"));
        questions.add(new Question("Siapa yang menemukan gravitasi?", shuffleOptions(new String[]{"Isaac Newton", "Albert Einstein", "Galileo Galilei", "Aristoteles"}), "Isaac Newton"));
        questions.add(new Question("Hewan yang bisa berubah warna?", shuffleOptions(new String[]{"Bunglon", "Kadal", "Iguana", "Tokek"}), "Bunglon"));
        questions.add(new Question("Apa bahasa resmi di Tiongkok?", shuffleOptions(new String[]{"Mandarin", "Kanton", "Shanghainese", "Hokkien"}), "Mandarin"));
        questions.add(new Question("Apa nama lautan terbesar di dunia?", shuffleOptions(new String[]{"Samudra Pasifik", "Samudra Atlantik", "Samudra Hindia", "Samudra Arktik"}), "Samudra Pasifik"));
        questions.add(new Question("Siapa penemu bola lampu?", shuffleOptions(new String[]{"Thomas Edison", "Nikola Tesla", "Alexander Graham Bell", "Benjamin Franklin"}), "Thomas Edison"));
        questions.add(new Question("Hewan apa yang dikenal sebagai 'raja hutan'?", shuffleOptions(new String[]{"Singa", "Harimau", "Gajah", "Serigala"}), "Singa"));
        questions.add(new Question("Apa ibu kota Australia", shuffleOptions(new String[]{"Berlin", "Canberra", "Wina", "Ottawa"}), "Canberra"));

        return questions;
    }

    private static String[] shuffleOptions(String[] options) {
        List<String> optionList = new ArrayList<>();
        Collections.addAll(optionList, options);
        Collections.shuffle(optionList);
        return optionList.toArray(new String[0]);
    }
}
