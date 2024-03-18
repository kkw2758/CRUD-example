package com.mysite.sbb;

import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // ModelMapper 객체 생성
        ModelMapper modelMapper = new ModelMapper();

        // 날짜 정보 매핑
//        LocalDateTime sourceDateTime = LocalDateTime.of(2023, 12, 4, 10, 30, 0);
        LocalDateTime sourceDateTime = LocalDateTime.now();

        DestinationDto destinationDto = modelMapper.map(sourceDateTime, DestinationDto.class);

        // 매핑된 결과 출력
        System.out.println(destinationDto);
    }

    // 대상 DTO 클래스
    public static class DestinationDto {
        private LocalDateTime dateTime;

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        @Override
        public String toString() {
            return "DestinationDto{" +
                    "dateTime=" + dateTime +
                    '}';
        }
    }
}
