package com.mysite.sbb.answer;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.QuestionDto;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer create(QuestionDto questionDto, String content, SiteUser author) {
        Answer answer = Answer.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .question(questionDto.toEntity())
                .author(author)
                .build();
        this.answerRepository.save(answer);
        return answer;
    }

    public AnswerDto getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get().toDto();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(AnswerDto answerDto, String content) {
        Answer answer = answerDto.toEntity();
        answer.updateAnswer(content);
        this.answerRepository.save(answer);
    }

    public void delete(AnswerDto answerDto) {
        this.answerRepository.delete(answerDto.toEntity());
    }

    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }
}
