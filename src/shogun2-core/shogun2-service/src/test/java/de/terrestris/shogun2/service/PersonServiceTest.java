package de.terrestris.shogun2.service;

import de.terrestris.shogun2.model.Application;
import de.terrestris.shogun2.model.Person;

public class PersonServiceTest extends AbstractExtDirectCrudServiceTest<Person> {

	/**
	 * 
	 * @throws Exception
	 */
	public void setUpImplToTest() throws Exception {
		implToTest = new Application();
	}

	/**
	 * 
	 */
	@Override
	protected AbstractExtDirectCrudService<Person> getCrudService() {
		return new PersonService();
	}

}