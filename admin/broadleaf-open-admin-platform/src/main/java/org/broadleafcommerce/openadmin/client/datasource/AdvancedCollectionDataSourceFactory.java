/*
 * Copyright 2008-2009 the original author or authors.
 *
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
 */

package org.broadleafcommerce.openadmin.client.datasource;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import org.broadleafcommerce.common.presentation.client.PersistencePerspectiveItemType;
import org.broadleafcommerce.openadmin.client.datasource.dynamic.ListGridDataSource;
import org.broadleafcommerce.openadmin.client.datasource.dynamic.module.BasicClientEntityModule;
import org.broadleafcommerce.openadmin.client.datasource.dynamic.module.DataSourceModule;
import org.broadleafcommerce.openadmin.client.dto.CollectionMetadata;
import org.broadleafcommerce.openadmin.client.dto.OperationTypes;
import org.broadleafcommerce.openadmin.client.dto.PersistencePerspective;
import org.broadleafcommerce.openadmin.client.service.AppServices;

import java.util.ArrayList;
import java.util.List;

/**
 * This factory is responsible for generating a datasource from CollectionMetadata.
 * The config information is derived from @AdminPresentationCollection, @AdminPresentationAdornedTargetCollection,
 * and @AdminPresentationMap annotations that inform collections and maps in the
 * JPA domain.
 *
 * @author Jeff Fischer
 */
public class AdvancedCollectionDataSourceFactory implements DataSourceFactory {

    private CollectionMetadata collectionMetadata;

    public AdvancedCollectionDataSourceFactory(CollectionMetadata collectionMetadata) {
        this.collectionMetadata = collectionMetadata;
    }

    @Override
    public void createDataSource(String name, OperationTypes operationTypes, Object[] additionalItems, AsyncCallback<DataSource> cb) {
        PersistencePerspective persistencePerspective = collectionMetadata.getPersistencePerspective();
        List<DataSourceModule> dataSourceModuleList = new ArrayList<DataSourceModule>();
        if (persistencePerspective.getPersistencePerspectiveItems().containsKey(PersistencePerspectiveItemType.ADORNEDTARGETLIST)) {
            //TODO add a adorned target module
        }
        if (persistencePerspective.getPersistencePerspectiveItems().containsKey(PersistencePerspectiveItemType.MAPSTRUCTURE)) {
            //TODO add a map module
        }
        //The BasicClientEntityModule is always added, regardless
        dataSourceModuleList.add(new BasicClientEntityModule(collectionMetadata.getCollectionCeilingEntity(), persistencePerspective, AppServices.DYNAMIC_ENTITY));
        DataSourceModule[] modules = new DataSourceModule[dataSourceModuleList.size()];
        modules = dataSourceModuleList.toArray(modules);

        ListGridDataSource dataSource = new ListGridDataSource(name, persistencePerspective, AppServices.DYNAMIC_ENTITY, modules);
        dataSource.buildFields(null, false, cb);
    }
}