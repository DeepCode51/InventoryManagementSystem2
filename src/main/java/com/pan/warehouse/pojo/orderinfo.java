package com.pan.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class orderinfo {
    private String oid;
    private String uname;
    private String receivecity;
    private Date otime;
    private String gname;
    private Integer amount;
    private String state;
}
