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
import com.silverpeas.util.FileUtil;
import com.silverpeas.util.StringUtil;
import com.silverpeas.util.i18n.I18NHelper;
import com.stratelia.silverpeas.pdcPeas.model.GlobalSilverResult;
import com.stratelia.silverpeas.peasCore.URLManager;
import com.stratelia.silverpeas.silvertrace.SilverTrace;
import com.stratelia.webactiv.beans.admin.ComponentInstLight;
import com.stratelia.webactiv.beans.admin.SpaceInstLight;
import com.stratelia.webactiv.beans.admin.UserDetail;
import com.stratelia.webactiv.beans.admin.indexation.UserIndexation;
import com.stratelia.webactiv.util.FileServerUtils;
import com.stratelia.webactiv.util.ResourceLocator;
import org.silverpeas.admin.web.ComponentEntity;
import org.silverpeas.attachment.AttachmentServiceFactory;
import org.silverpeas.attachment.model.SimpleDocument;
import org.silverpeas.attachment.model.SimpleDocumentPK;
import org.silverpeas.core.admin.OrganisationController;
import org.silverpeas.core.admin.OrganisationControllerFactory;
import org.silverpeas.search.searchEngine.model.MatchingIndexEntry;
import org.silverpeas.viewer.ViewerFactory;
import org.silverpeas.wysiwyg.control.WysiwygController;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ebonnet
 */
public class SearchResultLoader {

  private static final String locationSeparator = ">";

  private static OrganisationController orgaController =
      OrganisationControllerFactory.getOrganisationController();

  private static SearchConfig searchCfg = new SearchConfig();

  private ResourceLocator getSettings() {
    return searchCfg.getSettings();
  }

  public SearchResultLoader() {

  }


