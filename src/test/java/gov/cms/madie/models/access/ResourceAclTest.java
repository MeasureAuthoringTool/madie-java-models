package gov.cms.madie.models.access;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import gov.cms.madie.models.measure.Measure;

class ResourceAclTest {

  @Test
  void testEmptyAclSpecification() {
    Measure measure = new Measure();
    List<AclSpecification> aclCollection = new ArrayList<>();
    measure.setAcls(aclCollection);
    assertEquals(measure.getAcls().size(), 0);

  }

  @Test
  void testAclWithOneUserRole() {
    Measure measure = new Measure();
    List<AclSpecification> aclCollection = new ArrayList<>();
    AclSpecification aclSpecification = new AclSpecification();
    aclCollection.add(aclSpecification);
    measure.setAcls(aclCollection);
    assertEquals(measure.getAcls().size(), 1);
  }

  @SuppressWarnings("serial")
  @Test
  void testAclWithOneValidUserRole() {
    Measure measure = new Measure() ; 
    List<AclSpecification> aclCollection = new ArrayList<>() ; 
    AclSpecification aclSpecification = new AclSpecification() ; 
    aclSpecification.setUserId("akinsgre");
    
    
    aclSpecification.setRoles( new ArrayList<RoleEnum>() {{
         add(RoleEnum.SHARED_WITH);
    }} );

    aclCollection.add(aclSpecification);
    
    measure.setAcls(aclCollection) ; 
    assertEquals(measure.getAcls().size(), 1);
    AclSpecification result = measure.getAcls().get(0);
    assertNotNull(result);
    assertEquals("akinsgre", result.getUserId());
    assertEquals(1, result.getRoles().size());
    assertEquals(RoleEnum.SHARED_WITH, result.getRoles().get(0));
  }
}
