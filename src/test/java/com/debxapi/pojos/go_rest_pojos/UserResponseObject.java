package com.debxapi.pojos.go_rest_pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties({"code","meta"})
public class UserResponseObject {
    @JsonProperty("data")
    private User user;
}
