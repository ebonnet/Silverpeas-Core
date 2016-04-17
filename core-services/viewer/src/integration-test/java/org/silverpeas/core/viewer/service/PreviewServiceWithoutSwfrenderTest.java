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
 * FLOSS exception. You should have recieved a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/docs/core/legal/floss_exception.html"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.silverpeas.core.viewer.service;

import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.silverpeas.core.viewer.model.Preview;
import org.silverpeas.core.viewer.model.ViewerSettings;
import org.silverpeas.core.test.rule.MockByReflectionRule;
import org.silverpeas.core.util.ImageUtil;
import org.silverpeas.core.util.SettingBundle;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(Arquillian.class)
public class PreviewServiceWithoutSwfrenderTest extends AbstractViewerTest {

  @Rule
  public MockByReflectionRule reflectionRule = new MockByReflectionRule();

  @Inject
  private PreviewService previewService;

  @Before
  public void setup() throws Exception {
    FileUtils.deleteQuietly(getTemporaryPath());
    getTemporaryPath().mkdirs();
    final SettingBundle mockedSettings =
        reflectionRule.mockField(ViewerSettings.class, SettingBundle.class, "settings");
    when(mockedSettings.getInteger(eq("preview.width.max"), anyInt())).thenReturn(1000);
    when(mockedSettings.getInteger(eq("preview.height.max"), anyInt())).thenReturn(1000);
    when(mockedSettings.getBoolean(eq("viewer.cache.enabled"), anyBoolean())).thenReturn(true);
    when(mockedSettings.getBoolean(eq("viewer.cache.conversion.silent.enabled"), anyBoolean()))
        .thenReturn(false);
    when(mockedSettings.getBoolean(eq("viewer.conversion.strategy.split.enabled"), anyBoolean()))
        .thenReturn(false);
    reflectionRule.setField(SwfToolManager.class, false, "isSwfRenderActivated");
  }

  @After
  public void tearDown() throws Exception {
    FileUtils.deleteQuietly(getTemporaryPath());
  }

  @Test
  public void testOdtFile() throws Exception {
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.odt", getDocumentNamed("file.odt")));
    assertOfficeOrPdfDocumentPreview(preview);
  }

  @Test
  public void testOdtFileFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.odt")));
    assertOfficeOrPdfDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testDocFile() throws Exception {
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.doc", getDocumentNamed("file.doc")));
    assertOfficeOrPdfDocumentPreview(preview);
  }

  @Test
  public void testDocFileFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.doc")));
    assertOfficeOrPdfDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testDocxFile() throws Exception {
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.docx", getDocumentNamed("file.docx")));
    assertOfficeOrPdfDocumentPreview(preview);
  }

  @Test
  public void testDocxFileFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.docx")));
    assertOfficeOrPdfDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testDocxFileWithSpecialChars() throws Exception {
    final Preview preview = previewService
        .getPreview(new ViewerContext("file ' - '' .docx", getDocumentNamed("file ' - '' .docx")));
    assertOfficeOrPdfDocumentPreview(preview);
  }

  @Test
  public void testDocxFileWithSpecialCharsFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file ' - '' .docx")));
    assertOfficeOrPdfDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testOdpFile() throws Exception {
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.odp", getDocumentNamed("file.odp")));
    assertPptDocumentPreview(preview);
  }

  @Test
  public void testOdpFileFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.odp")));
    assertPptDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testPptFile() throws Exception {
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.ppt", getDocumentNamed("file.ppt")));
    assertPptDocumentPreview(preview);
  }

  @Test
  public void testPptFileFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.ppt")));
    assertPptDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testPptxFile() throws Exception {
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.pptx", getDocumentNamed("file.pptx")));
    assertPptDocumentPreview(preview);
  }

  @Test
  public void testPptxFileFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.pptx")));
    assertPptDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testOdsFile() throws Exception {
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.ods", getDocumentNamed("file.ods")));
    assertOfficeOrPdfDocumentPreview(preview);
  }

  @Test
  public void testOdsFileFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.ods")));
    assertOfficeOrPdfDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testXlsFile() throws Exception {
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.xls", getDocumentNamed("file.xls")));
    assertOfficeOrPdfDocumentPreview(preview);
  }

  @Test
  public void testXlsFileFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.xls")));
    assertOfficeOrPdfDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testXlsxFile() throws Exception {
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.xlsx", getDocumentNamed("file.xlsx")));
    assertThat(preview, notNullValue());
    assertThat(preview.getPhysicalFile().getName(), startsWith("file."));
//    The following assertions are comented out as the result size depends on the OpenOffice version
//    (OpenOffice.org or LibreOffice)
//    final String[] previewSize = ImageUtil.getWidthAndHeight(preview.getPhysicalFile());
//    assertThat(previewSize[0], is("595"));
//    assertThat(previewSize[1], is("842"));
  }

