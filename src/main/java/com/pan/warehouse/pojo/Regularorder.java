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
public class Regularorder {
    private String rid;
    private String gid;
    private int amount;
    private Date ntime;
    private int rtime;
    private int rnumber;
}
