package com.silverpeas.notification.delayed.repository;

import com.silverpeas.notification.delayed.model.DelayedNotificationUserSetting;
import org.silverpeas.persistence.model.identifier.UniqueIntegerIdentifier;
import org.silverpeas.persistence.repository.jpa.JpaBasicEntityManager;

import java.util.List;

/**
 * @author: ebonnet
 */
public class DelayedNotificationUserSettingJpaManager
    extends JpaBasicEntityManager<DelayedNotificationUserSetting, UniqueIntegerIdentifier>
    implements DelayedNotificationUserSettingManager {

  @Override
  public List<DelayedNotificationUserSetting> findByUserId(final int userId) {
    return listFromNamedQuery("DelayedNotificationUserSetting.findByUserId",
        newNamedParameters().add("userId", userId));
  }

  @Override
  public List<DelayedNotificationUserSetting> findByUserIdAndChannel(final int userId, final int channel) {
    return listFromNamedQuery("DelayedNotificationUserSetting.findByUserIdAndChannel",
        newNamedParameters().add("userId", userId).add("channel", channel));
  }

  public void deleteById(String id) {
    delete(getById(id));
  }
}