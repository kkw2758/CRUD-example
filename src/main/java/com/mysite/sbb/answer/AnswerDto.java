package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionDto;
import com.mysite.sbb.user.SiteUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
public class AnswerDto {
    private Integer id;

    private String content;

    private LocalDateTime createDate;

    private QuestionDto question;

    private SiteUser author;

    private LocalDateTime modifyDate;

    private Set<SiteUser> voter;

    public Answer toEntity() {
        return Answer.builder()
                .id(id)
                .content(content)
                .createDate(createDate)
                .question(question.toEntity())
                .author(author)
                .modifyDate(modifyDate)
                .voter(voter)
                .build();
    }
}
