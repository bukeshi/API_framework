package com.debxapi.pojos.go_rest_pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties({"meta","code"})
public class UserResponseList {
    @JsonProperty("data")
    private List<User> userList;
}
