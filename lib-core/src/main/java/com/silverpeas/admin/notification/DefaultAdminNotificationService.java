/*
 * Copyright (C) 2000 - 2011 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection withWriter Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have recieved a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/legal/licensing"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.silverpeas.admin.notification;

import static com.silverpeas.notification.NotificationTopic.onTopic;
import static com.silverpeas.notification.RegisteredTopics.ADMIN_TOPIC;

import javax.inject.Inject;
import javax.inject.Named;

import com.silverpeas.notification.NotificationPublisher;

/**
 * A service to notify about events on admin.
 * It provides an easy access to the underlying messaging system used in the notification.
 */
@Named("adminNotificationService")
public class DefaultAdminNotificationService implements AdminNotificationService {

  @Inject
  private NotificationPublisher publisher;

  /* (non-Javadoc)
   * @see com.silverpeas.admin.notification.AdminNotificationService#notifyOnDeletionOf(java.lang.String, java.lang.String)
   */
  @Override
  public void notifyOnDeletionOf(final String spaceId, String userId) {
      SpaceLogicalDeletionNotification deletion = new SpaceLogicalDeletionNotification(spaceId, userId);
      publisher.publish(deletion, onTopic(ADMIN_TOPIC.getTopicName()));
  }

}