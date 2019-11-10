package webflux.demo.customer.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
//import org.json.JSONObject;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cif")
public class Cif {

	@Id
    private String id;
	
    @ApiModelProperty(notes = "description", example = "Describe about customer")
    private String description;
    
    @ApiModelProperty(notes = "base")
    private String base;
    
    @ApiModelProperty(notes = "strict")
    private Boolean strict;
    
    @ApiModelProperty(notes = "idInjection")
    private Boolean idInjection;
    
    @ApiModelProperty(notes = "banklist")
    private List<BankListDemo> banklist;
    
    @ApiModelProperty(notes = "created")
    @CreatedDate
    private Date created;


//    @Override
//    public String toString() {
//        return new JSONObject(this).toString();
//    }
}
