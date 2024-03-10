package de.leipzig.htwk.gitrdf.listener.api.model.response.error;

import lombok.Value;

@Value
public class NotFoundErrorResponse {
    String status;
    String reason;
    String solution;
}

