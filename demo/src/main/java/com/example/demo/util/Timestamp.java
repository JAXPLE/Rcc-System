package com.example.demo.util;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Getter
//나를 상속받는 자식 클래스가 부모 멤버를 컬럼값으로 가져가길 강제하기
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public class Timestamp {



    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


}
