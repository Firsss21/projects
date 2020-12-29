package app;
import app.dao.Hibernate;
import app.dao.JPA.QuestionsEntity;
import app.dao.QuestionDao;
import app.dao.UniqueHibernateDao;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static app.dao.Hibernate.getSession;

public class Main {

    public static void main(String[] args) {

        Engine engine = new Engine();

    }

}
