package webflux.demo.customer.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Constant {
    BANKLISTDEMO_URI_ALL((String)"/all");
    private String val;
}
