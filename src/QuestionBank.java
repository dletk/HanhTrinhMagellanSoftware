import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionBank {

    private HashMap<String, String> ques_ans_10, ques_ans_20, ques_ans_30;
    private ArrayList<String> question10, question20, question30;

    public QuestionBank(File file10, File file20, File file30) {

        ques_ans_10 = extractData(file10);
        ques_ans_20 = extractData(file20);
        ques_ans_30 = extractData(file30);

        question10 = new ArrayList<>(ques_ans_10.keySet());
        question20 = new ArrayList<>(ques_ans_20.keySet());
        question30 = new ArrayList<>(ques_ans_30.keySet());
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

    public String[] getQuestion(int questionValue) {
        String question = "", answer = "";
        if ((questionValue == 10 && question10.size() == 0) || (questionValue == 20 && question20.size() == 0) || (questionValue == 30 && question30.size() == 0)) {
            return new String[]{question, answer};
        }
        switch (questionValue) {
            case 10:
                // The question list already random because it is generated from a KeySet. No need to shuffle
                // So, taking the question at 0 index is already random
                question = question10.get(0);
                // Remove the question from the available list
                question10.remove(0);

                answer = ques_ans_10.get(question);
                // Remove both question and answer from the available list
                ques_ans_10.remove(question);
                break;
            case 20:
                question = question20.get(0);
                question20.remove(0);

                answer = ques_ans_20.get(question);
                ques_ans_20.remove(question);
                break;
            case 30:
                question = question30.get(0);
                question30.remove(0);

                answer = ques_ans_30.get(question);
                ques_ans_30.remove(question);
        }

        return new String[]{question, answer};
    }
}
