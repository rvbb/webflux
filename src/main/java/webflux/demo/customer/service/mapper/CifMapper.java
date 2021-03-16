package webflux.demo.customer.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import webflux.demo.customer.model.Cif;
import webflux.demo.customer.model.CifDto;
import webflux.demo.customer.util.DateTimeUtil;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CifMapper {

    CifMapper instance = Mappers.getMapper(CifMapper.class);

    CifDto toDto(Cif entity);

    @Named("convertList")
    List<CifDto> convertList(Collection<Cif> entities);

    Cif toEntity(CifDto request);

    @Named("convertDateToString")
    static String convertDateToString(Date date) {
        return DateTimeUtil.date2string(date, false);
    }
}

