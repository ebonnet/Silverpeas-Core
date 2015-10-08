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

import com.silverpeas.search.settings.SearchConfig;
import com.silverpeas.util.CollectionUtil;
import com.silverpeas.util.StringUtil;
import com.stratelia.silverpeas.silvertrace.SilverTrace;
import com.stratelia.webactiv.beans.admin.PaginationPage;
import com.stratelia.webactiv.beans.admin.UserDetail;
import org.silverpeas.core.admin.OrganisationController;
import org.silverpeas.core.admin.OrganisationControllerFactory;
import org.silverpeas.search.searchEngine.model.QueryDescription;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ebonnet
 */
public class RestQueryDescriptionBuilder {

  private UserDetail requester = null;
  private String query = null;
  private List<String> applicationIds;
  private List<String> spaceIds;
  private String taxonomy;
  private String language;
  private boolean onlyAttachment = false;
  private PaginationPage paginationPage = null;
  private String serverUrl= null;

  private OrganisationController organisationController =
      OrganisationControllerFactory.getOrganisationController();

  private SearchConfig searchCfg = new SearchConfig();

  private RestQueryDescriptionBuilder(UserDetail requester) {
    this.requester = requester;
    this.language = requester.getUserPreferences().getLanguage();
  }

  public static RestQueryDescriptionBuilder get(UserDetail requester) {
    return new RestQueryDescriptionBuilder(requester);
  }

  public RestQueryDescriptionBuilder forQuery(String query) {
    this.query = query;
    return this;
  }

  public RestQueryDescriptionBuilder withTaxonomy(String taxonomy) {
    this.taxonomy = taxonomy;
    return this;
  }

  public RestQueryDescriptionBuilder withLanguage(String lang) {
    this.language = lang;
    return this;
  }

  public RestQueryDescriptionBuilder inSpaces(String spaceIds) {
    List<String> sIds = new ArrayList<String>();
    if (StringUtil.isDefined(spaceIds)) {
      for (String spaceId : spaceIds.split(";")) {
        sIds.add(spaceId);
      }
    }
    this.spaceIds = sIds;
    return this;
  }

  public RestQueryDescriptionBuilder inApplications(String applicationIds) {
    //TODO add filter on specific Kmelia applications !!! We only return publications results.
    List<String> appIds = new ArrayList<String>();
    if (StringUtil.isDefined(applicationIds)) {
      for (String applicationId : applicationIds.split(";")) {
        appIds.add(applicationId);
      }
    }
    this.applicationIds = appIds;
    return this;
  }

  public RestQueryDescriptionBuilder filterAttachment(boolean onlyAttachment) {
    this.onlyAttachment = onlyAttachment;
    return this;
  }

  public RestQueryDescriptionBuilder paginate(PaginationPage page) {
    this.paginationPage = page;
    return this;
  }

  public RestQueryDescriptionBuilder fromUrl(final String serverUrl) {
    this.serverUrl = serverUrl;
    return this;
  }

  public RestQueryDescription getRestQueryDescription() {
    QueryDescription queryDescription = new QueryDescription(query);
    queryDescription.setSearchingUser(requester.getId());
    queryDescription.setRequestedLanguage(language);

    List<String> availableIds = buildApplicationListWhereToSearch(spaceIds, applicationIds);
    for (String applicationId : availableIds) {
      queryDescription.addComponent(applicationId);
    }
    return new RestQueryDescription(queryDescription, onlyAttachment, taxonomy, paginationPage,
        serverUrl);
  }


  /**
   * Build the list of application identifiers where to search indexed data.
   * If spaces and apps parameters are null or empty, it returns all available application
   * identifiers which are not excluded from configuration file.
   * Else it returns all applications available from spaces and applications parameters for
   * user who has made the request.
   * @param spaceIds the list of spaces identifiers
   * @param appIds the list of application identifiers
   */
  private List<String> buildApplicationListWhereToSearch(List<String> spaceIds,
      List<String> appIds) {
    SilverTrace.info("search", "RestQueryDescriptionBuilder.buildComponentListWhereToSearch()",
        "root.MSG_GEN_ENTER_METHOD", "spaces = " + spaceIds + ", applications = " + appIds);

    Set<String> applicationList = new HashSet<String>();

    List<String> excludedApplicationIds =
        searchCfg.getComponentsExcludedFromGlobalSearch(requester.getId());
    if (CollectionUtil.isEmpty(spaceIds) && CollectionUtil.isEmpty(appIds)) {
      String[] allowedApplicationIds =
          getOrganisationController().getAvailCompoIds(requester.getId());
      for (String allowedApplicationId : allowedApplicationIds) {
        if (searchCfg.isApplicationSearchable(allowedApplicationId, excludedApplicationIds)) {
          applicationList.add(allowedApplicationId);
        }
      }
    } else {
      if (CollectionUtil.isNotEmpty(spaceIds)) {
        for (String spaceId : spaceIds) {
          String[] allowedApplicationIds =
              getOrganisationController().getAvailCompoIds(spaceId, requester.getId());
          for (String allowedApplicationId : allowedApplicationIds) {
            if (searchCfg.isApplicationSearchable(allowedApplicationId, excludedApplicationIds)) {
              applicationList.add(allowedApplicationId);
            }
          }

        }
      }
      if (CollectionUtil.isNotEmpty(appIds)) {
        for (String applicationId : appIds) {
          if (getOrganisationController().isComponentAvailable(applicationId, requester.getId()) &&
              searchCfg.isApplicationSearchable(applicationId, excludedApplicationIds)) {
            applicationList.add(applicationId);
          }
        }
      }
    }
    return new ArrayList<String>(applicationList);
  }

  private OrganisationController getOrganisationController() {
    return this.organisationController;
  }

}
