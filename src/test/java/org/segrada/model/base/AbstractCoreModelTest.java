package org.segrada.model.base;

import org.junit.Test;
import org.segrada.model.*;
import org.segrada.model.prototype.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class AbstractCoreModelTest {

	@Test
	public void testEquals() throws Exception {
		MockEntity entity1 = new MockEntity();
		MockEntity entity2 = new MockEntity();

		assertEquals(entity1, entity2);

		entity1.setCreated(1L);
		entity2.setCreated(2L);
		entity1.setModified(1L);
		entity2.setModified(2L);
		entity1.setCreator(new User());
		entity2.setCreator(new User());
		entity1.setModifier(new User());
		entity2.setModifier(new User());
		entity1.setTags(new String[]{});
		entity2.setTags(new String[]{"Test"});

		List<IFile> files1 = new LinkedList<>();
		List<IFile> files2 = new LinkedList<>();
		files1.add(new File());
		List<IComment> comments1 = new LinkedList<>();
		List<IComment> comments2 = new LinkedList<>();
		comments1.add(new Comment());
		List<ISourceReference> sourceReferences1 = new LinkedList<>();
		List<ISourceReference> sourceReferences2 = new LinkedList<>();
		sourceReferences1.add(new SourceReference());

		entity1.setFiles(files1);
		entity2.setFiles(files2);
		entity1.setComments(comments1);
		entity2.setComments(comments2);
		entity1.setSourceReferences(sourceReferences1);
		entity2.setSourceReferences(sourceReferences2);

		List<ILocation> locations1 = new LinkedList<>();
		List<ILocation> locations2 = new LinkedList<>();
		locations1.add(new Location());
		List<IPeriod> periods1 = new LinkedList<>();
		List<IPeriod> periods2 = new LinkedList<>();
		periods1.add(new Period());

		entity1.setLocations(locations1);
		entity2.setLocations(locations2);
		entity1.setPeriods(periods1);
		entity2.setPeriods(periods2);

		assertEquals(entity1, entity2);

		// now add load - should not be equal!
		entity1.setDummyLoad("Test1");
		entity2.setDummyLoad("Test2");

		assertNotEquals(entity1, entity2);

		// test for null
		assertFalse(entity1.equals(null));
	}

	@Test
	public void testHashCode() throws Exception {
		MockEntity entity1 = new MockEntity();
		MockEntity entity2 = new MockEntity();

		assertEquals(entity1.hashCode(), entity2.hashCode());

		entity1.setCreated(1L);
		entity2.setCreated(2L);
		entity1.setModified(1L);
		entity2.setModified(2L);
		entity1.setCreator(new User());
		entity2.setCreator(new User());
		entity1.setModifier(new User());
		entity2.setModifier(new User());
		entity1.setTags(new String[]{});
		entity2.setTags(new String[] { "Test"});

		List<IFile> files1 = new LinkedList<>();
		List<IFile> files2 = new LinkedList<>();
		files1.add(new File());
		List<IComment> comments1 = new LinkedList<>();
		List<IComment> comments2 = new LinkedList<>();
		comments1.add(new Comment());
		List<ISourceReference> sourceReferences1 = new LinkedList<>();
		List<ISourceReference> sourceReferences2 = new LinkedList<>();
		sourceReferences1.add(new SourceReference());

		entity1.setFiles(files1);
		entity2.setFiles(files2);
		entity1.setComments(comments1);
		entity2.setComments(comments2);
		entity1.setSourceReferences(sourceReferences1);
		entity2.setSourceReferences(sourceReferences2);

		List<ILocation> locations1 = new LinkedList<>();
		List<ILocation> locations2 = new LinkedList<>();
		locations1.add(new Location());
		List<IPeriod> periods1 = new LinkedList<>();
		List<IPeriod> periods2 = new LinkedList<>();
		periods1.add(new Period());

		entity1.setLocations(locations1);
		entity2.setLocations(locations2);
		entity1.setPeriods(periods1);
		entity2.setPeriods(periods2);

		assertEquals(entity1.hashCode(), entity2.hashCode());

		// now add load - should not be equal!
		entity1.setDummyLoad("Test1");
		entity2.setDummyLoad("Test2");

		assertNotEquals(entity1.hashCode(), entity2.hashCode());
	}

	private class MockEntity extends AbstractCoreModel {
		/**
		 * load for testing equality
		 */
		private String dummyLoad = "DUMMY";

		@Override
		public String getTitle() {
			return dummyLoad;
		}

		public String getDummyLoad() {
			return dummyLoad;
		}

		public void setDummyLoad(String dummyLoad) {
			this.dummyLoad = dummyLoad;
		}
	}
}