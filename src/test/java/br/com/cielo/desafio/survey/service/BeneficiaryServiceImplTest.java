//package br.com.digio.accountbeneficiary.service;
//
//import static br.com.digio.accountbeneficiary.exception.ApplicationError.AB_DB_BENEFICIARY_NOT_FOUND;
//import static br.com.digio.accountbeneficiary.exception.ApplicationError.AB_RB_BENEFICIARY_ALREADY_EXISTS;
//import static br.com.digio.accountbeneficiary.exception.ApplicationError.AB_RB_DOCUMENT_IS_NOT_SAME_OWNERSHIP;
//import static br.com.digio.accountbeneficiary.exception.ApplicationError.AB_UB_BENEFICIARY_ALREADY_EXISTS;
//import static br.com.digio.accountbeneficiary.exception.ApplicationError.AB_UB_BENEFICIARY_NOT_FOUND;
//import static br.com.digio.accountbeneficiary.fixture.BeneficiariesResponseFixture.P2P_BENEFICIARIES_REPONSE;
//import static br.com.digio.accountbeneficiary.fixture.BeneficiaryFilterFixture.FILTER_OK;
//import static br.com.digio.accountbeneficiary.fixture.BeneficiaryFixture.P2P_BENEFICIARY;
//import static br.com.digio.accountbeneficiary.fixture.BeneficiaryRequestFixture.DIFFERENT_OWNERSHIP;
//import static br.com.digio.accountbeneficiary.fixture.BeneficiaryRequestFixture.DIFFERENT_OWNERSHIP_ERROR;
//import static br.com.digio.accountbeneficiary.fixture.BeneficiaryResponseFixture.DELETED_P2P_BENEFICIARY_REPONSE;
//import static br.com.digio.accountbeneficiary.fixture.BeneficiaryResponseFixture.P2P_BENEFICIARY_REPONSE;
//import static br.com.digio.accountbeneficiary.fixture.BeneficiaryResponseFixture.UPDATED_P2P_BENEFICIARY_REPONSE;
//import static br.com.digio.accountbeneficiary.fixture.UpdateBeneficiaryRequestFixture.P2P_BENEFICIARY_UPDATE_REQUEST;
//import static br.com.digio.accountbeneficiary.specification.BeneficiarySpecification.findDocument;
//import static br.com.digio.accountbeneficiary.specification.BeneficiarySpecification.findName;
//import static br.com.digio.accountbeneficiary.specification.BeneficiarySpecification.findOwnerCpf;
//import static br.com.digio.accountbeneficiary.specification.BeneficiarySpecification.findProductType;
//import static br.com.digio.accountbeneficiary.specification.BeneficiarySpecification.findRegisterType;
//import static br.com.digio.accountcommons.model.BeneficiaryRegisterType.P2P;
//import static br.com.digio.accountcommons.model.BeneficiaryStatus.ACTIVE;
//import static br.com.digio.accountcommons.model.PersonType.PF;
//import static br.com.six2six.fixturefactory.Fixture.from;
//import static java.util.Objects.requireNonNull;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.powermock.api.mockito.PowerMockito.whenNew;
//import static org.springframework.data.jpa.domain.Specification.where;
//
//import java.lang.reflect.Method;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.dao.IncorrectResultSizeDataAccessException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import br.com.digio.accountbeneficiary.BaseTest;
//import br.com.digio.accountbeneficiary.filter.BeneficiaryFilter;
//import br.com.digio.accountbeneficiary.model.Beneficiary;
//import br.com.digio.accountbeneficiary.repository.BeneficiaryRepository;
//import br.com.digio.accountbeneficiary.resource.request.BeneficiaryRequest;
//import br.com.digio.accountbeneficiary.resource.request.UpdateBeneficiaryRequest;
//import br.com.digio.accountbeneficiary.resource.response.BeneficiariesResponse;
//import br.com.digio.accountbeneficiary.resource.response.BeneficiaryResponse;
//import br.com.digio.accountcommons.exception.ServiceException;
//import br.com.digio.accountcommons.model.BeneficiaryRegisterType;
//import br.com.digio.accountcommons.model.ProductType;
//import br.com.digio.accountcommons.util.Numbers;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({
//	BeneficiaryServiceImpl.class,
//	Beneficiary.class
//})
//public class BeneficiaryServiceImplTest extends BaseTest {
//
//	private static final String UUID = "211464b2-0af5-4051-bd2b-c61bc61dc506-20190902085230163";
//	private static final String CPF = "75689003719";
//	private static final ProductType PRODUCT_TYPE = ProductType.DIGIO;
//
//	private Class<BeneficiaryServiceImpl> clazz = BeneficiaryServiceImpl.class;
//	
//	@InjectMocks
//	private BeneficiaryServiceImpl service;
//	
//	@Mock
//	private BeneficiaryRepository beneficiaryRepository;
//	
//	@Test
//	public void testClassAnnotations() {
//		assertTrue(clazz.isAnnotationPresent(Service.class));
//	}
//	
//	@Test
//	public void testSaveMethodAnnotations() throws Exception {
//		Method method = clazz.getDeclaredMethod("save", BeneficiaryRequest.class, BeneficiaryRegisterType.class);
//		Transactional annotation = method.getDeclaredAnnotation(Transactional.class);
//		assertEquals(Throwable.class, annotation.rollbackFor()[0]);
//	}
//	
//	@Test
//	public void testUpdateMethodAnnotations() throws Exception {
//		Method method = clazz.getDeclaredMethod("update", String.class, String.class, ProductType.class, UpdateBeneficiaryRequest.class, BeneficiaryRegisterType.class);
//		Transactional annotation = method.getDeclaredAnnotation(Transactional.class);
//		assertEquals(Throwable.class, annotation.rollbackFor()[0]);
//	}
//	
//	@Test
//	public void testDeleteMethodAnnotations() throws Exception {
//		Method method = clazz.getDeclaredMethod("delete", String.class, String.class, ProductType.class, BeneficiaryRegisterType.class);
//		Transactional annotation = method.getDeclaredAnnotation(Transactional.class);
//		assertEquals(Throwable.class, annotation.rollbackFor()[0]);
//	}
//	
//	@Test
//	public void testSaveWhenIsNotSameOwnership() {
//		BeneficiaryRequest request = from(BeneficiaryRequest.class).gimme(DIFFERENT_OWNERSHIP_ERROR);
//		
//		try {
//			service.save(request, P2P);
//		} catch (ServiceException e) {
//			assertEquals(AB_RB_DOCUMENT_IS_NOT_SAME_OWNERSHIP, e.getError());
//		}
//	}
//	
//	@Test
//	public void testSaveWhenBeneficiaryAlreadyExists() {
//		BeneficiaryRequest request = from(BeneficiaryRequest.class).gimme(DIFFERENT_OWNERSHIP);
//		Beneficiary beneficiary = from(Beneficiary.class).gimme(P2P_BENEFICIARY);
//		
//		when(beneficiaryRepository
//				.findBeneficiary(request.getOwnerCpf(), request.getOwnerProductType(), request.getDocument(), request.getBranch(), request.getAccountNumber(),
//						request.getBankCode(), PF, request.getTransferAccountType(), P2P, ACTIVE))
//				.thenReturn(Optional.of(beneficiary));
//		
//		try {
//			service.save(request, P2P);
//		} catch (ServiceException e) {
//			assertEquals(AB_RB_BENEFICIARY_ALREADY_EXISTS, e.getError());
//			
//			verify(beneficiaryRepository)
//				.findBeneficiary(request.getOwnerCpf(), request.getOwnerProductType(), request.getDocument(), request.getBranch(), request.getAccountNumber(),
//						request.getBankCode(), PF, request.getTransferAccountType(), P2P, ACTIVE);
//		}
//	}
//	
//	@Test
//	public void testSave() throws Exception {
//		BeneficiaryRequest request = from(BeneficiaryRequest.class).gimme(DIFFERENT_OWNERSHIP);
//		Beneficiary beneficiary = from(Beneficiary.class).gimme(P2P_BENEFICIARY);
//		BeneficiaryResponse expected = from(BeneficiaryResponse.class).gimme(P2P_BENEFICIARY_REPONSE);
//		
//		when(beneficiaryRepository
//				.findBeneficiary(request.getOwnerCpf(), request.getOwnerProductType(), request.getDocument(), request.getBranch(), request.getAccountNumber(),
//						request.getBankCode(), PF, request.getTransferAccountType(), P2P, ACTIVE))
//				.thenReturn(Optional.empty());
//		
//		when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);
//		whenNew(Beneficiary.class).withAnyArguments().thenReturn(beneficiary);
//		
//		
//		var result = service.save(request, P2P);
//		
//		assertEquals(expected, result);
//		
//		verify(beneficiaryRepository)
//			.findBeneficiary(request.getOwnerCpf(), request.getOwnerProductType(), request.getDocument(), request.getBranch(), request.getAccountNumber(),
//					request.getBankCode(), PF, request.getTransferAccountType(), P2P, ACTIVE);
//		
//		verify(beneficiaryRepository).save(beneficiary);
//	}
//	
//	@Test
//	public void testUpdateWhenBeneficiaryNotFound() {
//		UpdateBeneficiaryRequest request = from(UpdateBeneficiaryRequest.class).gimme(P2P_BENEFICIARY_UPDATE_REQUEST);
//		
//		when(beneficiaryRepository
//				.findByUuidAndOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatus(UUID, CPF, PRODUCT_TYPE, P2P, ACTIVE))
//				.thenReturn(Optional.empty());
//		
//		try {
//			service.update(UUID, CPF, PRODUCT_TYPE, request, P2P);
//		} catch (ServiceException e) {
//			assertEquals(AB_UB_BENEFICIARY_NOT_FOUND, e.getError());
//			
//			verify(beneficiaryRepository)
//				.findByUuidAndOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatus(UUID, CPF, PRODUCT_TYPE, P2P, ACTIVE);
//		}
//	}
//	
//	@Test
//	public void testUpdateWhenBeneficiaryAlreadyExists() {
//		UpdateBeneficiaryRequest request = from(UpdateBeneficiaryRequest.class).gimme(P2P_BENEFICIARY_UPDATE_REQUEST);
//		Beneficiary beneficiary = from(Beneficiary.class).gimme(P2P_BENEFICIARY);
//		
//		when(beneficiaryRepository
//				.findByUuidAndOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatus(UUID, CPF, PRODUCT_TYPE, P2P, ACTIVE))
//				.thenReturn(Optional.of(beneficiary));
//		
//		when(beneficiaryRepository
//				.findBeneficiary(CPF, PRODUCT_TYPE, beneficiary.getDocument(), beneficiary.getBranch(), 
//						beneficiary.getAccountNumber(), beneficiary.getBankCode(), beneficiary.getPersonType(), 
//						beneficiary.getTransferAccountType(), P2P, ACTIVE))
//				.thenThrow(new IncorrectResultSizeDataAccessException(Numbers.ONE));
//		
//		try {
//			service.update(UUID, CPF, PRODUCT_TYPE, request, P2P);
//		} catch (ServiceException e) {
//			assertEquals(AB_UB_BENEFICIARY_ALREADY_EXISTS, e.getError());
//			
//			verify(beneficiaryRepository)
//					.findByUuidAndOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatus(UUID, CPF, PRODUCT_TYPE, P2P, ACTIVE);
//			
//			verify(beneficiaryRepository)
//					.findBeneficiary(CPF, PRODUCT_TYPE, beneficiary.getDocument(), beneficiary.getBranch(), 
//							beneficiary.getAccountNumber(), beneficiary.getBankCode(), beneficiary.getPersonType(), 
//							beneficiary.getTransferAccountType(), P2P, ACTIVE);
//		}
//	}
//	
//	@Test
//	public void testUpdate() {
//		UpdateBeneficiaryRequest request = from(UpdateBeneficiaryRequest.class).gimme(P2P_BENEFICIARY_UPDATE_REQUEST);
//		Beneficiary beneficiary = from(Beneficiary.class).gimme(P2P_BENEFICIARY);
//		BeneficiaryResponse expected = from(BeneficiaryResponse.class).gimme(UPDATED_P2P_BENEFICIARY_REPONSE);
//		
//		when(beneficiaryRepository
//				.findByUuidAndOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatus(UUID, CPF, PRODUCT_TYPE, P2P, ACTIVE))
//		.thenReturn(Optional.of(beneficiary));
//		
//		when(beneficiaryRepository
//				.findBeneficiary(CPF, PRODUCT_TYPE, beneficiary.getDocument(), beneficiary.getBranch(), 
//						beneficiary.getAccountNumber(), beneficiary.getBankCode(), beneficiary.getPersonType(), 
//						beneficiary.getTransferAccountType(), P2P, ACTIVE))
//				.thenReturn(Optional.of(beneficiary));
//		
//			BeneficiaryResponse result = service.update(UUID, CPF, PRODUCT_TYPE, request, P2P);
//
//			assertEquals(expected, result);
//			
//			verify(beneficiaryRepository)
//					.findByUuidAndOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatus(UUID, CPF, PRODUCT_TYPE, P2P, ACTIVE);
//			
//			verify(beneficiaryRepository)
//					.findBeneficiary(CPF, PRODUCT_TYPE, beneficiary.getDocument(), beneficiary.getBranch(), 
//							beneficiary.getAccountNumber(), beneficiary.getBankCode(), beneficiary.getPersonType(), 
//							beneficiary.getTransferAccountType(), P2P, ACTIVE);
//	}
//	
//	@Test
//	public void testDeleteWhenBeneficiaryNotFound() {
//		when(beneficiaryRepository
//				.findByUuidAndOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatus(UUID, CPF, PRODUCT_TYPE, P2P, ACTIVE))
//				.thenReturn(Optional.empty());
//		
//		try {
//			service.delete(UUID, CPF, PRODUCT_TYPE, P2P);
//		} catch (ServiceException e) {
//			assertEquals(AB_DB_BENEFICIARY_NOT_FOUND, e.getError());
//			
//			verify(beneficiaryRepository)
//					.findByUuidAndOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatus(UUID, CPF, PRODUCT_TYPE, P2P, ACTIVE);
//		}
//	}
//	
//	@Test
//	public void testDelete() {
//		Beneficiary beneficiary = from(Beneficiary.class).gimme(P2P_BENEFICIARY);
//		BeneficiaryResponse expected = from(BeneficiaryResponse.class).gimme(DELETED_P2P_BENEFICIARY_REPONSE);
//		
//		when(beneficiaryRepository
//				.findByUuidAndOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatus(UUID, CPF, PRODUCT_TYPE, P2P, ACTIVE))
//				.thenReturn(Optional.of(beneficiary));
//		
//		BeneficiaryResponse result = service.delete(UUID, CPF, PRODUCT_TYPE, P2P);
//		
//		assertEquals(expected, result);
//		
//		verify(beneficiaryRepository)
//				.findByUuidAndOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatus(UUID, CPF, PRODUCT_TYPE, P2P, ACTIVE);
//	}
//	
//	@Test
//	public void testGetAll() {
//		List<Beneficiary> beneficiaries = from(Beneficiary.class).gimme(1, P2P_BENEFICIARY);
//		BeneficiariesResponse expected = from(BeneficiariesResponse.class).gimme(P2P_BENEFICIARIES_REPONSE);
//
//		when(beneficiaryRepository
//				.findByOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatusOrderByName(CPF, PRODUCT_TYPE, P2P, ACTIVE))
//				.thenReturn(beneficiaries);
//
//		BeneficiariesResponse result = service.getAll(CPF, PRODUCT_TYPE, P2P);
//		
//		assertEquals(expected, result);
//		
//		verify(beneficiaryRepository)
//				.findByOwnerCpfAndOwnerProductTypeAndRegisterTypeAndStatusOrderByName(CPF, PRODUCT_TYPE, P2P, ACTIVE);
//	}
//
//	@Test
//	public void testGetListBeneficiary() {
//		List<Beneficiary> beneficiaries = from(Beneficiary.class).gimme(1, P2P_BENEFICIARY);
//		BeneficiaryFilter filter = from(BeneficiaryFilter.class).gimme(FILTER_OK);
//		when(beneficiaryRepository
//				.findAll(where(requireNonNull(findOwnerCpf(filter.getOwnerCpf())))
//						.and(findProductType(filter.getOwnerProductType()))
//						.or(findDocument(filter.getDocument()))
//						.or(findName(filter.getName()))
//						.or(findRegisterType(filter.getType()))))
//				.thenReturn(beneficiaries);
//		var ok = service.getListBeneficiary(filter);
//		assertNotNull(ok);
//	}
//}