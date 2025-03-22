<a href="https://github.com/git2RDFLab/"><img align="right" role="right" height="96" src="https://github.com/git2RDFLab/.github/blob/main/profile/images/GitLotus-logo.png?raw=true" style="height: 96px;z-index: 1000000" title="GitLotus" alt="GitLotus"/></a>

# GitLotus component -- Web service for accepting Git repositories (that should be transformed to RDF data) and providing status information about the status of tasks

The component is available as a Docker image [superdose/git2rdf-listener-service](https://hub.docker.com/r/superdose/git2rdf-listener-service/tags).
See the repository [project-deployment-compose](https://github.com/git2RDFLab/project-deployment-compose/tree/main) for a prepared Docker container starting and configuration script.

## Build and execution environment

The [Spring Boot](https://spring.io/projects/spring-boot) service can be created using [Apache Maven](https://maven.apache.org/).

```ShellSession
git clone git@github.com:git2RDFLab/ccr-listener-prototype.git
cd sparql-query-prototype
mvn package
```

See the folder `target` for the executable JAR file.

**Dependency notice:** To remove duplicate database [JPA](https://spring.io/projects/spring-data-jpa) definitions, a shared database commons project was introduced. See [database-shared-common](https://github.com/git2RDFLab/database-shared-common/) for installing this GitLotus-specific dependency.

## OpenAPI Documentation

The OpenAPI/Swagger documentation can be found under '/listener-service/swagger'.

## Environment Variables

| Environment Variables        | Description                                                                                                                                                                                                                           |
|------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `SPRING_DATASOURCE_URL`      | The fully qualified URL to the database. Expects the database connection string as of the defined schema by the used database. This project expects per default a Postgres database. A default value is given for local deployments. |
| `SPRING_DATASOURCE_PASSWORD` | The password of the database. A default value is given for local deployments.                                                                                                                                                         |

### CURL example  to upload a file

```
curl -XPOST -F "file=@gitexample.zip;name=field1;filename=gitexample.zip;type=application/zip" localhost:8080/listener-service/api/v1/git/upload
```

Endpoint: `/listener-service/api/v1/git/upload?name=abc`

```
curl -XPOST -F "file=@gitexample.zip;filename=gitexample.zip;type=application/zip" localhost:8080/listener-service/api/v1/git/upload?name=abc
```

### CURL example to delete .git repo + all connected resources

Endpoint: `/listener-service/api/v1/git/rdf/completedelete/{id}`

```
curl -XDELETE localhost:8080/listener-service/api/v1/git/rdf/completedelete/{id}
```

## CURL example  to list all GitHub repositories in the queue of jobs

Endpoint: `/listener-service/api/v1/github`

```
curl -XGET localhost:8080/listener-service/api/v1/github
```

## CURL example  to insert GitHub repository into the queue of jobs

Endpoint: `/listener-service/api/v1/github/queue`

```
curl -XPOST -H "Content-type: application/json" -d '{"owner": "dotnet", "repository": "core"}' localhost:8080/listener-service/api/v1/github/queue
```

## CURL example  to insert GitHub repository into the queue  of jobs with a filter

Endpoint: `/listener-service/api/v1/github/queue/filter`

```
curl -XPOST -H "Content-type: application/json" -d '{"owner": "dotnet", "repository": "core", "repositoryFilter": {"githubIssueFilter": {}, "gitCommitFilter": {"enableAuthorName": true}}}' localhost:8080/listener-service/api/v1/github/queue/filter
```

*NOTE*

- when any filter is specified, all other not explicitly named filters are _disabled_ (`false`) by default
- there are currently 2 filter-types which _all must_ be specified when using a filter

### `githubIssueFilter`:

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

## CURL example to delete GitHub repository complete from the queue of jobs

Endpoint: `/listener-service/api/v1/github/rdf/completedelete/{id}`

```
curl -XDELETE localhost:8080/listener-service/api/v1/github/rdf/completedelete/{id}
```


[Spring Initializr Template](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.2.2&packaging=jar&jvmVersion=21&groupId=de.leipzig.htwk.gitrdf&artifactId=listener&name=listener&description=Archetype%20project%20for%20HTWK%20Leipzig%20-%20Project%20to%20transform%20git%20to%20RDF&packageName=de.leipzig.htwk.gitrdf.listener&dependencies=web,lombok,devtools,data-jpa,postgresql,testcontainers)


## Contribute

We are happy to receive your contributions. 
Please create a pull request or an issue. 
As this tool is published under the MIT license, feel free to fork it and use it in your own projects.

## Disclaimer

This tool just temporarily stores the image data. 
It is provided "as is" and without any warranty, express or implied.

