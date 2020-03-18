package br.com.cielo.desafio.survey;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.BeforeClass;
import org.powermock.core.classloader.annotations.PowerMockIgnore;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*"})
public class BaseTest {

	@Before
	public void init() {
		initMocks(this);
	}
	
	@BeforeClass
	public static void setUp() {
		loadTemplates("br.com.digio.accountbeneficiary.fixture");
	}
}
