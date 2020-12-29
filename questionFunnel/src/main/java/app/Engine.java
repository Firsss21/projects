package app;

import app.dao.JPA.QuestionsEntity;
import app.dao.QuestionDao;

import java.util.Arrays;
import java.util.Scanner;

public class Engine {
    FillDB fillDB = new FillDB();

    private QuestionDao questionDao = new QuestionDao();
    private Scanner keyboardScanner = new Scanner(System.in);

    public Engine() {
        menu();
    }

    public void nextQuestionRecursion(String category) {
        QuestionsEntity currentQuestion = questionDao.getRandomQuestionWithCategory(category);
        if (currentQuestion == null) {System.out.println("Список вопросов пуст"); return;}
        System.out.println(currentQuestion.getQuestion());
        System.out.println("Know? 1 - yes, any other  - no, back - 0");
        Scanner stringScanner = new Scanner(System.in);
        String result = stringScanner.nextLine();
        switch (result) {
            case "0":
                return;
            case "1":
                currentQuestion.setIknowit(true);
                questionDao.update(currentQuestion);
            default:
                break;
        }
        nextQuestionRecursion(category);
    }

    public void menu() {
        while(true) {
            System.out.println("Select one of menu items");
            System.out.println("1. Select category and get questions");
            System.out.println("2. Reset category");
            System.out.println("3. Reset all");
            System.out.println("4. Exit");

            Scanner menuItemScanner = new Scanner(System.in);
            String menuItem = menuItemScanner.nextLine();

            switch (menuItem)
            {
                case "1":
                    System.out.println("Select theme by entering number:");
                    Arrays.asList(FillDB.questionsEnum.values())
                            .forEach(string -> {
                                System.out.println(string.getId() + ". " + string.getString() + "  || Remaining questions: " + questionDao.getRemainingQuestionsSizeInCategory(string.getString()) + "/" + questionDao.getResultListByCategory(string.getString()).size());
                            });
                    String category = FillDB.questionsEnum.getEnumById(keyboardScanner.nextInt()).getString();
                    System.out.println("Тема: " + category);
                    nextQuestionRecursion(category);
                    break;
                case "2":
                    System.out.println("Select category to reset:");
                    Arrays.asList(FillDB.questionsEnum.values())
                            .forEach(string -> {
                                System.out.println(string.getId() + ". " + string.getString());
                            });
                    String resetCategory = FillDB.questionsEnum.getEnumById(keyboardScanner.nextInt()).getString();
                    questionDao.resetCategory(resetCategory);
                    break;
                case "3":
                    questionDao.resetAllCategory();
                    break;
                case "4":
                    System.out.println("Exiting..");
                    return;

                default:
                    break;
            }
        }
    }
}