  /**
   * Transform a MatchingIndexEntry into a GLobalSilverResult which contains all information used
   * to build the response entity
   * @param matchingIndexEntries - a list of MatchingIndexEntry retrieve from search engine
   * @return a list of consolidated data
   */
  public static List<GlobalSilverResult> matchingIndexEntries2GlobalSilverResults(
      List<MatchingIndexEntry> matchingIndexEntries, RestQueryDescription restQD) throws Exception {
    SilverTrace.info("search", "SearchResultLoader.matchingIndexEntries2GlobalSilverResults()",
        "root.MSG_GEN_ENTER_METHOD");
    List<GlobalSilverResult> results = new ArrayList<GlobalSilverResult>();
    if (matchingIndexEntries == null || matchingIndexEntries.isEmpty()) {
      return results;
    }

    // Initialize loop variables
    Map<String, String> places = new HashMap<String, String>();
    List<String> wysiwygSuffixes = new ArrayList<String>();
    for (String language : I18NHelper.getAllSupportedLanguages()) {
      wysiwygSuffixes.add(WysiwygController.WYSIWYG_CONTEXT + "_" + language + ".txt");
    }

    try {
      String requestedLanguage = restQD.getQueryDescription().getRequestedLanguage();
      for (MatchingIndexEntry mie : matchingIndexEntries) {

        // reinitialisation
        String title = mie.getTitle();
        String componentId = mie.getComponent();

        GlobalSilverResult gsr = new GlobalSilverResult(mie);
        gsr.setResultId(matchingIndexEntries.indexOf(mie));

        SilverTrace.info("search", "SearchResultLoader.matchingIndexEntries2GlobalSilverResults()",
            "root.MSG_GEN_PARAM_VALUE", "title= " + title);

        // WARNING : LINE BELOW HAS BEEN ADDED TO NOT SHOW WYSIWYG ALONE IN SEARCH RESULT PAGE
        if (isWysiwyg(title, wysiwygSuffixes) &&
            (componentId.startsWith("kmelia") || componentId.startsWith("kmax"))) {
          continue;
        }


        String userId = mie.getCreationUser();
        if (StringUtil.isDefined(userId)) {
          gsr.setCreatorName(UserDetail.getById(userId).getDisplayedName());
        }

        String place = "";
        String accessLink = restQD.getServerBaseUrl();
        String resultType = mie.getObjectType();
        if (!StringUtil.isDefined(resultType)) {
          resultType = "";
        }

        if (resultType.startsWith("Attachment")) {
          if (!componentId.startsWith("webPages")) {
            try {
              accessLink += getAttachmentUrl(gsr);
            } catch (Exception e) {
              SilverTrace
                  .warn("search", "SearchResultLoader.matchingIndexEntries2GlobalSilverResults()",
                      "Cannot get download link from attachment", e);
            }
          } else {
            accessLink += URLManager.getSimpleURL(URLManager.URL_COMPONENT, componentId);
          }
        } else if (resultType.equals("LinkedFile")) {
          // open the linked file inside a popup window
          accessLink = FileServerUtils
              .getUrl(mie.getTitle(), mie.getObjectId(), FileUtil.getMimeType(mie.getTitle()));
        } else if (resultType.equals("TreeNode")) {
          // the PDC uses this type of object.
          String objectId = mie.getObjectId();
          String treeId = objectId.substring(objectId.indexOf("_") + 1, objectId.length());
          String valueId = objectId.substring(0, objectId.indexOf("_"));
          String uniqueId = treeId + "_" + valueId;
          SilverTrace.warn("pdcPeas", "PdcSearchRequestRouter.buildResultList()",
              "root.MSG_GEN_PARAM_VALUE", "uniqueId= " + uniqueId);
          accessLink +=
              URLManager.getApplicationURL() + "/RpdcSearch/jsp/AxisTree?query=&uniqueId=" +
                  uniqueId;
        } else if (resultType.equals("Space")) {
          // retour sur l'espace
          String spaceId = mie.getObjectId();
          accessLink += URLManager.getPermalink(URLManager.Permalink.Space, spaceId);
        } else if (resultType.equals("Component")) {
          // retour sur le composant
          componentId = mie.getObjectId();
          accessLink += URLManager.getPermalink(URLManager.Permalink.Component, componentId);
          place = getResultPlace(componentId, places, requestedLanguage);
        } else if ("Node".equals(resultType)) {
          accessLink +=
              URLManager.getPermalink(URLManager.Permalink.Folder, mie.getObjectId());
          place = getResultPlace(componentId, places, requestedLanguage);
        } else if ("Publication".equals(resultType)) {
          accessLink +=
              URLManager.getPermalink(URLManager.Permalink.Publication, mie.getObjectId());
          place = getResultPlace(componentId, places, requestedLanguage);
        } else if (componentId.startsWith("user@")) {
          UserDetail user = UserDetail.getById(componentId.substring(5, componentId.indexOf("_")));
          String component = componentId.substring(componentId.indexOf("_") + 1);
          place = user.getDisplayedName() + " " + locationSeparator + " " + component;
          accessLink += URLManager.getApplicationURL() + URLManager.getURL(resultType) +
              mie.getPageAndParams();
        } else if (componentId.equals("users")) {
          place = "";
        } else if (componentId.equals("pdc")) {
          place = searchCfg.getSettings().getString("pdcPeas.pdc");
        } else if (UserIndexation.OBJECT_TYPE.equals(resultType)) {
          UserDetail userDetail = UserDetail.getById(mie.getPK().getObjectId());
          if (userDetail != null) {
            gsr.setThumbnailURL(userDetail.getSmallAvatar());
          }
          accessLink += "/Rprofil/jsp/Main?userId=" + mie.getPK().getObjectId();
        } else {
          accessLink += getUrl(URLManager.getApplicationURL(), mie);
          place = getResultPlace(componentId, places, requestedLanguage);
        }
        gsr.setLocation(place);
        if (StringUtil.isDefined(accessLink)) {
          gsr.setDownloadLink(accessLink);
        }

        results.add(gsr);
      }
    } catch (Exception e) {
      //TODO remove this try/catch
      e.printStackTrace();
    }

    if (places != null) {
      places.clear();
    }
    return results;
  }

  private static String getResultPlace(final String componentId, final Map<String, String> places,
      final String requestedLanguage) {
    String place = places.get(componentId);
    if (place == null) {
      place = getLocation(componentId, requestedLanguage);
      places.put(componentId, place);
    }
    return place;
  }

  private static String getApplicationURL(final String componentId,
      final String requestedLanguage) {
    String accessLink = null;
    ComponentInstLight componentInst =
        getOrganisationController().getComponentInstLight(componentId);
    if (componentInst != null) {
      ComponentEntity componentEntity =
          ComponentEntity.createFrom(componentInst, requestedLanguage);
      accessLink = componentEntity.getUrl();
    }
    return accessLink;
  }

