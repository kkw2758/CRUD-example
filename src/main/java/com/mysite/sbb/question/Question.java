package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteUser> voter;

    private Question(QuestionForm questionForm, SiteUser author) {
        this.subject = questionForm.getSubject();
        this.content = questionForm.getContent();
        this.author = author;
        this.createDate = LocalDateTime.now();
    }

    public static Question of(QuestionForm questionForm, SiteUser author) {
        return new Question(questionForm, author);
    }

    public void updateQuestion(QuestionForm questionForm){
        this.subject = questionForm.getSubject();
        this.content = questionForm.getContent();
        this.modifyDate = LocalDateTime.now();
    }

    public QuestionDto toDto() {
        return QuestionDto.builder()
                .id(id)
                .subject(subject)
                .content(content)
                .createDate(createDate)
                .answerList(answerList)
                .author(author)
                .modifyDate(modifyDate)
                .voter(voter)
                .build();
    }
}

