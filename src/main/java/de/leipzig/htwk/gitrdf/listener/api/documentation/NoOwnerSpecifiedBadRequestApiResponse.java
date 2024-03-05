package de.leipzig.htwk.gitrdf.listener.api.documentation;

import de.leipzig.htwk.gitrdf.listener.api.model.response.error.BadRequestErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(
        responseCode = "400",
        description = "No owner was specified",
        content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = BadRequestErrorResponse.class),
                examples = @ExampleObject("{\"status\": \"Bad Request\", \"reason\": \"No owner was specified\", \"solution\": \"Specify an owner. For example: 'dotnet' (who is the owner for example of the repo 'core')\"}")))
public @interface NoOwnerSpecifiedBadRequestApiResponse {
}
