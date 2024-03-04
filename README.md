# Prototype for accepting git repositories and providing status information

## Spring Initializr Template
https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.2.2&packaging=jar&jvmVersion=21&groupId=de.leipzig.htwk.gitrdf&artifactId=listener&name=listener&description=Archetype%20project%20for%20HTWK%20Leipzig%20-%20Project%20to%20transform%20git%20to%20RDF&packageName=de.leipzig.htwk.gitrdf.listener&dependencies=web,lombok,devtools,data-jpa,postgresql,testcontainers

## Dependency notice

In order to remove duplicate database jpa definitions, a shared database commons project was introduced, which is used
in all projects, that share the mentioned jpa entities and logic.
The project can be found under: https://github.com/git2RDFLab/database-shared-common

In order to compile this project you have to pull the shared database common project and install the maven artifact locally
(via: `mvn clean install`) as dependency, so this project can find the necessary dependency.

The database shared common dependency is already included in this project as a dependency in the pom with

```
<dependency>
	<groupId>de.leipzig.htwk.gitrdf.database</groupId>
	<artifactId>common</artifactId>
	<version>${de.leipzig.htwk.gitrdf.database.common.version}</version>
</dependency>
```

## Environment Variables

| Environment Variables      | Description                                                                                                                                                                                                                           |
|----------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `SPRING_DATASOURCE_URL`      | The fully qualified url to the database. Expects the database connection string as of the defined schema by the used database. This projects expects per default a postgres database. A default value is given for local deployments. |
| `SPRING_DATASOURCE_PASSWORD` | The password of the database. A default value is given for local deployments.                                                                                                                                                         |

### CURL-Example to upload file
```
curl -XPOST -F "file=@gitexample.zip;name=field1;filename=gitexample.zip;type=application/zip" localhost:8080/api/v1/git/upload
```

```
curl -XPOST -F "file=@gitexample.zip;filename=gitexample.zip;type=application/zip" localhost:8080/api/v1/git/upload?name=abc
```

### CURL-Example to delete .git repo + all connected resources
```
curl -XDELETE localhost:8080/api/v1/git/rdf/completedelete/{id}
```

## CURL-Example to list all github repositories in queue
```
curl -XGET localhost:8080/api/v1/github
```
## CURL-Example to insert github repository into queue
```
curl -XPOST -H "Content-type: application/json" -d '{"owner": "dotnet", "repository": "core"}' localhost:8080/api/v1/github/queue
```
## CURL-Example to insert github repository into queue with filter
```
curl -XPOST -H "Content-type: application/json" -d '{"owner": "dotnet", "repository": "core", "repositoryFilter": {"githubIssueFilter": {}, "gitCommitFilter": {"enableAuthorName": true}}}' localhost:8080/api/v1/github/queue/filter
```

NOTE:
- when any filter is specified, all other not explicitly named filters are _disabled_ (`false`) by default
- there are currently 2 filter-types which _all must_ be speciified when using a filter

### `gitCommitFilter`:
| FilterName |  Type |
| ----- | ----------- |
| `enableIssueId` | `bool` |
| `enableIssueState` | `bool` |
| `enableIssueTitle` | `bool` |
| `enableIssueBody` | `bool` |
| `enableIssueUser` | `bool` |
| `enableIssueLabels` | `bool` |
| `enableIssueAssignees` | `bool` |
| `enableIssueMilestone` | `bool` |
| `enableIssueCreatedAt` | `bool` |
| `enableIssueUpdatedAt` | `bool` |
| `enableIssueClosedAt` | `bool` |

### `gitCommitFilter`:
| FilterName | Type |
| ----- | ----------- |
| `enableCommitHash` | `bool` | 
| `enableAuthorName` | `bool` | 
| `enableAuthorEmail` | `bool` | 
| `enableAuthorDate` | `bool` | 
| `enableCommitDate` | `bool` | 
| `enableCommitterName` | `bool` | 
| `enableCommitterEmail` | `bool` | 
| `enableCommitMessage` | `bool` | 
| `enableCommitDiff` | `bool` | 
| `enableCommitBranch` | `bool` | 

## CURL-Example to delete github repository complete from queue
curl -XDELETE localhost:8080/api/v1/github/rdf/completedelete/{id}

