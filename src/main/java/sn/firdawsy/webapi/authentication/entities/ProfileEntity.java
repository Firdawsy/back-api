package sn.firdawsy.webapi.authentication.entities;


import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import sn.firdawsy.webapi.common.models.EntityCore;

/**
 * by osow on 15/11/17.
 * for kiss-api
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Document(collection = "profiles")
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity extends EntityCore {

    private String guid;

    @Indexed(unique = true)
    private String login;

    private String password;

    private String firstName, lastName;
}
