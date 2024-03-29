package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;

    @ManyToMany
    private Set<SiteUser> voter;

    public void updateAnswer(String content) {
        this.modifyDate = LocalDateTime.now();
        this.content = content;
    }

    public AnswerDto toDto() {
        return AnswerDto.builder()
                .id(id)
                .content(content)
                .createDate(createDate)
                .question(question.toDto())
                .author(author)
                .modifyDate(modifyDate)
                .voter(voter)
                .build();
    }
}
