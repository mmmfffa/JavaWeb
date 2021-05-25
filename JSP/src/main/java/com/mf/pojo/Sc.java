package com.mf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sc {
    private String  Sno ;
    private String  Cno ;
    private float   Grade;
    private String  newGrade;
}
