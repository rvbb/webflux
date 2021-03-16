package webflux.demo.customer.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cif")
public class Cif {

	@Id
    private String id;
	
    private String description;
    
    private String base;
    
    private Boolean strict;
    
    private Boolean idInjection;
    
    private List<BankListDemo> banklist;
    
    @CreatedDate
    private Date created;

}
