package de.leipzig.htwk.gitrdf.listener.api.documentation;

import de.leipzig.htwk.gitrdf.listener.api.model.response.error.BadRequestErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(
        responseCode = "400",
        description = "No rdf file is available yet",
        content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = BadRequestErrorResponse.class),
                examples = @ExampleObject("{\"status\": \"Bad Request\", \"reason\": \"Specified repository was not yet processed and therefore also doesnt contain a rdf file to download\", \"solution\": \"Wait until the repository was successfully processed (ie. status of repository is 'DONE')\"}")))
public @interface NoRdfFileAvailableYetBadRequestApiResponse {
}
