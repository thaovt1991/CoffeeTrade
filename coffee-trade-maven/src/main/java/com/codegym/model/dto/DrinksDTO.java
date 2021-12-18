package com.codegym.model.dto;

import com.codegym.model.Drinks;
import com.codegym.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DrinksDTO {

    private long id ;

    private MultipartFile image ;

    @NotEmpty(message = "Name not empty")
    @Pattern(regexp = "(^([AÀẢÃÁẠĂẰẮẲẴẶÂẤẦẨẪẬBCDĐEÈÉẺẼẸÊỀẾỂỄỆFGHIÍÌỈĨỊJKLMNOÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢPQRSTUÙÚỦŨỤƯỪỨỬỮỰVWXYÝỲỶỸỴZ]+[aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*?[ ]?)+$)", message = "Name format not true, Ex example : Coffee Black")
    @Size(min = 1, max = 45, message = "Name drinks description within 255 characters ! ")
    @Column(unique = true)
    private String name;

    private Long type ;

    @NotEmpty(message = "Number of drinks not empty")
    @Min(value = 0, message = "Number of drinks must be greater than 0 !")
    private int quantity ;

    @Digits(integer = 12, fraction = 0 )
    @Min(0)
    private BigDecimal price ;

    private String description ;

    @Value("${file-upload}")
    private String fileUpload;

    public Drinks toDrinks(Type type) {
        MultipartFile multipartFile = image;
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(image.getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
      Drinks drinks = new Drinks() ;
      drinks.setId(id);
      drinks.setName(name);
      drinks.setImage(fileName);
      drinks.setType(type);
      drinks.setQuantity(quantity);
      drinks.setPrice(price);
      drinks.setDescription(description) ;
      return drinks ;
    }
}
