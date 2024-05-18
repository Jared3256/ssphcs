package com.shaninfosystem.SpecializedCare.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AuditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String>
{

    /**
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor()
    {
        return Optional.of("Shan Info-systems");
    }
}
