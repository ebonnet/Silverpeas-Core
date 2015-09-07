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

import com.silverpeas.web.Exposable;
import com.silverpeas.web.RESTWebService;
import com.stratelia.silverpeas.pdcPeas.model.GlobalSilverResult;
import com.stratelia.silverpeas.peasCore.URLManager;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;
import java.net.URI;
import java.util.Date;

/**
 * @author ebonnet
 */
public class SearchResultEntity implements Exposable {
  private static final long serialVersionUID = 6553624714640978486L;

  private GlobalSilverResult gsr;

  @XmlElement
  public String getTitle() {
    return gsr.getTitle();
  }

  @XmlElement
  public String getDescription() {
    return gsr.getDescription();
  }

  @XmlElement
  public Date getLastUpdateDate() {
    return null;
  }

  @XmlElement
  public String getAccessLink() {
    return gsr.getDownloadLink();
  }

  @XmlElement
  public String getRelevance() {
    return Float.toString(gsr.getRawScore());
  }

  @XmlElement
  public String getAuthor() {
    return gsr.getCreatorName();
  }

  @XmlElement
  public String getLocation() {
    return gsr.getLocation();
  }


  private URI uri;

  @Override
  public URI getURI() {
    return this.uri;
  }

  protected SearchResultEntity decorate(GlobalSilverResult gsr) {
    this.gsr = gsr;
    return this;
  }

  protected SearchResultEntity withURI(URI uri) {
    this.uri = uri;
    return this;
  }

  /**
   * Centralized the build of a search result URI.
   * @param gsr ths global silver result.
   * @return the URI of the search result entity.
   */
  private static URI buildSearchResultURI(GlobalSilverResult gsr) {
    if (gsr == null) {
      return null;
    }
    return UriBuilder.fromUri(URLManager.getApplicationURL())
        .path(RESTWebService.REST_WEB_SERVICES_URI_BASE).path(SearchResourceURIs.SEARCH_BASE_URI)
        .path(SearchResourceURIs.SEARCH_RESULT_URI_PART).build();
  }


  public static SearchResultEntity from(GlobalSilverResult gsr, UriInfo uriInfo) {
    return new SearchResultEntity().decorate(gsr).withURI(buildSearchResultURI(gsr));
  }

}
