package com.makiia.modules.bus.contracts;

import com.makiia.crosscutting.domain.model.EntyRecmaesfavoritoResponse;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;

import java.util.List;

public interface  IjpaDataProviders<T> {

    List<T> getAll() throws EBusinessException;

    EntyRecmaesfavoritoResponse getAll (int currentPage , int PageSize, String filter) throws EBusinessException;

    T get(String id) throws EBusinessException;

    T save(T dto) throws EBusinessException;

    List<T> save(List<T> dto) throws EBusinessException;

    T update(String id, T dto) throws EBusinessException;

    void delete(String id) throws EBusinessException;
}
