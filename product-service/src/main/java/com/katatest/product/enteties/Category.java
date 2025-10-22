package com.katatest.product.enteties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


@Entity
public class Category {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;
    public String Name;
    public String Description;
    
}
