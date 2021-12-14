package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date created_at = new Date();

    private Long create_by;


    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date update_at = new Date();

    private Long update_by;

    @Pattern(regexp = "(^[a-z][a-z0-9_]{6,16}$)", message = "Username must have 6 to 16 lowercase and numeric characters, starting with 1 character letter ")
    private String username ;

    @Pattern(regexp = "(^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{6,}$)", message = "Has 6 or more characters, including letters and numbers")
    private String password ;

    private String roles ;

    @OneToOne
    @JoinColumn(name = "staff_id")
    @JsonIgnore
    private Staff staff;

    private boolean isDeleted = false;
}
