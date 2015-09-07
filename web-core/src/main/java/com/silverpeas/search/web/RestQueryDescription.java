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

import com.stratelia.silverpeas.pdc.model.SearchCriteria;
import com.stratelia.webactiv.beans.admin.PaginationPage;
import org.silverpeas.search.searchEngine.model.QueryDescription;

import java.util.List;

/**
 * @author ebonnet
 */
public class RestQueryDescription {
  private QueryDescription queryDescription;
  private boolean file;
  private List<SearchCriteria> taxonomyCriteria;
  private PaginationPage paginationPage;
  private String serverUrl;

  public RestQueryDescription(QueryDescription queryDescription, boolean file, String taxonomy,
      PaginationPage page, String serverUrl) {
    this.queryDescription = queryDescription;
    this.file = file;
    this.taxonomyCriteria = SearchCriteria.getCriterias(taxonomy);
    this.paginationPage = page;
    this.serverUrl = serverUrl;
  }

  public QueryDescription getQueryDescription() {
    return queryDescription;
  }

  public boolean isFile() {
    return file;
  }

  public List<SearchCriteria> getTaxonomyCriteria() {
    return taxonomyCriteria;
  }

  public PaginationPage getPaginationPage() {
    return paginationPage;
  }

  public String getServerBaseUrl() {
    return serverUrl;
  }

}
