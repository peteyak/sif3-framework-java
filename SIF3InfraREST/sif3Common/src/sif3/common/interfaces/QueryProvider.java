/*
 * QueryProvider.java
 * Created: 05/01/2015
 *
 * Copyright 2015 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package sif3.common.interfaces;

import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

/**
 * This interface is an extension to the Provider interface to also allow object queries.
 *
 * @author Ben Carter
 */
public interface QueryProvider extends Provider
{

	/**
	 * This method is used to retrieve any number of objects, filtered by the service path. Each predicate within the 
	 * criteria holds a parent object and it's key. This method uses 'paging' in the same way as Provider.retrieve.
	 * The number of records to be returned are determined by the query criteria as well as information within the
	 * paging info parameter. If the data set to be returned is considered too large by the provider (implementation 
	 * dependent) then a DataTooLargeException must be raised. This exception is then translated into an appropriate
	 * HTTP Status within the framework to meet the SIF Specification.
	 * 
	 * @param queryCriteria The criteria to use when filtering results
	 * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
	 * @param context The Context for which the objects shall be returned. Can be Null (default Zone)
	 * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED!).
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
	 * 
	 * @return Object Plural Type containing list of objects
	 * 
	 * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is 
	 *                              performed and the  message of the exceptions holds some info.
	 * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
	 * @throws DataTooLargeException If the data that shall be returned is too large due to the query criteria or
	 *                               values in the paging info.
	 */
	public Object retrieveByServicePath(QueryCriteria queryCriteria, 
			                            SIFZone zone, 
			                            SIFContext context, 
			                            PagingInfo pagingInfo, 
			                            RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException;
}