  @Test
  public void testXlsxFileFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.xlsx")));
    assertThat(preview, notNullValue());
    assertThat(preview.getPhysicalFile().getName(), startsWith("file."));
//    The following assertions are comented out as the result size depends on the OpenOffice version
//    (OpenOffice.org or LibreOffice)
//    final String[] previewSize = ImageUtil.getWidthAndHeight(preview.getPhysicalFile());
//    assertThat(previewSize[0], is("595"));
//    assertThat(previewSize[1], is("842"));
  }

  @Test
  public void testJpgFile() throws Exception {
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.jpg", getDocumentNamed("file.jpg")));
    assertImageDocumentPreview(preview);
  }

  @Test
  public void testJpgFileFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.jpg")));
    assertImageDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testJpgFileWithSpecialChars() throws Exception {
    final Preview preview = previewService
        .getPreview(new ViewerContext("file ' - '' .jpg", getDocumentNamed("file ' - '' .jpg")));
    assertImageDocumentPreview(preview);
  }

  @Test
  public void testJpgFileWithSpecialCharsFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file ' - '' .jpg")));
    assertImageDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testJpegFile() throws Exception {
    // The uppercase letter of the following file extension is not a mistake
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.jpEg", getDocumentNamed("file.jpEg")));
    assertImageDocumentPreview(preview);
  }

  @Test
  public void testJpegFileFromSimpleDocument() throws Exception {
    // The uppercase letter of the following file extension is not a mistake
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.jpEg")));
    assertImageDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testPdfFile() throws Exception {
    final Preview preview =
        previewService.getPreview(new ViewerContext("file.pdf", getDocumentNamed("file.pdf")));
    assertOfficeOrPdfDocumentPreview(preview);
  }

  @Test
  public void testPdfFileFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file.pdf")));
    assertOfficeOrPdfDocumentPreviewWithCacheManagement(preview);
  }

  @Test
  public void testPdfFileWithSpecialChars() throws Exception {
    final Preview preview = previewService
        .getPreview(new ViewerContext("file ' - '' .pdf", getDocumentNamed("file ' - '' .pdf")));
    assertOfficeOrPdfDocumentPreview(preview);
  }

  @Test
  public void testPdfFileWithSpecialCharsFromSimpleDocument() throws Exception {
    final Preview preview =
        previewService.getPreview(ViewerContext.from(getSimpleDocumentNamed("file ' - '' .pdf")));
    assertOfficeOrPdfDocumentPreviewWithCacheManagement(preview);
  }

  private void assertPptDocumentPreview(Preview preview) {
    assertDocumentPreview(preview, 720, 540, false, "png");
  }

  private void assertPptDocumentPreviewWithCacheManagement(Preview preview) {
    assertDocumentPreview(preview, 720, 540, true, "png");
  }

  private void assertImageDocumentPreview(Preview preview) {
    assertDocumentPreview(preview, 1000, 750, false, "jpg");
  }

  private void assertImageDocumentPreviewWithCacheManagement(Preview preview) {
    assertDocumentPreview(preview, 1000, 750, true, "jpg");
  }

  private void assertOfficeOrPdfDocumentPreview(Preview preview) {
    assertDocumentPreview(preview, 595, 842, false, "png");
  }

  private void assertOfficeOrPdfDocumentPreviewWithCacheManagement(Preview preview) {
    assertDocumentPreview(preview, 595, 842, true, "png");
  }

  private void assertDocumentPreview(Preview preview, int width, int height,
      final boolean cacheUsed, final String fileExtension) {
    assertThat(preview, notNullValue());
    int nbFilesAtTempRoot = cacheUsed ? 2 : 1;
    assertThat(getTemporaryPath().listFiles(), arrayWithSize(nbFilesAtTempRoot));
    assertThat(preview.getPhysicalFile().getParentFile().listFiles(), arrayWithSize(1));
    assertThat(preview.getPhysicalFile().getName(), is("file." + fileExtension));
    assertThat(preview.getWidth(), is(String.valueOf(width)));
    assertThat(preview.getHeight(), is(String.valueOf(height)));
    final String[] previewSize = ImageUtil.getWidthAndHeight(preview.getPhysicalFile());
    assertThat(previewSize[0], is(String.valueOf(width)));
    assertThat(previewSize[1], is(String.valueOf(height)));
  }
}
