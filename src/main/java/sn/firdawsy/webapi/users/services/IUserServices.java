package sn.firdawsy.webapi.users.services;

import sn.firdawsy.webapi.common.models.PageableResult;
import sn.firdawsy.webapi.common.models.Profile;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by nureynisow on 07/04/2018.
 * for firdawsy
 */
public interface IUserServices {


    /**
     * Create a user
     *
     * @param profile user to create
     * @return created user
     */
    Profile create(Profile profile);

    /**
     * List all user profiles by page
     *
     * @param page page
     * @param size size per page
     * @return profiles
     */
    PageableResult<Profile> list(Integer page, Integer size);

    /**
     * Get a specific user with a given id
     *
     * @param id id
     * @return a user if exist
     */
    Optional<Profile> getUser(String id);


    /**
     * Update a specific user profile
     *
     * @param toUpdateId
     * @param toUpdate   new Profile data
     * @return updated user
     */
    Profile update(String toUpdateId, Profile toUpdate);

    /**
     * Delete a given userProfile
     *
     * @param id of the user
     */
    void delete(String id);

    default String buildGuid() {
        return UUID.randomUUID().toString();
    }


}
