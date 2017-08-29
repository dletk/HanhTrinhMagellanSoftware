import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionBank {

    File file10, file20, file30;
    HashMap<String, String> question10, question20, question30;

    public QuestionBank(File file10, File file20, File file30) {
        this.file10 = file10;
        this.file20 = file20;
        this.file30 = file30;

        question10 = extractData(file10);
        question20 = extractData(file20);
        question30 = extractData(file30);

        // TODO: Work on question bank to make it randomly give out a question and erase the seen questions
        System.out.println(question10);
    }

    private HashMap<String, String> extractData(File fileQuestion) {
        HashMap<String, String> data = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileQuestion));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] questionAndAns = line.split("\\|");
                data.put(questionAndAns[0], questionAndAns[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
