package org.mmy.camel;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class MyBean {
    private Integer id;
    private String name;
}