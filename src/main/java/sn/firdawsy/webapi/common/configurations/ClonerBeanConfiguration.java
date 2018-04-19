package sn.firdawsy.webapi.common.configurations;

import com.rits.cloning.Cloner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by nureynisow on 14/04/2018.
 * for firdawsy
 */
@Configuration
public class ClonerBeanConfiguration {
    @Bean
    public Cloner getCloner(){
        return new Cloner();
    }
}
