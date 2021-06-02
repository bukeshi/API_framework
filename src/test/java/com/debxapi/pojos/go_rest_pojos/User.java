package com.debxapi.pojos.go_rest_pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties({"created_at","updated_at"})
public class User {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;

}
