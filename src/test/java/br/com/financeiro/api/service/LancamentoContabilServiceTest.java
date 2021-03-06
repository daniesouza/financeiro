package br.com.financeiro.api.service;

import br.com.financeiro.FinanceiroApplication;
import br.com.financeiro.dao.LancamentoContabilRepository;
import br.com.financeiro.model.LancamentoContabil;
import br.com.financeiro.service.LancamentoContabilService;
import br.com.financeiro.service.impl.LancamentoContabilServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class )
@SpringBootTest(classes = FinanceiroApplication.class)
public class LancamentoContabilServiceTest {


	@Mock
	private LancamentoContabilServiceImpl lancamentoContabilService;

	@Before
	public void setUpMock() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(lancamentoContabilService.save(any(LancamentoContabil.class)))
				.thenReturn(new LancamentoContabil(1234l,BigDecimal.TEN,new Date()));
	}


	@Test
	public void lancamentoContabilServiceTest() {
		Assert.assertNotNull(lancamentoContabilService.save(new LancamentoContabil()));
	}

}
