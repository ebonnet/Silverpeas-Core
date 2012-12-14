package org.silverpeas.workflow.control;

import java.util.List;

import org.silverpeas.workflow.model.ProcessInstanceMdl;
import org.springframework.transaction.annotation.Transactional;

import com.silverpeas.annotation.Service;

@Service
@Transactional
public class SimpleWorkflowManager implements WorkflowManager {

  @Override
  public List<ProcessInstanceMdl> getListProcessModel() {
    // TODO Auto-generated method stub
    return null;
  }

  
  
}
