package io.appform.statesman.server.callbacktransformation.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.appform.statesman.server.callbacktransformation.TransformationTemplate;
import io.appform.statesman.server.callbacktransformation.TransformationTemplateType;
import io.appform.statesman.server.callbacktransformation.TransformationTemplateVisitor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 *
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OneShotTransformationTemplate extends TransformationTemplate  {
    @NotNull
    @NotEmpty
    String template;

    @Builder
    public OneShotTransformationTemplate(
            @JsonProperty("provider") String provider,
            @JsonProperty("idPath") String idPath,
            @JsonProperty("template") String template) {
        super(TransformationTemplateType.ONE_SHOT, idPath, provider);
        this.template = template;
    }

    @Override
    public <T> T accept(TransformationTemplateVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
