package test;
 
import org.junit.Assert;
import org.junit.Test;
 
public class MyTest {
 
	@Test
	public void testAgeOfPersons() {
        PersonSample ps = new PersonSample();
        System.out.println(ps.getPredicate().count());
		Assert.assertEquals(ps.getPredicate().count(), 2);
 
	}

    @Test
    public void testAgeAndLastName() {
        PersonSample ps = new PersonSample();
        System.out.println(ps.getMultiplePredicates().toArray()[0]);
      //  System.out.println(ps.getMultiplePredicates().toArray()[1]);
        Assert.assertEquals(ps.getMultiplePredicates().count(), 1);

    }

    @Test
    public void testOptional() {
        PersonSample ps = new PersonSample();
        System.out.println(ps.getSuffix().toArray()[0]);
        //  System.out.println(ps.getMultiplePredicates().toArray()[1]);
        Assert.assertEquals(ps.getSuffix().count(), 2);

    }

    @Test
    public void testSupplier() {
        PersonSample ps = new PersonSample();
        System.out.println(ps.getSupplier());
        //  System.out.println(ps.getMultiplePredicates().toArray()[1]);
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

