package webflux.demo.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FailureResponse {

    CODE((String)"0"),
    FAIL((String)""),
    MESSAGE(""),
    PATH(""),
    MOMENT((String)"0"),
    BANKLISTDEMO_URI_ALL((String)"/all");

    private String stringVal;
}
