/*
 * Copyright (C) 2000 - 2015 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception. You should have received a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "https://www.silverpeas.org/legal/floss_exception.html"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.silverpeas.search.web;

import com.silverpeas.annotation.Authorized;
import com.silverpeas.annotation.RequestScoped;
import com.silverpeas.annotation.Service;
import com.silverpeas.util.StringUtil;
import com.silverpeas.web.RESTWebService;
import com.stratelia.silverpeas.pdcPeas.model.GlobalSilverResult;
import com.stratelia.silverpeas.peasCore.URLManager;
import com.stratelia.silverpeas.silvertrace.SilverTrace;
import org.silverpeas.search.PlainSearchResult;
import org.silverpeas.search.SearchEngineFactory;
import org.silverpeas.search.searchEngine.model.MatchingIndexEntry;
import org.silverpeas.search.searchEngine.model.ParseException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * A REST Web resource representing a search query.
 * It is a web service that provides an access to a search result referenced by its URL.
 */
@Service
@RequestScoped
@Path(SearchResourceURIs.SEARCH_BASE_URI + "/" + SearchResourceURIs.SEARCH_QUERY_URI)
@Authorized
public class SearchResource extends RESTWebService {


  protected URI identifiedBy(URI uri) {
    return uri;
  }

  @Override
  public String getComponentId() {
    return null;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<SearchResultEntity> search(@QueryParam("q") String query,
      @DefaultValue("false") @QueryParam("file") boolean file, @QueryParam("appids") String appIds,
      @QueryParam("spaceids") String spaceIds, @QueryParam("taxonomy") String taxonomy,
      @QueryParam("page") String page) {
    if (!StringUtil.isDefined(query)) {
      throw new WebApplicationException(
          Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("q parameter is mandatory")
              .build());
    }

    RestQueryDescription restQueryDescription =
        RestQueryDescriptionBuilder.get(getUserDetail()).forQuery(query).inSpaces(spaceIds)
            .inApplications(appIds).withTaxonomy(taxonomy).filterAttachment(file)
            .paginate(fromPage(page)).fromUrl(URLManager.getServerURL(getHttpServletRequest()))
            .getRestQueryDescription();

    try {
      PlainSearchResult plainSR =
          SearchEngineFactory.getSearchEngine().search(restQueryDescription.getQueryDescription());
      List<MatchingIndexEntry> mie = plainSR.getEntries();
      List<GlobalSilverResult> gsResults =
          SearchResultLoader.matchingIndexEntries2GlobalSilverResults(mie, restQueryDescription);
      List<SearchResultEntity> results = new ArrayList<SearchResultEntity>();
      for (GlobalSilverResult gsResult : gsResults) {
        results.add(SearchResultEntity.from(gsResult, getUriInfo()));
      }
      return results;

    } catch (ParseException e) {
      SilverTrace.error("search", SearchResource.class.getSimpleName(),
          "problem when making search request", e);
      throw new WebApplicationException(
          Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity("parsing exception")
              .build());
    } catch (Exception e) {
      SilverTrace.error("search", SearchResource.class.getSimpleName(),
          "problem when making search request", e);
      throw new WebApplicationException(
          Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity("unknown exception")
              .build());
    }

  }


}
