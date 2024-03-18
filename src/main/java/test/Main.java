package test;

import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // SourceObject 생성
        SourceObject sourceObject = new SourceObject();
        sourceObject.setName("Example");
        sourceObject.setDateTime(LocalDateTime.now());

        // ModelMapper 생성
        ModelMapper modelMapper = new ModelMapper();

        // SourceObject를 DestinationObject로 매핑
        DestinationObject destinationObject = modelMapper.map(sourceObject, DestinationObject.class);

        // 매핑된 DestinationObject 출력
        System.out.println("DestinationObject - Name: " + destinationObject.getName());
        System.out.println("DestinationObject - DateTime: " + destinationObject.getDateTime());
    }
}