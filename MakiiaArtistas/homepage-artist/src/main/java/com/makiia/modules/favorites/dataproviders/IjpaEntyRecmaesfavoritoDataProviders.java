package com.makiia.modules.favorites.dataproviders;

import com.makiia.crosscutting.domain.model.EntyRecmaesfavoritoDto;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.modules.bus.contracts.IjpaDataProviders;

public interface IjpaEntyRecmaesfavoritoDataProviders extends IjpaDataProviders<EntyRecmaesfavoritoDto> {
    EntyRecmaesfavoritoDto update(String id, EntyRecmaesfavoritoDto dto) throws EBusinessException;
    void delete(String id) throws EBusinessException;

}
