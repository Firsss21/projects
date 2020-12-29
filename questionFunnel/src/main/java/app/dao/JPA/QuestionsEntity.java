package app.dao.JPA;

import javax.persistence.*;

@Entity
@Table(name = "questions", schema = "public", catalog = "postgres")
public class QuestionsEntity {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Basic
        @Column(name = "question")
        private String question;

        @Basic
        @Column(name = "iknowit")
        private boolean iknowit;

        @Basic
        @Column(name = "category")
        private String category;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isIknowit() {
        return iknowit;
    }

    public void setIknowit(boolean iknowit) {
        this.iknowit = iknowit;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }


}




