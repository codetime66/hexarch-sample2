package br.com.stelo.gsurf.token.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorMessage {

    private String message;
}
