package com.makiia.crosscutting.translate;

import com.makiia.crosscutting.domain.model.EntyRecmaesfavoritoDto;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.patterns.Translator;
import com.makiia.crosscutting.persistence.entity.EntyRecmaesfavorito;
import com.makiia.crosscutting.utils.GsonUtil;
import org.springframework.stereotype.Component;

@Component
public class EntyRecmaesfavoritoSaveResponseTranslate implements Translator<EntyRecmaesfavorito, EntyRecmaesfavoritoDto>{

    @Override
    public EntyRecmaesfavoritoDto translate(EntyRecmaesfavorito input) throws EBusinessException {
        return GsonUtil.getGson().fromJson(GsonUtil.getGson().toJson(input), EntyRecmaesfavoritoDto.class);
    }
}
