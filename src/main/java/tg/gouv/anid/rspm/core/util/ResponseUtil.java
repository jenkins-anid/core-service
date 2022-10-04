package tg.gouv.anid.rspm.core.util;

import org.springframework.http.HttpStatus;
import tg.gouv.anid.common.entities.util.Response;

public class ResponseUtil {
    private ResponseUtil() {
        //Default constructor
    }

    public static Response successResponse(Object data) {
        return Response.builder()
                .status(HttpStatus.OK)
                .message("message.success")
                .service("core-service")
                .data(data).build();
    }
}
