package edu.upm.midas.repository.jpa;


import edu.upm.midas.model.jpa.Source;

import java.util.List;

/**
 * Created by gerardo on 28/04/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationDiseasesWikipedia
 * @className SourceService
 * @see
 */
public interface SourceRepository {

    Source findById(String sourceId);

    Source findByNameQuery(String name);

    String findIdByNameNative(String name);

    Source findByIdNative(String sourceId);

    List<Source> findAllNative();

    void persist(Source source);

    int insertNative(String sourceId, String name);

    boolean deleteById(String sourceId);

    void delete(Source source);

    Source update(Source source);

    int updateByIdQuery(Source source);

}
