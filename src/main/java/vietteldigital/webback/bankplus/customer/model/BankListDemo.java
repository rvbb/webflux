package vietteldigital.webback.bankplus.customer.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankListDemo {
    private long id;
    private String bankName;
    
    private int type;
    
    private Date created;
}
