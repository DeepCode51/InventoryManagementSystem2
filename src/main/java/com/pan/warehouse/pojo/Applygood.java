package com.pan.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Applygood {
    private String appid;
    private String fromid;
    private String gid;
    private Integer amount;
    private Integer needtime;
    private String state;
}
