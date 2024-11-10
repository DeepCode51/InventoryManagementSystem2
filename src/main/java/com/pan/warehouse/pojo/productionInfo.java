package com.pan.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class productionInfo {
    private String pid;
    private String gid;
    private String gname;
    private int amount;
}
