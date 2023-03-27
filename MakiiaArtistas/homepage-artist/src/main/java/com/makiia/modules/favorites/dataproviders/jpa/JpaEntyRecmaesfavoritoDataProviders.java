package com.makiia.modules.favorites.dataproviders.jpa;
import com.makiia.crosscutting.domain.model.EntyRecmaesfavoritoDto;
import com.makiia.crosscutting.domain.model.EntyRecmaesfavoritoResponse;
import com.makiia.crosscutting.domain.model.PaginationResponse;
import com.makiia.crosscutting.exceptions.DataProvider;
import com.makiia.crosscutting.exceptions.ExceptionBuilder;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.messages.SearchMessages;
import com.makiia.crosscutting.patterns.Translator;
import com.makiia.crosscutting.persistence.entity.EntyRecmaesfavorito;
import com.makiia.crosscutting.persistence.repository.EntyRecmaesfavoritoRepository;
import com.makiia.modules.favorites.dataproviders.IjpaEntyRecmaesfavoritoDataProviders;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@DataProvider
public class JpaEntyRecmaesfavoritoDataProviders implements IjpaEntyRecmaesfavoritoDataProviders{
  @Autowired
    private EntyRecmaesfavoritoRepository repository;
    @Autowired
    @Qualifier("entyRecmaesfavoritoSaveResponseTranslate")
    private Translator<EntyRecmaesfavorito, EntyRecmaesfavoritoDto> saveResponseTranslate;

    @Autowired
    @Qualifier("entyRecmaesfavoritoDtoToEntityTranslate")
    private  Translator<EntyRecmaesfavoritoDto, EntyRecmaesfavorito> dtoToEntityTranslate;

    @Override
    public List<EntyRecmaesfavoritoDto> getAll() throws EBusinessException {
        List<EntyRecmaesfavoritoDto> dtos = new ArrayList<>();
        try {
            List<EntyRecmaesfavorito> responses = (List<EntyRecmaesfavorito>) repository.findAll();
            if (!responses.isEmpty()) {
                for (EntyRecmaesfavorito response : responses) {
                    dtos.add(saveResponseTranslate.translate(response));
                }
            }
            return dtos;
        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.SEARCH_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.SEARCH_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    @Override
    public EntyRecmaesfavoritoResponse getAll(int currentPage , int totalPageSize ,String filter) throws EBusinessException {
        try {
            Pageable pageable = PageRequest.of(currentPage, totalPageSize);
            Page<EntyRecmaesfavorito>ResponsePage = repository.findNameFavorite(filter,pageable);
            List<EntyRecmaesfavorito> ListPage = ResponsePage.getContent();
            List<EntyRecmaesfavoritoDto> content  = ListPage.stream().map(p ->mapToDto(p)).collect(Collectors.toList());

            EntyRecmaesfavoritoResponse response = new EntyRecmaesfavoritoResponse();

            response.setRspMessage(response.getRspMessage());
            response.setRspValue(response.getRspValue());
            response.setRspPagination(headResponse(ResponsePage.getNumber(),ResponsePage.getSize() , ResponsePage.getTotalElements(),ResponsePage.getTotalPages() , ResponsePage.hasNext(), ResponsePage.hasPrevious()));
            response.setRspData(content);
            return response;


        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.SEARCH_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.SEARCH_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    @Override
    public EntyRecmaesfavoritoDto get(String id) throws EBusinessException {
        try{
            return  saveResponseTranslate.translate(repository.findById(id).get());
        }
        catch (PersistenceException | DataAccessException e){
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.SEARCH_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.SEARCH_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }
    @Override
    public EntyRecmaesfavoritoDto save(EntyRecmaesfavoritoDto dto) throws EBusinessException {
        try{
            return saveResponseTranslate.translate(repository.save(dtoToEntityTranslate.translate(dto)));
          }catch (PersistenceException | DataAccessException e){
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.CREATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.CREATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    @Override
    public List<EntyRecmaesfavoritoDto> save(List<EntyRecmaesfavoritoDto> dtos) throws EBusinessException {
        try{
            List<EntyRecmaesfavorito> entities = new ArrayList<>();
            for(EntyRecmaesfavoritoDto dto : dtos){
                entities.add(dtoToEntityTranslate.translate(dto));
            }
            dtos = new ArrayList<>();
            for (EntyRecmaesfavorito entity: repository.saveAll(entities)){
                dtos.add(saveResponseTranslate.translate(entity));
            }
            return  dtos;
        }catch (PersistenceException | DataAccessException e){
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.CREATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.CREATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    @Override
    public EntyRecmaesfavoritoDto update(String id, EntyRecmaesfavoritoDto dto) throws EBusinessException {
        try{

            EntyRecmaesfavorito entity = dtoToEntityTranslate.translate(dto);
            EntyRecmaesfavorito old = repository.findById(id).get();

            old.setRecNroregRefv(
                    Objects.nonNull(dto.getRecNroregRefv()) && !entity.getRecNroregRefv().isEmpty()
                            ? entity.getRecNroregRefv()
                            : old.getRecNroregRefv());
            old.setRecNroregReus(
                    Objects.nonNull(dto.getRecNroregReus()) && !entity.getRecNroregReus().isEmpty()
                    ? entity.getRecNroregReus()
                    : old.getRecNroregReus());
            old.setRecFecrecRefv(
                    Objects.nonNull(dto.getRecFecrecRefv()) && !entity.getRecFecrecRefv().isEmpty()
                            ? entity.getRecFecrecRefv()
                            : old.getRecFecrecRefv());
            old.setRecOrdvisRefv(
                    Objects.nonNull(dto.getRecOrdvisRefv()) && !entity.getRecOrdvisRefv().equals(0)
                            ? entity.getRecOrdvisRefv()
                            : old.getRecOrdvisRefv());
            old.setRecEstregRefv(
                    Objects.nonNull(dto.getRecEstregRefv()) && !entity.getRecEstregRefv().isEmpty()
                            ? entity.getRecEstregRefv()
                            : old.getRecEstregRefv());

            return  saveResponseTranslate.translate(repository.save(old));

        }catch (PersistenceException | DataAccessException e){
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.UPDATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.UPDATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }
    @Override
    public void delete(String id) throws EBusinessException {
        try {
            repository.delete(repository.findById(id).get());
        }catch (PersistenceException | DataAccessException e){
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.DELETE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.DELETE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }
    private EntyRecmaesfavoritoDto mapToDto(EntyRecmaesfavorito entyRecmaesfavorito){
        EntyRecmaesfavoritoDto entity = new EntyRecmaesfavoritoDto();

        entity.setRecNroregReus(entyRecmaesfavorito.getRecNroregReus());
        entity.setApjNroregAphp(entyRecmaesfavorito.getApjNroregAphp());
        entity.setRecFecrecRefv(entyRecmaesfavorito.getRecFecrecRefv());
        entity.setRecOrdvisRefv(entyRecmaesfavorito.getRecOrdvisRefv());
        entity.setRecEstregRefv(entyRecmaesfavorito.getRecEstregRefv());

        return  entity;
    }

    public static PaginationResponse headResponse(int currentPage    , int totalPageSize ,
                                                  long totalResults  , int totalPages,
                                                  boolean hasNextPage, boolean hasPreviousPage)
    {
        return PaginationResponse.builder()
                .currentPage(currentPage)
                .totalPageSize(totalPageSize)
                .totalResults(totalResults)
                .totalPages(totalPages)
                .hasNextPage(hasNextPage)
                .hasPreviousPage(hasPreviousPage)
                .build();

    }
}
