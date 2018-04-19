package sn.firdawsy.webapi.users.services;

import com.rits.cloning.Cloner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sn.firdawsy.webapi.ApplicationTest;
import sn.firdawsy.webapi.authentication.repositories.ProfileRepository;
import sn.firdawsy.webapi.common.models.PageableResult;
import sn.firdawsy.webapi.common.models.Profile;

/**
 * Created by nureynisow on 12/04/2018.
 * for firdawsy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@WebAppConfiguration
@ActiveProfiles("test")
@Slf4j
public class UserServicesTest {

    @Autowired
    private IUserServices userServices;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private Cloner cloner;

    private Profile created;

    @Test
    public void testCreate() {
        Profile profile = Profile.builder()
                .firstName("Jean Marc")
                .lastName("Danton")
                .login("jmarc")
                .password("Passer33)")
                .build();
        Profile created = userServices.create(profile);
        Assert.assertNotNull(created);
        Assert.assertNotNull(created.getGuid());
    }

    @Before
    public void initProfiles() {
        this.removeAllProfiles();
        for (int i = 0; i < 20; i++) {
            Profile profile = Profile.builder()
                    .firstName("Jean Marc")
                    .lastName("Danton")
                    .login("jmarc" + i)
                    .password("Passer33)")
                    .build();
            this.created = this.userServices.create(profile);
        }
    }

    @After
    public void removeAllProfiles() {
        this.profileRepository.deleteAll();
    }

    @Test
    public void testListNotEmptyOrNull() {
        PageableResult<Profile> profilesPage = this.userServices.list(2, 5);
        Assert.assertTrue(CollectionUtils.isNotEmpty(profilesPage.getContent()));
    }

    @Test
    public void testListPaging() {
        PageableResult<Profile> profilesPage = this.userServices.list(2, 5);
        Assert.assertEquals(profilesPage.getNbElements(), 20);
        Assert.assertEquals(profilesPage.getNbPages(), 4);
        Assert.assertTrue(profilesPage.getContent().stream().anyMatch(profile -> profile.getLogin().equals("jmarc5")));
    }


    @Test
    public void testGetUser() {
        Assert.assertTrue(this.userServices.getUser(created.getGuid()).isPresent());
        Assert.assertEquals(this.userServices.getUser(created.getGuid()).get().getLogin(), "jmarc19");
    }

    @Test
    public void testUpdate() {
        final String toUpdateId = created.getGuid();
        Profile newVal = cloner.deepClone(created);
        newVal.setLastName("Test Update");
        Profile updated = this.userServices.update(toUpdateId, newVal);
        Assert.assertEquals(updated.getLastName(), "Test Update");
        updated = this.userServices.getUser(toUpdateId).orElse(null);
        Assert.assertNotNull(updated);
        Assert.assertEquals(updated.getLastName(), "Test Update");
    }

    @Test
    public void testDelete() {
        userServices.delete(created.getGuid());
        Assert.assertFalse(profileRepository.findByGuid(created.getGuid()).isPresent());
    }
}