package org.segrada.service.repository.orientdb.base;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.segrada.model.base.AbstractAnnotatedModel;
import org.segrada.model.prototype.SegradaAnnotatedEntity;
import org.segrada.service.repository.orientdb.factory.OrientDbRepositoryFactory;
import org.segrada.session.Identity;
import org.segrada.test.OrientDBTestInstance;
import org.segrada.test.OrientDbTestApplicationSettings;

import static org.junit.Assert.*;

public class AbstractAnnotatedOrientDbRepositoryTest {
	/**
	 * reference to test instance of orientdb in memory
	 */
	private OrientDBTestInstance orientDBTestInstance = new OrientDBTestInstance();

	/**
	 * mock repository - see below
	 */
	private MockOrientDbRepository mockOrientDbRepository;

	@Before
	public void setUp() throws Exception {
		// set up schema if needed
		orientDBTestInstance.setUpSchemaIfNeeded();

		// open database
		ODatabaseDocumentTx db = orientDBTestInstance.getDatabase();

		// create schema
		db.command(new OCommandSQL("create class Mock extends V")).execute();

		OrientDbRepositoryFactory factory = new OrientDbRepositoryFactory(db, new OrientDbTestApplicationSettings(), new Identity());

		// create repo
		mockOrientDbRepository = new MockOrientDbRepository(factory);
	}

	@After
	public void tearDown() throws Exception {
		// close db
		try {
			mockOrientDbRepository.db.close();
		} catch (Exception e) {
			// do nothing
		}

		// open database
		ODatabaseDocumentTx db = orientDBTestInstance.getDatabase();

		// remove schema
		db.command(new OCommandSQL("delete vertex V")).execute();
		db.command(new OCommandSQL("delete edge E")).execute();
		db.command(new OCommandSQL("truncate class Pictogram")).execute();
		db.command(new OCommandSQL("drop class Mock")).execute();

		// close db
		db.close();
	}

	@Test
	public void testPopulateODocumentWithAnnotated() throws Exception {
		fail();

	}

	@Test
	public void testPopulateEntityWithAnnotated() throws Exception {
		fail();

	}

	@Test
	public void testLazyLoadSourceReferences() throws Exception {
		fail();

	}

	@Test
	public void testLazyLoadComments() throws Exception {
		fail();

	}

	@Test
	public void testLazyLoadFiles() throws Exception {
		fail();

	}

	@Test
	public void testProcessAfterSaving() throws Exception {
		fail();

	}

	@Test
	public void testUpdateEntityTags() throws Exception {
		fail();

	}

	@Test
	public void testDelete() throws Exception {
		fail("TODO: check, if needed!");

	}

	/**
	 * Mock entity
	 */
	private class MockEntity extends AbstractAnnotatedModel implements SegradaAnnotatedEntity {
		private String id;

		@Override
		public void setId(String id) {
			this.id = id;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String getTitle() {
			return "DUMMY";
		}
	}

	/**
	 * Partial/mock repository to test methods
	 */
	private class MockOrientDbRepository extends AbstractAnnotatedOrientDbRepository<MockEntity> {
		public MockOrientDbRepository(OrientDbRepositoryFactory repositoryFactory) {
			super(repositoryFactory);
		}

		@Override
		public MockEntity convertToEntity(ODocument document) {
			MockEntity entity = new MockEntity();
			populateEntityWithBaseData(document, entity);
			return entity;
		}

		@Override
		public ODocument convertToDocument(MockEntity entity) {
			return createOrLoadDocument(entity);
		}

		@Override
		public String getModelClassName() {
			return "Mock";
		}
	}
}