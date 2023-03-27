package com.makiia.crosscutting.domain.model;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntyRecmaesfavoritoResponse {
    private  String rspValue ="OK";
    private  String rspMessage ="OK";
    private  PaginationResponse rspPagination;
    private  List<EntyRecmaesfavoritoDto> rspData;
}