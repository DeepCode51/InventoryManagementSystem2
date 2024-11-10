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
public class regularorderInfo {
    private String rid;
    private String gname;
    private int amount;
    private Date ntime;
    private int rtime;
    private int rnumber;
}
