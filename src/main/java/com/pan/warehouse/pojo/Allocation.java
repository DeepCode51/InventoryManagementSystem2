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
public class Allocation {
    private String alid;
    private String fromid;
    private String toid;
    private String tsid;
    private String state;
    private String gid;
    private Integer amount;
}