  private static boolean isWysiwyg(String filename, List<String> wysiwygSuffixes) {
    for (String suffix : wysiwygSuffixes) {
      if (filename.endsWith(suffix)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get location of result
   * @param instanceId - application id
   * @param language
   * @return location as a String
   */
  private static String getLocation(String instanceId, final String language) {
    ComponentInstLight componentInst =
        getOrganisationController().getComponentInstLight(instanceId);
    if (componentInst != null) {
      String spaceId = componentInst.getDomainFatherId();
      return getSpaceLabel(spaceId, language) + " " + locationSeparator + " " +
          getComponentLabel(spaceId, instanceId, language);
    }
    return "";
  }

  /**
   * Returns the label of the given domain/space
   */
  private static String getSpaceLabel(String spaceId, final String language) {
    SpaceInstLight spaceInst = getOrganisationController().getSpaceInstLightById(spaceId);
    if (spaceInst != null) {
      return spaceInst.getName(language);
    } else {
      return spaceId;
    }
  }

  /**
   * Returns the label of the given component
   */
  private static String getComponentLabel(String spaceId, String componentId,
      final String language) {
    ComponentInstLight componentInst = null;
    try {
      if (!spaceId.startsWith("user@") && !spaceId.equals("transverse")) {
        componentInst = orgaController.getComponentInstLight(componentId);
      }
    } catch (Exception e) {
      SilverTrace.warn("pdcPeas", "searchEngineSessionController.getComponentLabel()",
          "pdcPeas.EXE_PREFIX_NULL", "spaceId= " + spaceId);
    }

    if (componentInst != null) {
      if (componentInst.getLabel().length() > 0) {
        return componentInst.getLabel(language);
      } else {
        return componentInst.getName(language);
      }
    } else {
      return componentId;
    }
  }


  private static OrganisationController getOrganisationController() {
    return orgaController;
  }

  private static String getAttachmentUrl(GlobalSilverResult gsr) throws Exception {
    String componentId = gsr.getIndexEntry().getComponent();
    String id = gsr.getAttachmentId();
    String language = gsr.getAttachmentLanguage();

    SimpleDocumentPK documentPk = new SimpleDocumentPK(id, componentId);
    SimpleDocument document =
        AttachmentServiceFactory.getAttachmentService().searchDocumentById(documentPk, language);

    // check if attachment is previewable and viewable
    File attachmentFile = new File(document.getAttachmentPath());
    boolean previewable = ViewerFactory.getPreviewService().isPreviewable(attachmentFile);
    boolean viewable = ViewerFactory.getViewService().isViewable(attachmentFile);

    gsr.setPreviewable(previewable);
    gsr.setViewable(viewable);
    gsr.setVersioned(false);
    gsr.setDownloadAllowedForReaders(document.isDownloadAllowedForReaders());
    //gsr.setUserAllowedToDownloadFile(document.isDownloadAllowedForRolesFrom(getUserDetail()));

    String urlAttachment = document.getAttachmentURL();
    return FileServerUtils.getApplicationContext() + urlAttachment;
  }

  private static String getVersioningUrl(GlobalSilverResult gsr) throws Exception {
    String componentId = gsr.getIndexEntry().getComponent();
    String documentId = gsr.getAttachmentId();
    SilverTrace
        .info("pdcPeas", "PdcSearchRequestRouter.getVersioningUrl", "root.MSG_GEN_PARAM_VALUE",
            "documentId = " + documentId + ", componentId = " + componentId);
    SimpleDocument document = AttachmentServiceFactory.getAttachmentService()
        .searchDocumentById(new SimpleDocumentPK(documentId, componentId), null);
    SimpleDocument version = document.getLastPublicVersion();

    if (version != null) {
      // check if attachment is previewable and viewable
      File file = new File(version.getAttachmentPath());
      boolean previewable = ViewerFactory.getPreviewService().isPreviewable(file);
      boolean viewable = ViewerFactory.getViewService().isViewable(file);

      gsr.setPreviewable(previewable);
      gsr.setViewable(viewable);
      gsr.setVersioned(true);
      gsr.setDownloadAllowedForReaders(document.isDownloadAllowedForReaders());
      //gsr.setUserAllowedToDownloadFile(document.isDownloadAllowedForRolesFrom(getUserDetail()));

      // process download link
      return FileServerUtils.getApplicationContext() + document.getAttachmentURL();
    }
    return null;
  }

  public static String getUrl(String urlBase, MatchingIndexEntry indexEntry) {
    return getUrl(urlBase, indexEntry.getComponent(), indexEntry.getPageAndParams());
  }

  public static String getUrl(String urlBase, String componentId, String pageAndParams) {
    String url = urlBase + URLManager.getURL(null, componentId) + pageAndParams;
    if (url.contains("?")) {
      url += "&From=Search";
    } else {
      url += "?From=Search";
    }
    return url;
  }

}
