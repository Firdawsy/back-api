package sn.firdawsy.webapi.users.services;

import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sn.firdawsy.webapi.authentication.entities.ProfileEntity;
import sn.firdawsy.webapi.authentication.repositories.ProfileRepository;
import sn.firdawsy.webapi.common.exceptions.InvalidParamsException;
import sn.firdawsy.webapi.common.models.PageableResult;
import sn.firdawsy.webapi.common.models.Profile;
import sn.firdawsy.webapi.users.exceptions.ProfileNotFoundException;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by nureynisow on 07/04/2018.
 * for firdawsy
 */
@Slf4j
@Service
public class UserServices implements IUserServices {


    private static final String LOG_HEADER = "[USERS SERVICES]";

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public Profile create(Profile profile) {
        final ProfileEntity profileEntity = mapper.map(profile, ProfileEntity.class);
        profileEntity.setGuid(buildGuid());
        log.info("{} creating a new user profile : {}", LOG_HEADER, profileEntity);
        return mapper.map(this.profileRepository.save(profileEntity), Profile.class);
    }

    @Override
    public PageableResult<Profile> list(Integer page, Integer size) {
        log.info("{} get all profiles of page {} with page size = {}", LOG_HEADER, page, size);
        if (page < 1 || size < 1) throw new InvalidParamsException("Page or size incorrect");
        Page<ProfileEntity> pageOfProfile = this.profileRepository.findAll(new PageRequest(page - 1, size));
        return PageableResult.<Profile>builder()
                .content(pageOfProfile.getContent()
                        .parallelStream()
                        .map(p -> mapper.map(p, Profile.class))
                        .collect(Collectors.toList()))
                .nbElements(pageOfProfile.getTotalElements())
                .nbPages(pageOfProfile.getTotalPages())
                .build();
    }

    @Override
    public Optional<Profile> getUser(String guid) {
        log.info("{} get user of guid {}", LOG_HEADER, guid);
        return this.profileRepository.findByGuid(guid)
                .map(profileEntity -> mapper.map(profileEntity, Profile.class));
    }

    @Override
    public Profile update(String toUpdateId, Profile toUpdate) {
        log.info("{} updating user {} with ", LOG_HEADER, toUpdateId, toUpdate);
        ProfileEntity oldVal = this.profileRepository.findByGuid(toUpdateId).orElseGet(null);
        if (oldVal == null) throw new ProfileNotFoundException("Profile not found");
        ProfileEntity newVal = mapper.map(toUpdate, ProfileEntity.class);
        newVal.setId(oldVal.getId());
        log.debug("{} old = {} and new  = {}", LOG_HEADER, oldVal, newVal);
        return mapper.map(this.profileRepository.save(newVal), Profile.class);
    }

    @Override
    public void delete(String guid) {
        log.info("{} deleting user with guid {}", LOG_HEADER, guid);
        this.profileRepository.deleteByGuid(guid);
    }
}
