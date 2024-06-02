package pro.sky2.Spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    public String validateCheckName(String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new ValidationFiledExeption();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }
}
