package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // SourceObject 생성
        QuestionDto questionDto = new QuestionDto();
        questionDto.setSubject("subject");
        questionDto.setContent("content");
        questionDto.setModifyDate(LocalDateTime.now());
        questionDto.setCreateDate(LocalDateTime.now());
        System.out.println(questionDto.getModifyDate());
        // ModelMapper 생성
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        // SourceObject를 DestinationObject로 매핑
        Question question = modelMapper.map(questionDto, Question.class);

        // 매핑된 DestinationObject 출력
        System.out.println("createDate : " + question.getCreateDate());

        QuestionDto resultDto = modelMapper.map(question, QuestionDto.class);
        System.out.println("resultDto = " + resultDto);
        System.out.println(resultDto.getCreateDate());
    }
}
