package com.mf.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Provide {
    private Integer id;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proContact;
    private String  proPhone;
    private String  proAddress;
    private String  proFax;
    private String createdBy;
    private Date creationDate;
    private String modifyBy;
    private Date modifyDate;

}
