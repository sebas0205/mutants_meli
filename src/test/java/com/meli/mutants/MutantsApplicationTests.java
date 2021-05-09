package com.meli.mutants;


import com.meli.mutants.controller.MutantController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MutantController.class)
public class MutantsApplicationTests {

	@Autowired
	private MockMvc mock;

	@MockBean
	private MutantController mutantController;


	@Test
	public void testDNAInvalidByStructure() throws Exception {
		String request = "{ "+
				"dna:[GGGGGT," +
				"     CAGC," +
				"     TTAT," +
				"     AGAAGG," +
				"     CTCCTA," +
				"     TCAC]" +
				"}";



		ResultActions result = mock.perform(MockMvcRequestBuilders.post("/mutant").
				contentType(MediaType.APPLICATION_JSON).

				content(request.getBytes())).
				andExpect(status().isForbidden());

		System.out.println(result); ;


	}

}
