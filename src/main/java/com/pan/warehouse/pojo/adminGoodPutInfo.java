package com.pan.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class adminGoodPutInfo {
    private String gid;
    private String gname;
    private String fid;
    private String fname;
    private String uid;
    private String uname;
    private Integer amount;
}
