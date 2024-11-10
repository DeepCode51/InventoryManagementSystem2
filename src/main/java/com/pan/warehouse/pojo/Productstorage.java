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
public class Productstorage {
    private String psid;
    private String gname;
    private Integer amount;
    private Date accesstime;
    private Date badtime;
}
