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
public class Order {
    private String oid;
    private String uid;
    private String receivecity;
    private Date otime;
    private String gid;
    private Integer amount;
}
