package com.mysite.sbb.question;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class QuestionListDto {
    private Integer id;
    private String subject;
    private LocalDateTime createDate;
    private int answerCount;
    private String author;
}
