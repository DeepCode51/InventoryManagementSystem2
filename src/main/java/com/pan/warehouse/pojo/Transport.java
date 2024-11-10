package com.pan.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transport {
    private String tsid;
    private String tname;
    private String fromid;
    private String toid;
    private Integer needtime;
    private Integer speed;
}
