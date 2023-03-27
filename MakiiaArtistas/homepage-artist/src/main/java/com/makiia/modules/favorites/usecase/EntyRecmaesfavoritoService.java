package com.makiia.modules.favorites.usecase;
import com.makiia.crosscutting.domain.model.EntyRecmaesfavoritoDto;
import com.makiia.modules.bus.services.UseCase;
import com.makiia.modules.bus.services.UsecaseServices;
import com.makiia.modules.favorites.dataproviders.jpa.JpaEntyRecmaesfavoritoDataProviders;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
@Log4j2
@UseCase
public class EntyRecmaesfavoritoService extends UsecaseServices<EntyRecmaesfavoritoDto, JpaEntyRecmaesfavoritoDataProviders> {
    @Autowired
    private JpaEntyRecmaesfavoritoDataProviders jpaDataProviders;
    @PostConstruct
    public void init() {
        this.ijpaDataProvider = jpaDataProviders;
    }
}
