package com.pan.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    private String aid;
    private String ausername;
    private String apassword;
    private String aphone;
    private String aaddress;
    private String acity;
}
