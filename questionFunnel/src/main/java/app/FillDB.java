package app;

import app.dao.JPA.QuestionsEntity;
import app.dao.QuestionDao;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class FillDB {

    public enum questionsEnum {
        OOP("OOP.txt", 1), JAVACORE("JavaCore.txt", 2), COLLECTIONS("Collections", 3), CSS ("css", 4), DATABASE ("Database", 5), DESIGNPATTERNS("DesignPatterns", 6), HTML("HTML", 7),
        INPUTOUTPUT("InputOutput", 8), JAVA8("Java8", 9), JBDC("JBDC", 10), JURNALIROVANIE("Jurnalirovanie", 11), JVM("JVM", 12),
        MULTITHREADING("Multithreading", 13), SERIALIZABLE("Serializable", 14), SERVLETS("Servlets", 15), SQL("SQL", 16),
        TEST("Test", 17), UML("UML", 18), WEB("WEB", 19), XML("XML", 20);
        private final String string;
        private final int id;
        private questionsEnum(String s, int i) {
            this.string = s;
            this.id = i;
        }
        public String getString() {
            return this.string;
        }

        public int getId() {
            return this.id;
        }

        public static questionsEnum getEnumById(int id) {
            for(questionsEnum el : values()) {
                if(el.id == id) return el;
            }
            return OOP;
        }
    }

    public FillDB() {

        QuestionDao dao = new QuestionDao();
        FileReader fileReader = new FileReader();

        Arrays.asList(questionsEnum.values())
                .forEach(string -> {
                    if (dao.getResultListByCategory(string.getString()).isEmpty())
                    {
                        System.out.println(string.getString() + " questions added to DataBase ");

                        List<String> list = fileReader.readFileWithBufferedReader(string.getString());
                        fillDBWithCategory(list, string.getString());
                    }
                });
    }


    public void fillDBWithCategory(List<String> questionsList, String category) {
        String question;
        ListIterator iterator = questionsList.listIterator();
        while (iterator.hasNext()) {
            question = (String) iterator.next();

            QuestionsEntity newQuestion = new QuestionsEntity();
            newQuestion.setQuestion(question);
            newQuestion.setCategory(category);

            QuestionDao dao = new QuestionDao();
            dao.save(newQuestion);
        }
    }
}
