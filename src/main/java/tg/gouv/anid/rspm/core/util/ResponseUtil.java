package tg.gouv.anid.rspm.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import tg.gouv.anid.common.entities.util.Response;

@Slf4j(topic = "EventLog")
public class ResponseUtil {

    private static final String SERVICE = "core-service";
    private ResponseUtil() {
        //Default constructor
    }

    public static Response successResponse(Object data) {
        log.info("===>SUCCESS RESPONSE : {}", data);
        return Response.builder()
                .status(HttpStatus.OK)
                .message("default.message.success")
                .service(SERVICE)
                .data(data).build();
    }

    public static Response successResponse(Object data, String message) {
        log.info("===>SUCCESS RESPONSE : {}", data);
        return Response.builder()
                .status(HttpStatus.OK)
                .message(message)
                .service(SERVICE)
                .data(data).build();
    }

    public static Response errorResponse(HttpStatus status, String message) {
        return Response.builder()
                .status(status)
                .message(message)
                .service(SERVICE)
                .data(null).build();
    }
}
