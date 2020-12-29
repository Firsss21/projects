package app.dao;

import app.dao.JPA.QuestionsEntity;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao extends AbstractHibernateDao<QuestionsEntity> implements DAO<QuestionsEntity>
{
    public QuestionDao() {
        setClazz(QuestionsEntity.class);
    }

    public List<QuestionsEntity> getResultListByCategory(String category) {
        Session session = Hibernate.getSession();

        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionsEntity> query = builder.createQuery(QuestionsEntity.class);
        Root<QuestionsEntity> root = query.from(QuestionsEntity.class);

        query.select(root).where(builder.equal(root.get("category"), category));
        List<QuestionsEntity> resultList = session.createQuery(query).getResultList();

        session.getTransaction().commit();
        return resultList;
    }

    public void deleteAll() {
        List<QuestionsEntity> all = getAll();

        for(int i = 0; i < all.size(); i++) {
           delete(all.get(i));
        }
    }

    public void resetCategory(String category) {
        QuestionDao dao = new QuestionDao();
        dao.getResultListByCategory(category).forEach(el -> {
            el.setIknowit(false);
            dao.update(el);
        });
    }

    public void resetAllCategory() {
        QuestionDao dao = new QuestionDao();
        List<QuestionsEntity> list = dao.getAll();
        list.forEach(el -> {
            el.setIknowit(false);
            dao.update(el);
        });
    }

    public void setIknowIt(QuestionsEntity entity) {
        QuestionDao dao = new QuestionDao();
        entity.setIknowit(true);
        dao.update(entity);
    }

    public QuestionsEntity getRandomQuestionWithCategory(String category) {

       try (Session session = Hibernate.getSession()) {
           session.beginTransaction();

           CriteriaBuilder builder = session.getCriteriaBuilder();
           CriteriaQuery<QuestionsEntity> query = builder.createQuery(QuestionsEntity.class);
           Root<QuestionsEntity> root = query.from(QuestionsEntity.class);

           List<Predicate> predicates = new ArrayList<>();
           predicates.add(builder.equal(root.get("category"), category));
           predicates.add(builder.equal(root.get("iknowit"), false));

           query.where(predicates.toArray(new Predicate[0]));
           List<QuestionsEntity> resultList = session.createQuery(query).getResultList();
           if (resultList.size() < 1) {
//            QuestionsEntity empty = new QuestionsEntity();
//            empty.setQuestion("Вопросы кончились - на выход");
//            return empty;
               return null;
           }
           int randomNumber = (int) (Math.random() * resultList.size());
           return resultList.get(randomNumber);
       }
       catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }

    public int getRemainingQuestionsSizeInCategory(String category) {

        try (Session session = Hibernate.getSession()) {
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<QuestionsEntity> query = builder.createQuery(QuestionsEntity.class);
            Root<QuestionsEntity> root = query.from(QuestionsEntity.class);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("category"), category));
            predicates.add(builder.equal(root.get("iknowit"), false));

            query.where(predicates.toArray(new Predicate[0]));
            List<QuestionsEntity> resultList = session.createQuery(query).getResultList();


            return resultList.size();
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
