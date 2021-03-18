package webflux.demo.customer.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class CifDto {
    private String id;
    private String description;
    private String base;
    private Boolean strict;
    private Boolean idInjection;
    private List<BankListDemo> bankList;
    private Date created;
}
