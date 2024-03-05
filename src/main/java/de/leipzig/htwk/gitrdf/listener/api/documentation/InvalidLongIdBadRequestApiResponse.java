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
        description = "Invalid id was specified",
        content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = BadRequestErrorResponse.class),
                examples = @ExampleObject("{\"status\": \"Bad Request\", \"reason\": \"Invalid id 'blub' was given\", \"solution\": \"Provide a valid id. Example id: 55\"}")))
public @interface InvalidLongIdBadRequestApiResponse {
}
