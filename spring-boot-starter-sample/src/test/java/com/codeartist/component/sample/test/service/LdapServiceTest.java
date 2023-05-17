//package com.codeartist.component.sample.test.service;
//
//import com.codeartist.component.sample.repository.OrganizationRepository;
//import com.codeartist.component.sample.test.AbstractSpringRunnerTests;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.naming.InvalidNameException;
//import javax.naming.ldap.LdapName;
//
///**
// * @author 艾江南
// * @date 2022/8/24
// */
//public class LdapServiceTest extends AbstractSpringRunnerTests {
//
//    @Autowired
//    private OrganizationRepository organizationRepository;
//
//    @Test
//    public void query() throws InvalidNameException {
//        LdapName id = new LdapName("ou=employees");
//        organizationRepository.findById(id).ifPresent(organization -> {
//            System.out.println(organization);
//            organization.setDescription("员工列表");
//            organizationRepository.save(organization);
//        });
//    }
//}
