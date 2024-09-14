//package com.microservices.quiz_service.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.List;
//
//@Data
//@Entity
//public class Quiz {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    private String title;
//
//    @ElementCollection
//    private List<Long> questionIds;
//}
package com.microservices.quiz_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "quiz")  // Map the entity to the `Quiz` table in the database
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ElementCollection
    @CollectionTable(
            name = "quiz_questionIds",  // Map to the manually created `quiz_questionIds` table
            joinColumns = @JoinColumn(name = "quiz_id")  // Map `quiz_id` column from `quiz_questionIds`
    )
    @Column(name = "question_id")  // Map the `question_id` column
    private List<Long> questionIds;
}
