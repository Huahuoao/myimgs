package com.example.huahuoimg.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImgR implements Serializable {
     private String Code;
     private String fileName;
     private String url;
}
