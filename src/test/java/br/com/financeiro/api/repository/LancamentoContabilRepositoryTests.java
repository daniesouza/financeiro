package br.com.financeiro.api.repository;

import br.com.financeiro.FinanceiroApplication;
import br.com.financeiro.dao.LancamentoContabilRepository;
import br.com.financeiro.model.LancamentoContabil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinanceiroApplication.class)
public class LancamentoContabilRepositoryTests {


	@Autowired
	private LancamentoContabilRepository lancamentoContabilRepository;

	@Before
	public void setup() {

		LancamentoContabil lancamentoContabil = new LancamentoContabil();
		lancamentoContabil.setId("637e8d6c-435f-4759-ae4a-a87d945e85c6");
		lancamentoContabil.setValor(BigDecimal.TEN);
		lancamentoContabil.setData(new Date());
		lancamentoContabil.setContaContabil(12345l);

		lancamentoContabilRepository.save(lancamentoContabil);
	}

	@Test
	public void shouldNotReturnLancamentoContabil() {


		Optional<LancamentoContabil> lancamentoContabil = lancamentoContabilRepository.findById("22323");

		Assert.isTrue( !lancamentoContabil.isPresent(), "Should not return lancamentoContabil!");
	}

	@Test
	public void shouldReturnLancamentoContabil() {


		Optional<LancamentoContabil> lancamentoContabil = lancamentoContabilRepository.findById("637e8d6c-435f-4759-ae4a-a87d945e85c6");

		Assert.isTrue( lancamentoContabil.isPresent(), "Should return lancamentoContabil!");
	}
}
