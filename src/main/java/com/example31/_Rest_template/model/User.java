package com.example31._Rest_template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data//ломбок аннотация: генерирует геттеры, сеттеры, икуалс, хеш код методы
@NoArgsConstructor//ломбок аннотация: конструктор без аргуметов
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;


}
