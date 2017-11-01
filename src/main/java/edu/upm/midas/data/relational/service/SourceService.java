package edu.upm.midas.data.relational.service;


import edu.upm.midas.data.relational.entities.addb.Source;

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
public interface SourceService {

    Source findById(String sourceId);

    Source findByNameQuery(String name);

    String findIdByNameNative(String name);

    Source findByIdNative(String sourceId);

    List<Source> findAllNative();

    void save(Source source);

    int insertNative(String sourceId, String name);

    void delete(Source source);

    Source update(Source source);

}
