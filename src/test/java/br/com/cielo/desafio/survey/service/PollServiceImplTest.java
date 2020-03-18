package br.com.cielo.desafio.survey.service;

import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.cielo.desafio.survey.BaseTest;
import br.com.cielo.desafio.survey.fixture.PollFixture;
import br.com.cielo.desafio.survey.fixture.PollRequestFixture;
import br.com.cielo.desafio.survey.fixture.PollResponseFixture;
import br.com.cielo.desafio.survey.model.Poll;
import br.com.cielo.desafio.survey.repository.PollRepository;
import br.com.cielo.desafio.survey.resource.request.PollRequest;
import br.com.cielo.desafio.survey.resource.response.PollResponse;

@RunWith(PowerMockRunner.class)
//@PrepareForTest({
//		PollServiceImpl.class,
//		Poll.class
//})

class PollServiceImplTest  extends BaseTest{

	private Class<PollServiceImpl> clazz = PollServiceImpl.class;
	//private static final Class<PollServiceImpl> CLAZZ = PollServiceImpl.class;

	@InjectMocks
	private PollServiceImpl service;

	
	private PollRepository pollRepository;

	@Mock
	private PersonService personService;

	@Mock
	private OptionService optionService;

	private PollRequest pollRequest;

	private Poll poll;

	private PollResponse pollResponse;

	@Test
	public void testClassAnnotations() {
		assertTrue(clazz.isAnnotationPresent(Service.class));
	}

	@Before
	public void setup() {
		//pollRepository = Mockito.mock(PollRepository.class);
		//MockitoAnnotations.initMocks(this);
		pollRequest = from(PollRequest.class).gimme(PollRequestFixture.POLL_REQUEST_OK);
		poll = from(Poll.class).gimme(PollFixture.POLL_OK);
		pollResponse = from(PollResponse.class).gimme(PollResponseFixture.POLL_RESPONSE_OK);
	}

	@Test
	void testCreatePoll() {
		
		when(pollRepository.findByTitle(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
		when(pollRepository.save(ArgumentMatchers.any())).thenReturn(poll);
		var poll = service.createPoll(pollRequest);
		verify(pollRepository).findByTitle(ArgumentMatchers.anyString());
		verify(pollRepository).save(ArgumentMatchers.any());
		assertNotNull(poll);
	}

}
