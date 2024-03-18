package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class QuestionDto {
    private Integer id;

    private String subject;

    private String content;

    private LocalDateTime createDate;

    private List<Answer> answerList;

    private SiteUser author;

    private LocalDateTime modifyDate;

    Set<SiteUser> voter;

    public QuestionDto() {
    }
}
