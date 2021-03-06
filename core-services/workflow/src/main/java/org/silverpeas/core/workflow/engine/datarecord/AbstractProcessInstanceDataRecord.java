/*
 * Copyright (C) 2000 - 2014 Silverpeas
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

package org.silverpeas.core.workflow.engine.datarecord;

import java.util.HashMap;
import java.util.Map;

import org.silverpeas.core.contribution.content.form.DataRecord;
import org.silverpeas.core.contribution.content.form.Field;
import org.silverpeas.core.contribution.content.form.FormException;

public class AbstractProcessInstanceDataRecord implements DataRecord {

  private static final long serialVersionUID = 1L;

  @Override
  public String getId() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * The id of an instance is immutable.
   */
  @Override
  public void setId(String externalId) {
    // do nothing
  }

  /**
   * An instance is always registred.
   */
  @Override
  public boolean isNew() {
    return true;
  }

  @Override
  public Field getField(String fieldName) throws FormException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Field getField(String fieldName, int occurrence) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Field getField(int fieldIndex) throws FormException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String[] getFieldNames() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getLanguage() {
    return null;
  }

  @Override
  public void setLanguage(String lang) {
    // do nothing
  }

  @Override
  public Map<String, String> getValues(String language) {
    // no implemented yet !
    return new HashMap<>();
  }

}
