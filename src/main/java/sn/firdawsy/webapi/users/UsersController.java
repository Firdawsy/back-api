package sn.firdawsy.webapi.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sn.firdawsy.webapi.common.models.Profile;
import sn.firdawsy.webapi.users.services.IUserServices;

import java.util.List;

/**
 * Created by nureynisow on 06/04/2018.
 * for firdawsy
 */
@Slf4j
@RestController
@RequestMapping("users")
public class UsersController {

    private static final String LOG_HEADER = "[USER CONTROLLER]";

    @Autowired
    private IUserServices userServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Profile>> getAllUsers(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        log.info("{} get all users", LOG_HEADER);
        //  return new ResponseEntity<>(this.userServices.list(page, size), HttpStatus.OK);
        return null;
    }

}
