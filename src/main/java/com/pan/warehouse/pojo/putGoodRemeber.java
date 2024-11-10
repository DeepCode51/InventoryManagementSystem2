package com.pan.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class putGoodRemeber {
    private String gptid;
    private String psid;
    private String sid;
    private Integer amount;
    private String putstate;
}
