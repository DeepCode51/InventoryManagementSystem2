package com.pan.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ticketGood {
    private String tid;
    private String tname;
    private String gname;
    private String fname;
}
