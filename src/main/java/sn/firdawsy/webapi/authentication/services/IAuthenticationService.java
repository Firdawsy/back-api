package sn.firdawsy.webapi.authentication.services;

import sn.firdawsy.webapi.authentication.models.LoginDataBean;
import sn.firdawsy.webapi.authentication.models.SignupDataBean;
import sn.firdawsy.webapi.common.models.Profile;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * by osow on 15/11/17.
 * for kiss-api
 */
public interface IAuthenticationService {
    /**
     * Register a new user
     *
     * @param signupDataBean information wrapper
     * @return saved {@link Profile}
     */
    Profile signup(SignupDataBean signupDataBean);

    /**
     * Log in a user
     *
     * @param loginDataBean credentials wrapper
     * @return logged in {@link Profile}
     */
    Profile login(LoginDataBean loginDataBean);

    /**
     * Setting JWT in http header
     *
     * @param profile  logged in profile
     * @param response {@link HttpServletResponse} to set token in header
     */
    void setJsonWebTokenUsingProfile(Profile profile, HttpServletResponse response);

    /**
     * Get profile by login
     *
     * @param profileId login
     * @return {@link Profile} if found
     */
    Optional<Profile> getProfile(String profileId);
}
