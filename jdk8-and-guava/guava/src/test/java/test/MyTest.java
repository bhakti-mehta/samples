package test;
 
import org.junit.Assert;
import org.junit.Test;
 
public class MyTest {
 
	@Test
	public void testAgeOfPeople() {
        PersonSample ps = new PersonSample();
        //System.out.println(ps.getPredicate().size());
		Assert.assertEquals(ps.getPredicate().size(), 2);
 
	}

    @Test
    public void testAgeOfPeopleAndName() {
        PersonSample ps = new PersonSample();
        //System.out.println(ps.getMultiplePredicates().size());
        Assert.assertEquals(ps.getMultiplePredicates().size(), 1);

    }

    @Test
    public void testOptional() {
        PersonSample ps = new PersonSample();
        //System.out.println(ps.getSuffix().toArray()[0]);
        Assert.assertEquals(ps.getSuffix().size(), 2);

    }

    @Test
      public void testSupplier() {
        PersonSample ps = new PersonSample();
        System.out.println(ps.getSupplier());
        Assert.assertEquals(ps.getSupplier(), 53);

    }


    @Test
    public void testJoiner() {
        PersonSample ps = new PersonSample();
        System.out.println(ps.getJoiner());
        Assert.assertEquals(ps.getJoiner(), "Violet; Indigo; Blue; Green; Yellow; Orange; Red"
        );

    }
}
