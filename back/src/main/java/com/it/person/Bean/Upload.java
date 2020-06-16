package com.it.person.Bean;

import lombok.Data;
import java.io.Serializable;

@Data
public class Upload implements Serializable {
    private Integer id;
    private String username;
    private String filename;
    private String type;
    private Integer size;
}
