package com.pan.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class putGoodInfo {
    private String sid;
    private String psid;
    private String sstate;
    private String gid;
    private String gname;
    private Integer amount;
}
