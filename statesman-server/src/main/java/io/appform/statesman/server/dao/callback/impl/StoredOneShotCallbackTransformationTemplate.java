package io.appform.statesman.server.dao.callback.impl;

import io.appform.statesman.server.callbacktransformation.TransformationTemplateType;
import io.appform.statesman.server.dao.callback.StoredCallbackTransformationTemplate;
import io.appform.statesman.server.dao.callback.StoredCallbackTransformationTemplateVisitor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "ONE_SHOT")
public class StoredOneShotCallbackTransformationTemplate extends StoredCallbackTransformationTemplate {

    @Builder
    public StoredOneShotCallbackTransformationTemplate(String provider,
                                                       String idPath,
                                                       byte[] template) {
        super(TransformationTemplateType.ONE_SHOT, provider, idPath, template);
    }

    @Override
    public <T> T visit(StoredCallbackTransformationTemplateVisitor<T> visitor) {
        return visitor.visit(this);
    }


}
