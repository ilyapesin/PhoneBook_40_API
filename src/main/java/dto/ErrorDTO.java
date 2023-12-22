package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@Getter
@ToString
@Builder
public class ErrorDTO {

//    "timestamp": "2023-12-18T19:27:26.488Z",
//            "status": 0,
//            "error": "string",
//            "message": {},
//            "path": "string"
    int status;
    String error;
    Object message;
}
