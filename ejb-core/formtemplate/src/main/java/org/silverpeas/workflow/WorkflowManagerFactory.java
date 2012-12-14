package org.silverpeas.workflow;

import javax.inject.Inject;
import javax.inject.Named;

import org.silverpeas.workflow.control.WorkflowManager;

/**
 * 
 * @author ebonnet
 *
 */
public class WorkflowManagerFactory {
  private static final WorkflowManagerFactory instance = new WorkflowManagerFactory();

  @Inject
  @Named("simpleResourcesManager")
  private WorkflowManager workflowManager;
  
  public static final WorkflowManagerFactory getInstance() {
    return instance;
  }

  public static WorkflowManager getWorkflowManager() {
    return instance.workflowManager;
  }

  private WorkflowManagerFactory() {
  }
  
}
