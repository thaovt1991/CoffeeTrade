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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "staffs")
public class Staff {
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

    @NotEmpty(message = "Name not empty")
    @Pattern(regexp = "(^([AÀẢÃÁẠĂẰẮẲẴẶÂẤẦẨẪẬBCDĐEÈÉẺẼẸÊỀẾỂỄỆFGHIÍÌỈĨỊJKLMNOÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢPQRSTUÙÚỦŨỤƯỪỨỬỮỰVWXYÝỲỶỸỴZ]+[aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*?[ ]?)+$)", message = "Name format not true, Ex example : Nguyễn Văn A ")
    @Size(min = 1, max = 45, message = "Full name description within 255 characters ! ")
    private String fullName;


    private String gender ;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z")
    private Date birthday ;

    @Pattern(regexp = "(^[a-z][a-z0-9_\\.]{3,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,7}){1,7}$)", message = "Mail not true, Ex: codegymhue2021@codegym.com ")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "(^$|[0][0-9]{9,10}$)", message = "Formatter not true, phone number is have 10-11 character ! ")
    private String phone;

    @Size(min = 1, max = 255, message = "Address description within 255 characters ! ")
    private String address;

    private String image ;

    private boolean isDeleted = false;

    @OneToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;
}
