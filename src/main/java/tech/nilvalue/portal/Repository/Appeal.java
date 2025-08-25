package tech.nilvalue.portal.Repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;


@Entity
@Data
@Table(name = "APPEALS")
@Validated
public class Appeal {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @NotNull
    private String id;

    @Column(nullable = false)
    private String ipAddress;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;

    @Pattern(regexp = "^[а-яА-Я]{3,16}$")
    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false, length = 64)
    private String email;

    @Pattern(regexp = "^.{10,300}$")
    @Column(length = 300)
    private String message;
}
