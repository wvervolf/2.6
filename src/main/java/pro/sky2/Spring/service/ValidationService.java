package pro.sky2.Spring.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky2.Spring.exeption.IncorrectNameExeption;

@Service
public class ValidationService {
    public String validateCheckName(String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new IncorrectNameExeption();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }
}
