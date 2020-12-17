package com.epam.springcloud.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String userName;
    private String product;

    public String getUserName() {
        return userName;
    }

    public String getProduct() {
        return product;
    }
}
