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

package com.silverpeas.search.settings;

import com.silverpeas.util.CollectionUtil;
import com.silverpeas.util.StringUtil;
import com.stratelia.silverpeas.pdc.control.PdcBm;
import com.stratelia.silverpeas.pdc.control.PdcBmImpl;
import com.stratelia.silverpeas.pdc.model.PdcException;
import com.stratelia.silverpeas.silvertrace.SilverTrace;
import com.stratelia.webactiv.util.ResourceLocator;
import org.silverpeas.core.admin.OrganisationController;
import org.silverpeas.core.admin.OrganisationControllerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author ebonnet
 */
public class SearchConfig {

  public static final String SEARCH_LOCATION_SEPARATOR = ">";

  private static ResourceLocator defaultResourceLocator =
      new ResourceLocator("org.silverpeas.pdcPeas.settings.pdcPeasSettings", "");

  private ResourceLocator resourceLocator = null;

  private boolean platformUsesPDC = false;
  private boolean includeUsers = false;
  // Activate export
  private boolean isExportEnabled = false;
  private boolean isRefreshEnabled = false;

  // External search configuration
  private boolean isEnableExternalSearch;
  private String curServerName = null;
  private List<ExternalSPConfigVO> externalServers = null;

  private PdcBm pdcBm = null;

  public ResourceLocator getSettings() {
    if (resourceLocator != null) {
      return resourceLocator;
    }
    return defaultResourceLocator;
  }

  private PdcBm getPdcBm() {
    if (pdcBm ==null) {
      pdcBm = new PdcBmImpl();
    }
    return pdcBm;
  }

  private static OrganisationController getOrganisationController() {
    return OrganisationControllerFactory.getOrganisationController();
  }

  public SearchConfig() {
    this(null);
  }

  public SearchConfig(ResourceLocator resourceLocator) {
    this.resourceLocator = resourceLocator;
    isExportEnabled = isExportLicenseOK();
    isRefreshEnabled = getSettings().getBoolean("EnableRefresh", true);
    isRefreshEnabled = getSettings().getBoolean("EnableRefresh", true);
    try {
      platformUsesPDC = !getPdcBm().getAxis().isEmpty();
    } catch (PdcException e) {
      SilverTrace.info("pdcPeas", "PdcSearchSessionController()", "root.MSG_GEN_ERROR", e);
    }
    includeUsers = getSettings().getBoolean("search.users.included", false);
    isEnableExternalSearch = getSettings().getBoolean("external.search.enable", false);
    getExternalSPConfig();
  }

  private boolean isExportLicenseOK() {
    ResourceLocator resource = new ResourceLocator("license.license", "");
    String code = resource.getString("export");

    boolean validSequence = true;
    String serial = "643957685";
    try {
      for (int i = 0; i < 9 && validSequence; i++) {
        String groupe = code.substring(i * 3, i * 3 + 3);
        int total = 0;
        for (int j = 0; j < groupe.length(); j++) {
          String valeur = groupe.substring(j, j + 1);
          total += Integer.parseInt(valeur);
        }
        if (total != Integer.parseInt(serial.substring(i * 1, i * 1 + 1))) {
          validSequence = false;
        }
      }
    } catch (Exception e) {
      validSequence = false;
    }
    return validSequence;
  }

  /**
   * Retrieve the external silverpeas server configuration from pdcPeasSettings file<br> Using the
   * following keys<br> <ul> <li>external.search.server.CPT.name=ADEF</li>
   * <li>external.search.server.CPT.data.path=D:\\silverpeas\\data</li>
   * <li>external.search.server.CPT.component.filters=kmelia</li>
   * <li>external.search.server.CPT.url=http://monserveur/silverpeas</li> </ul> Where CPT is the
   * number of external servers starting from 1 to N
   */
  private void getExternalSPConfig() {
    if (isEnableExternalSearch) {
      curServerName = getSettings().getString("server.name");
      externalServers = new ArrayList<ExternalSPConfigVO>();
      String prefixKey = "external.search.server.";
      String nameKey = ".name";
      String pathKey = ".data.path";
      String urlKey = ".url";
      String filterKey = ".component.filters";
      int cptSrv = 1;
      String srvName = getSettings().getString(prefixKey + cptSrv + nameKey);
      while (StringUtil.isDefined(srvName)) {
        String path = getSettings().getString(prefixKey + cptSrv + pathKey);
        String url = getSettings().getString(prefixKey + cptSrv + urlKey);
        String components = getSettings().getString(prefixKey + cptSrv + filterKey);
        String[] componentsArray = components.split(",");
        externalServers.add(new ExternalSPConfigVO(srvName, cptSrv, path,
            Arrays.asList(componentsArray), url));
        // Loop increase
        cptSrv++;
        srvName = getSettings().getString(prefixKey + cptSrv + nameKey);
      }
    }
  }

  public List<String> getComponentsExcludedFromGlobalSearch(String userId) {
    List<String> excluded = new ArrayList<String>();

    // exclude all components of all excluded spaces
    List<String> spaces = getSpaceItemsExcludedFromGlobalSearch();
    for (String space : spaces) {
      String[] availableComponentIds =
          getOrganisationController().getAvailCompoIds(space, userId);
      excluded.addAll(Arrays.asList(availableComponentIds));
    }

    // exclude components explicitly excluded
    List<String> components = getApplicationItemsExcludedFromGlobalSearch();
    excluded.addAll(components);

    return excluded;
  }

  private List<String> getSpaceItemsExcludedFromGlobalSearch() {
    return getItemsExcludedFromGlobalSearch("SpacesExcludedFromGlobalSearch");
  }

  private List<String> getApplicationItemsExcludedFromGlobalSearch() {
    return getItemsExcludedFromGlobalSearch("ComponentsExcludedFromGlobalSearch");
  }


  private List<String> getItemsExcludedFromGlobalSearch(String parameterName) {
    List<String> items = new ArrayList<String>();
    String param = getSettings().getString(parameterName);
    if (StringUtil.isDefined(param)) {
      StringTokenizer tokenizer = new StringTokenizer(param, ",");
      while (tokenizer.hasMoreTokens()) {
        items.add(tokenizer.nextToken());
      }
    }
    return items;
  }

  public boolean isApplicationSearchable(String applicationId, List<String> exclusionList) {
    if (CollectionUtil.isNotEmpty(exclusionList) && exclusionList.contains(applicationId)) {
      return false;
    }
    if (applicationId.startsWith("silverCrawler")
        || applicationId.startsWith("gallery")
        || applicationId.startsWith("kmelia")) {
      boolean isPrivateSearch = "yes".equalsIgnoreCase(getOrganisationController().
          getComponentParameterValue(applicationId, "privateSearch"));
      return !isPrivateSearch;
    }
    return true;
  }


  public boolean isExportEnabled() {
    return isExportEnabled;
  }

  public boolean isRefreshEnabled() {
    return isRefreshEnabled;
  }

  public boolean isIncludeUsers() {
    return includeUsers;
  }

  public boolean isPlatformUsesPDC() {
    return platformUsesPDC;
  }

  public boolean isEnableExternalSearch() {
    return isEnableExternalSearch;
  }

  public String getCurServerName() {
    return curServerName;
  }

  public List<ExternalSPConfigVO> getExternalServers() {
    return externalServers;
  }

}
