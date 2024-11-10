package com.pan.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String uid;
    private String uname;
    private String uusername;
    private String upassword;
    private String uphone;
    private String uaddress;
    private String ucity;
}
