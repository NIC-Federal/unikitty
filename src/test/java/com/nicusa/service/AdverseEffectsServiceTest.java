package com.nicusa.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import com.nicusa.domain.AdverseEffectDescription;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;


public class AdverseEffectsServiceTest
{
    AdverseEffectService service = null;

    @Before
    public void setup () throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        EntityManager em = mock(EntityManager.class);
        Query q = mock(Query.class);
        when(em.createQuery(any(String.class))).thenReturn(q);
        when(q.getResultList()).thenReturn(new ArrayList<AdverseEffectDescription>());
        service = new AdverseEffectService();
        service.entityManager = em;
        service.documentBuilder = builder;
    }

    @Test
    public void testAdverseEffects () throws Exception
    {
        String description = service.findEffectDescription("RENAL");
        
        assertEquals("relating to, involving, affecting, or located in the region of the kidneys :nephric  renal function", description);
    }

    String adverseEventXml1 =
            "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
            "<entry_list version=\"1.0\">" +
            "<entry id=\"renal\"><hw>re&#xB7;nal</hw><pr>ˈrēn-ᵊl</pr><sound><wav>renal001.wav</wav><wpr>!rE-n<up>u</up>l</wpr></sound><fl>adjective</fl><def><sensb><sens><dt>relating to, involving, affecting, or located in the region of the kidneys :<sx>nephric</sx>  <vi><it>renal</it> function</vi></dt></sens></sensb></def></entry>" +
            "</entry_list>";

}
