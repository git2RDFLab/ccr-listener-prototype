package de.leipzig.htwk.gitrdf.listener.api.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    public static BadRequestException noMultiPartFileWasGiven() {

        String status = "Bad Request";
        String reason = "No file was given";
        String solution = "Include a zip file containing only a .git repository in the request. The file attribute of the multipart request has to be 'file'";

        String message = getMessageFrom(status, reason, solution);

        return new BadRequestException(message, status, reason, solution);
    }

    public static BadRequestException noFileNameWasGiven() {

        String status = "Bad Request";
        String reason = "No file name was specified";
        String solution = "Specify a file name as part of the multipart request. The file name is to be specified as a query parameter with the title 'name'";

        String message = getMessageFrom(status, reason, solution);

        return new BadRequestException(message, status, reason, solution);
    }

    public static BadRequestException cantDisableAllRepositoryFilterOptions() {

        String status = "Bad Request";
        String reason = "All repository filter options are disabled";
        String solution = "Enable at least one repository filter option";

        String message = getMessageFrom(status, reason, solution);

        return new BadRequestException(message, status, reason, solution);
    }

    public static BadRequestException unsupportedContentType(String supportedContentType) {

        String status = "Bad Request";
        String reason = "Unsupported content type was specified";
        String solution = String.format("Supported content type is '%s'", supportedContentType);

        String message = getMessageFrom(status, reason, solution);

        return new BadRequestException(message, status, reason, solution);
    }

    public static BadRequestException invalidZipFile() {

        String status = "Bad Request";
        String reason = "Invalid zip file was given";
        String solution = "The zip file should only contain the .git directory and its corresponding content";

        String message = getMessageFrom(status, reason, solution);

        return new BadRequestException(message, status, reason, solution);
    }

    public static BadRequestException invalidId(String idString) {

        String status = "Bad Request";
        String reason = String.format("Invalid id '%s' was given", idString);
        String solution = "Provide a valid id. Example id: 55";

        String message = getMessageFrom(status, reason, solution);

        return new BadRequestException(message, status, reason, solution);
    }

    public static BadRequestException noOwnerSpecified() {
        String status = "Bad Request";
        String reason = "No owner was specified";
        String solution = "Specify an owner. For example: 'dotnet' (who is the owner for example of the repo 'core')";

        String message = getMessageFrom(status, reason, solution);

        return new BadRequestException(message, status, reason, solution);
    }

    public static BadRequestException noRepositorySpecified() {
        String status = "Bad Request";
        String reason = "No repository was specified";
        String solution = "Specify a repository. For example: 'core' (the owner 'dotnet' provides for example a 'core' repository)";

        String message = getMessageFrom(status, reason, solution);

        return new BadRequestException(message, status, reason, solution);
    }

    public static BadRequestException noRdfFileAvailableYet() {

        String status = "Bad Request";
        String reason = "Specified repository was not yet processed and therefore also doesnt contain a rdf file to download";
        String solution = "Wait until the repository was successfully processed (ie. status of repository is 'DONE')";

        String message = getMessageFrom(status, reason, solution);

        return new BadRequestException(message, status, reason, solution);
    }

    private final String status;
    private final String reason;
    private final String solution;

    private BadRequestException(String message, String status, String reason, String solution) {
        super(message);

        this.status = status;
        this.reason = reason;
        this.solution = solution;
    }

    private static String getMessageFrom(String status, String reason, String solution) {
        return String.format("Status: %s, Reason: %s, Solution: %s", status, reason, solution);
    }
}
