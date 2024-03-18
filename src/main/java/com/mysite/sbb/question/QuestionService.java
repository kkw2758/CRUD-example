package com.mysite.sbb.question;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public Page<QuestionDto> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Page<Question> resultPage = this.questionRepository.findAll(search(kw), pageable);
        Question question = resultPage.getContent().get(0);
        QuestionDto result = modelMapper.map(question, QuestionDto.class);// Error
        System.out.println(result);
        QuestionDto questionDto = convertToDto(question); // 에러 발생
        Page<QuestionDto> questionDtoPage = resultPage.map(this::convertToDto);
        return questionDtoPage;
    }

    private QuestionDto convertToDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    public QuestionDto getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return convertToDto(question.get());
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content, SiteUser author) {
        Question q = Question.of(subject, content, author);
        this.questionRepository.save(q);
    }

    public void modify(Integer id, String subject, String content) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            question.get().updateQuestion(subject, content);
            this.questionRepository.save(question.get());
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void delete(Integer id) {
        this.questionRepository.deleteById(id);
    }

    public void vote(QuestionDto questionDto, SiteUser siteUser) {
        Question question = modelMapper.map(questionDto, Question.class);
        System.out.println("question = " + question);
        question.getVoter().add(siteUser);
        this.questionRepository.save(question);
    }

    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제 목
                        cb.like(q.get("content"), "%" + kw + "%"), // 내 용
                        cb.like(u1.get("userName"), "%" + kw + "%"), // 질 문 작 성 자
                        cb.like(a.get("content"), "%" + kw + "%"), // 답 변 내 용
                        cb.like(u2.get("userName"), "%" + kw + "%")); // 답 변 작 성 자
            }
        };
    }
}
