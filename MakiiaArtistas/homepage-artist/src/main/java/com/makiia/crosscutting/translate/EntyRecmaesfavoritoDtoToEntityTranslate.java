package com.makiia.crosscutting.translate;

import com.makiia.crosscutting.domain.model.EntyRecmaesfavoritoDto;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.patterns.Translator;
import com.makiia.crosscutting.persistence.entity.EntyRecmaesfavorito;
import com.makiia.crosscutting.utils.GsonUtil;
import org.springframework.stereotype.Component;

@Component
public class EntyRecmaesfavoritoDtoToEntityTranslate implements Translator<EntyRecmaesfavoritoDto, EntyRecmaesfavorito>{

    @Override
    public EntyRecmaesfavorito translate(EntyRecmaesfavoritoDto input) throws EBusinessException {
        return GsonUtil.getGson(false)
                .fromJson(GsonUtil.getGson().toJson(input), EntyRecmaesfavorito.class);
    }

}
