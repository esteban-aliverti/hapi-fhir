package ca.uhn.fhir.jpa.provider;

/*
 * #%L
 * HAPI FHIR JPA Server
 * %%
 * Copyright (C) 2014 - 2015 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.servlet.http.HttpServletRequest;

import ca.uhn.fhir.jpa.dao.IFhirResourceDao;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.annotation.ConditionalOperationParam;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.Delete;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;

public class JpaResourceProviderDstu2<T extends IResource> extends BaseJpaResourceProvider<T> {

	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(JpaResourceProviderDstu2.class);

	public JpaResourceProviderDstu2() {
		// nothing
	}

	public JpaResourceProviderDstu2(IFhirResourceDao<T> theDao) {
		super(theDao);
	}

	@Create
	public MethodOutcome create(HttpServletRequest theRequest, @ResourceParam T theResource, @ConditionalOperationParam String theConditional) {
		startRequest(theRequest);
		try {
			if (theConditional != null) {
				return getDao().create(theResource, theConditional);
			} else {
				return getDao().create(theResource);
			}
		} finally {
			endRequest(theRequest);
		}
	}

	@Delete
	public MethodOutcome delete(HttpServletRequest theRequest, @IdParam IdDt theResource, @ConditionalOperationParam String theConditional) {
		startRequest(theRequest);
		try {
			if (theConditional != null) {
				return getDao().deleteByUrl(theConditional);
			} else {
				return getDao().delete(theResource);
			}
		} finally {
			endRequest(theRequest);
		}
	}

	@Update
	public MethodOutcome update(HttpServletRequest theRequest, @ResourceParam T theResource, @IdParam IdDt theId, @ConditionalOperationParam String theConditional) {
		startRequest(theRequest);
		try {
			if (theConditional != null) {
				return getDao().update(theResource, theConditional);
			} else {
				theResource.setId(theId);
				return getDao().update(theResource);
			}
		} catch (ResourceNotFoundException e) {
			ourLog.info("Can't update resource with ID[" + theId.getValue() + "] because it doesn't exist, going to create it instead");
			theResource.setId(theId);
			return getDao().create(theResource);
		} finally {
			endRequest(theRequest);
		}
	}

}
