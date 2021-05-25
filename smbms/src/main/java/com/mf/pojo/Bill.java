package com.mf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    private Integer id;
    private String billCode;
    private String productName;
    private String productDesc;
    private String productUnit;
    private BigDecimal productCount;
    private BigDecimal totalPrice;
    private Integer isPayment;
    private String createdBy;
    private Date creationDate;
    private String modifyBy;
    private Date modifyDate;
    private String providerId;

    private String provideName;//供应商名称
}
