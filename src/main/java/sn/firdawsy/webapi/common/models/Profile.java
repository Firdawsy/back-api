package sn.firdawsy.webapi.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * by osow on 15/11/17.
 * for kiss-api
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    private String guid;

    private String login;

    @JsonIgnore
    private String password;

    private String firstName, lastName;
}
