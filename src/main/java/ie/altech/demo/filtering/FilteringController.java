package ie.altech.demo.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering")
    public FilterBean retrieveFilterBean(){
        return new FilterBean("value1", "value2", "value3");
    }

    @GetMapping(path = "/filtering/list")
    public List<FilterBean> retrieveListOfFilterBean(){
        return Arrays.asList(
                new FilterBean("value1", "value2", "value3"),
                new FilterBean("value4", "value5", "value6")
                );
    }

    @GetMapping(path = "/filter")
    public FilteredBean retrieveFilteredBean(){
        return new FilteredBean("value1", "value2", "value3", "value4", "value5", "value6");
    }

    @GetMapping(path = "/filter/list")
    public List<FilteredBean> retrieveListOfFilteredBean(){
        return Arrays.asList(
                new FilteredBean("value11", "value12", "value13", "value14", "value15", "value16"),
                new FilteredBean("value21", "value22", "value23", "value24", "value25", "value26"),
                new FilteredBean("value31", "value32", "value33", "value34", "value35", "value36"),
                new FilteredBean("value41", "value42", "value43", "value44", "value45", "value46"));
    }

    @GetMapping(path = "/filter/dynamic")
    public MappingJacksonValue retrieveDynamicFilter(){
        DynamicalyFilteredBean bean = new DynamicalyFilteredBean("value1", "value2", "value3", "value4", "value5", "value6");
        MappingJacksonValue mapping = new MappingJacksonValue(bean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field_2", "field_4", "field_1", "field_5");
        FilterProvider filters = new SimpleFilterProvider().addFilter("FilteredBean", filter);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping(path = "/filter/dynamic/list")
    public MappingJacksonValue retrieveListOfDynamicFilter(){
        List<DynamicalyFilteredBean> beans = Arrays.asList(
                new DynamicalyFilteredBean("value11", "value12", "value13", "value14", "value15", "value16"),
                new DynamicalyFilteredBean("value21", "value22", "value23", "value24", "value25", "value26"),
                new DynamicalyFilteredBean("value31", "value32", "value33", "value34", "value35", "value36"),
                new DynamicalyFilteredBean("value41", "value42", "value43", "value44", "value45", "value46")
        );
        MappingJacksonValue mapping = new MappingJacksonValue(beans);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field_3", "field_4", "field_1", "field_6");
        FilterProvider filters = new SimpleFilterProvider().addFilter("FilteredBean", filter);
        mapping.setFilters(filters);
        return mapping;
    }
}
