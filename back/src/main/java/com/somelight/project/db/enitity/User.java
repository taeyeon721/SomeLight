package com.somelight.project.db.enitity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.management.relation.Role;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert // JPA insert시 null인 필드 제외
@DynamicUpdate // 변경된 컬럼만 업데이트(Patch)
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String email;
    private String nickname;

    @Builder
    public User(String email, String nickname)
    {
        this.email = email;
        this.nickname = nickname;
    }
}