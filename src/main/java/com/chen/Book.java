package com.chen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode//���ں��ڵ�ȥ��ʹ��
public class Book {
    //id
    private Long id;
    //����
    private String name;

    //����
    private String category;

    //����
    private Integer score;

    //���
    private String intro;

}