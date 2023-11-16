package com.magicqoven;/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


// [START bigquery_create_table]
import com.google.auth.Credentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Field;
import com.google.cloud.bigquery.Schema;
import com.google.cloud.bigquery.StandardSQLTypeName;
import com.google.cloud.bigquery.StandardTableDefinition;
import com.google.cloud.bigquery.TableDefinition;
import com.google.cloud.bigquery.TableId;
import com.google.cloud.bigquery.TableInfo;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;

public class App {

    public static void main(String[] args) {
        // TODO(developer): Replace these variables before running the sample.
        String datasetName = "MY_DATASET_NAME";
        String tableName = "MY_TABLE_NAME";
        Schema schema =
                Schema.of(
                        Field.of("stringField", StandardSQLTypeName.STRING),
                        Field.of("booleanField", StandardSQLTypeName.BOOL));
        createTable(datasetName, tableName, schema);
    }

    public static void createTable(String datasetName, String tableName, Schema schema) {
        try {
            // Initialize client that will be used to send requests. This client only needs to be created
            // once, and can be reused for multiple requests.
            BigQuery bigquery = BigQueryOptions.newBuilder().setProjectId("magicqoven").setCredentials("${spring.cloud.gcp.bigquery.datasetName}").getService();

            TableId tableId = TableId.of(datasetName, tableName);
            TableDefinition tableDefinition = StandardTableDefinition.of(schema);
            TableInfo tableInfo = TableInfo.newBuilder(tableId, tableDefinition).build();

            bigquery.create(tableInfo);
            System.out.println("Table created successfully");
        } catch (BigQueryException e) {
            System.out.println("Table was not created. \n" + e.toString());
        }
    }
}
// [END bigquery_create_table]