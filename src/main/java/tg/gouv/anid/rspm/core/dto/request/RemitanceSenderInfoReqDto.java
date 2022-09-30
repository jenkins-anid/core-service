package tg.gouv.anid.rspm.core.dto.request;

import lombok.Data;

@Data
public class RemitanceSenderInfoReqDto {
    private Long householdId;
    private String sex;
    private String relationship;
    private Double amount;
    private String devise;
}
